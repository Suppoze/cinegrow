package hu.zsoki.cinegrow.search.model.response;

import hu.zsoki.cinegrow.api.omdb.model.response.OmdbSearchResponse;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SearchResponse {

    private final List<SearchResultEntry> searchResults;

    public SearchResponse(OmdbSearchResponse omdbSearchResponse) {
        this.searchResults = omdbSearchResponse.getSearchResults().stream()
                .map(SearchResultEntry::new)
                .collect(Collectors.toList());
    }

}
