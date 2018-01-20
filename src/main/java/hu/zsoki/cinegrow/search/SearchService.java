package hu.zsoki.cinegrow.search;

import hu.zsoki.cinegrow.api.omdb.OmdbClient;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbRequest;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbSearchRequest;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbTitleOrIdRequest;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbSearchResponse;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbMovieResponse;
import hu.zsoki.cinegrow.search.model.MovieResponse;
import hu.zsoki.cinegrow.search.model.SearchRequest;
import hu.zsoki.cinegrow.search.model.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchService {

    private final OmdbClient omdbClient;

    @Autowired
    public SearchService(OmdbClient omdbClient) {
        this.omdbClient = omdbClient;
    }

    public SearchResponse search(SearchRequest searchRequest) {
        OmdbRequest omdbRequest = new OmdbSearchRequest(searchRequest);
        OmdbSearchResponse omdbSearchResponse = omdbClient.executeOmdbRequest(omdbRequest, OmdbSearchResponse.class);

        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setResponse(omdbSearchResponse.toString()); // TODO: mapping instead of toString.
        return searchResponse;
    }

    public MovieResponse getByTitle(String title) {
        OmdbRequest omdbRequest = OmdbTitleOrIdRequest.builder.buildWithTitle(title);
        OmdbMovieResponse omdbMovieResponse = omdbClient.executeOmdbRequest(omdbRequest, OmdbMovieResponse.class);
        return new MovieResponse(); // TODO: fill with data.
    }

    public MovieResponse getById(String id) {
        OmdbRequest omdbRequest = OmdbTitleOrIdRequest.builder.buildWithId(id);
        OmdbMovieResponse omdbMovieResponse = omdbClient.executeOmdbRequest(omdbRequest, OmdbMovieResponse.class);
        return new MovieResponse(); // TODO: fill with data.
    }
}
