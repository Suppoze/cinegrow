package hu.zsoki.cinegrow.search.model.response;

import hu.zsoki.cinegrow.api.omdb.model.response.OmdbSearchResponse;
import lombok.Data;

@Data
public class SearchResponse {

    private final OmdbSearchResponse omdbSearchResponse; // TODO: temporary while no frontend

    public SearchResponse(OmdbSearchResponse omdbSearchResponse) {
        this.omdbSearchResponse = omdbSearchResponse;
    }
}
