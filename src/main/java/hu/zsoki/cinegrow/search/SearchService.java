package hu.zsoki.cinegrow.search;

import hu.zsoki.cinegrow.api.omdb.OmdbClient;
import hu.zsoki.cinegrow.api.omdb.OmdbClientException;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbSearchRequest;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbTitleRequest;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbSearchResponse;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbTitleResponse;
import hu.zsoki.cinegrow.common.SearchRequestMapper;
import hu.zsoki.cinegrow.search.model.SearchRequest;
import hu.zsoki.cinegrow.search.model.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchService {

    private final OmdbClient omdbClient;
    private final SearchRequestMapper searchRequestMapper;

    @Autowired
    public SearchService(OmdbClient omdbClient,
                         SearchRequestMapper searchRequestMapper) {
        this.omdbClient = omdbClient;
        this.searchRequestMapper = searchRequestMapper;
    }

    public SearchResponse find(SearchRequest searchRequest) {
        SearchResponse searchResponse = new SearchResponse();

        OmdbTitleRequest omdbTitleRequest = (OmdbTitleRequest) searchRequestMapper.map(searchRequest, OmdbTitleRequest.class);
        OmdbTitleResponse omdbTitleResponse = omdbClient.executeOmdbRequest(omdbTitleRequest, OmdbTitleResponse.class);

        searchResponse.setResponse(omdbTitleResponse.toString()); // TODO: mapping instead of toString.
        return searchResponse;
    }

    public SearchResponse search(SearchRequest searchRequest) {
        SearchResponse searchResponse = new SearchResponse();

        OmdbSearchRequest omdbSearchRequest = (OmdbSearchRequest) searchRequestMapper.map(searchRequest, OmdbSearchRequest.class);
        OmdbSearchResponse omdbSearchResponse = omdbClient.executeOmdbRequest(omdbSearchRequest, OmdbSearchResponse.class);

        searchResponse.setResponse(omdbSearchResponse.toString()); // TODO: mapping instead of toString.
        return searchResponse;
    }

}
