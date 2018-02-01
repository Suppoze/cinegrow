package hu.zsoki.cinegrow.search.service;

import hu.zsoki.cinegrow.api.omdb.OmdbClient;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbRequest;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbSearchRequest;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbTitleOrIdRequest;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbSearchResponse;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbMovieResponse;
import hu.zsoki.cinegrow.data.mongo.MovieRepository;
import hu.zsoki.cinegrow.data.mongo.document.Movie;
import hu.zsoki.cinegrow.search.model.response.MovieResponse;
import hu.zsoki.cinegrow.search.model.request.SearchRequest;
import hu.zsoki.cinegrow.search.model.response.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
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

    // TODO: validation with aspect?
    @Override
    public SearchResponse search(final SearchRequest searchRequest) {
        final OmdbRequest omdbRequest = new OmdbSearchRequest(searchRequest);
        final OmdbSearchResponse omdbSearchResponse = omdbClient.executeOmdbRequest(omdbRequest, OmdbSearchResponse.class);

        final List<Movie> movies = omdbSearchResponse.getSearchResults().stream()
                .map(Movie::new)
                .collect(Collectors.toList());
        movieRepository.insert(movies);

        return new SearchResponse(omdbSearchResponse);
    }

    @Override
    public MovieResponse getByTitle(final String title) {
        final Movie existingMovie = movieRepository.findMovieByTitle(title);
        if (existingMovie != null) {
            return new MovieResponse(existingMovie);
        }

        final OmdbRequest omdbRequest = OmdbTitleOrIdRequest.builder.buildWithTitle(title);
        final OmdbMovieResponse omdbMovieResponse = omdbClient.executeOmdbRequest(omdbRequest, OmdbMovieResponse.class);

        movieRepository.insert(new Movie(omdbMovieResponse));

        return new MovieResponse(omdbMovieResponse);
    }

    @Override
    public MovieResponse getById(String id) {
        final Movie existingMovie = movieRepository.findMovieByImdbID(id);
        if (existingMovie != null) {
            return new MovieResponse(existingMovie);
        }

        final OmdbRequest omdbRequest = OmdbTitleOrIdRequest.builder.buildWithId(id);
        final OmdbMovieResponse omdbMovieResponse = omdbClient.executeOmdbRequest(omdbRequest, OmdbMovieResponse.class);

        movieRepository.insert(new Movie(omdbMovieResponse));

        return new MovieResponse(omdbMovieResponse);
    }
}
