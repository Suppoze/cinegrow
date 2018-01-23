package hu.zsoki.cinegrow.search;

import hu.zsoki.cinegrow.api.omdb.OmdbClient;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbRequest;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbSearchRequest;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbTitleOrIdRequest;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbSearchResponse;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbMovieResponse;
import hu.zsoki.cinegrow.search.model.response.MovieResponse;
import hu.zsoki.cinegrow.search.model.request.SearchRequest;
import hu.zsoki.cinegrow.search.model.response.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchService {

    private final OmdbClient omdbClient;

    @Autowired
    public SearchService(OmdbClient omdbClient) {
        this.omdbClient = omdbClient;
    }

    // TODO: validation with aspect?
    SearchResponse search(final SearchRequest searchRequest) {
        final OmdbRequest omdbRequest = new OmdbSearchRequest(searchRequest);
        final OmdbSearchResponse omdbSearchResponse = omdbClient.executeOmdbRequest(omdbRequest, OmdbSearchResponse.class);
        return new SearchResponse(omdbSearchResponse);
    }

    MovieResponse getByTitle(final String title) {
        final OmdbRequest omdbRequest = OmdbTitleOrIdRequest.builder.buildWithTitle(title);
        final OmdbMovieResponse omdbMovieResponse = omdbClient.executeOmdbRequest(omdbRequest, OmdbMovieResponse.class);
        return new MovieResponse(omdbMovieResponse);
    }

    MovieResponse getById(String id) {
        final OmdbRequest omdbRequest = OmdbTitleOrIdRequest.builder.buildWithId(id);
        final OmdbMovieResponse omdbMovieResponse = omdbClient.executeOmdbRequest(omdbRequest, OmdbMovieResponse.class);
        return new MovieResponse(omdbMovieResponse);
    }
}
