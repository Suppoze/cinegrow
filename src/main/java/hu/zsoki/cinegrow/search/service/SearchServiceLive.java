package hu.zsoki.cinegrow.search.service;

import hu.zsoki.cinegrow.api.omdb.OmdbClient;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbRequest;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbSearchRequest;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbTitleOrIdRequest;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbMovieResponse;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbSearchResponse;
import hu.zsoki.cinegrow.data.mongo.MovieRepository;
import hu.zsoki.cinegrow.data.mongo.document.Movie;
import hu.zsoki.cinegrow.search.exception.SearchServiceAsyncOMBbCallException;
import hu.zsoki.cinegrow.search.model.request.SearchRequest;
import hu.zsoki.cinegrow.search.model.response.MovieResponse;
import hu.zsoki.cinegrow.search.model.response.SearchResponse;
import hu.zsoki.cinegrow.search.model.response.SearchResultEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Profile("live")
@Component
public class SearchServiceLive implements SearchService {

    private final OmdbClient omdbClient;
    private final MovieRepository movieRepository;

    @Autowired
    public SearchServiceLive(OmdbClient omdbClient, MovieRepository movieRepository) {
        this.omdbClient = omdbClient;
        this.movieRepository = movieRepository;
    }
    
    @Override
    public SearchResponse search(SearchRequest searchRequest) {
        if (searchRequest.isCacheEnabled()) {
            return searchAll(searchRequest);
        }
        return searchApiOnly(searchRequest);
    }

    @Override
    public MovieResponse getById(String id) {
        final Movie existingMovie = movieRepository.findMovieByImdbID(id);
        if (existingMovie != null) {
            return new MovieResponse(existingMovie);
        }

        final OmdbRequest omdbRequest = OmdbTitleOrIdRequest.builder.buildWithId(id);
        final OmdbMovieResponse omdbMovieResponse = omdbClient.executeOmdbRequest(omdbRequest, OmdbMovieResponse.class);

        movieRepository.save(new Movie(omdbMovieResponse));

        return new MovieResponse(omdbMovieResponse);
    }

    private SearchResponse searchAll(SearchRequest searchRequest) {
        final List<SearchResultEntry> movies =
                movieRepository.findMoviesByTitleContains(searchRequest.getTitle()).stream()
                        .map(SearchResultEntry::new)
                        .collect(Collectors.toList());

        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future<SearchResponse> searchResponseFuture = executor.submit(() -> searchApiOnly(searchRequest));

        if (!movies.isEmpty()) {
            return new SearchResponse(movies);
        }

        return getSearchResponseFromFuture(searchResponseFuture);
    }

    private SearchResponse searchApiOnly(SearchRequest searchRequest) {
        final OmdbRequest omdbRequest = new OmdbSearchRequest(searchRequest);
        final OmdbSearchResponse omdbSearchResponse = omdbClient.executeOmdbRequest(omdbRequest, OmdbSearchResponse.class);

        final List<Movie> movies = omdbSearchResponse.getSearchResults().stream()
                .map(Movie::new)
                .collect(Collectors.toList());

        launchMovieDetailsBatchRequest(movies);
        movieRepository.saveAll(movies);

        return new SearchResponse(omdbSearchResponse);
    }

    private SearchResponse getSearchResponseFromFuture(Future<SearchResponse> searchResponseFuture) {
        final SearchResponse searchResponse;
        try {
            searchResponse = searchResponseFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new SearchServiceAsyncOMBbCallException("Exception during concurrent OMDb API call", e);
        }
        return searchResponse;
    }

    private void launchMovieDetailsBatchRequest(List<Movie> movies) {
        final ExecutorService executor = Executors.newWorkStealingPool();
        final List<Callable<Void>> callables = movies.stream()
                .map(movie -> OmdbTitleOrIdRequest.builder.buildWithId(movie.getImdbID()))
                .map(this::getOmdbMovieRequestPersistorCallable)
                .collect(Collectors.toList());
        try {
            executor.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Callable<Void> getOmdbMovieRequestPersistorCallable(final OmdbRequest omdbRequest) {
        return () -> {
            final OmdbMovieResponse omdbMovieResponse = omdbClient.executeOmdbRequest(omdbRequest, OmdbMovieResponse.class);
            movieRepository.save(new Movie(omdbMovieResponse));
            return null;
        };
    }

}
