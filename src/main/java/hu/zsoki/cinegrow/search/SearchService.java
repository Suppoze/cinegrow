package hu.zsoki.cinegrow.search;

import hu.zsoki.cinegrow.omdb.OmdbClient;
import hu.zsoki.cinegrow.omdb.model.OmdbResponse;
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
        OmdbResponse omdbResponse = omdbClient.search(searchRequest.getTitle());
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setResponse(omdbResponse.toString());
        return searchResponse;
    }

}
