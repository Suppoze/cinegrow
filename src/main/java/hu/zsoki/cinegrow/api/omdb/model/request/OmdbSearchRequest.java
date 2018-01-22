package hu.zsoki.cinegrow.api.omdb.model.request;

import hu.zsoki.cinegrow.api.omdb.model.request.enums.DataType;
import hu.zsoki.cinegrow.api.omdb.model.request.enums.SearchType;
import hu.zsoki.cinegrow.search.model.request.SearchRequest;
import lombok.Data;
import lombok.NonNull;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Year;
import java.util.Optional;

@Data
public class OmdbSearchRequest implements OmdbRequest {

    private static final String TITLE_FIELD = "s";
    private static final String SEARCH_TYPE_FIELD = "type";
    private static final String YEAR_FIELD = "y";
    private static final String RESPONSE_DATA_TYPE_FIELD = "r";
    private static final String PAGE_TYPE_FIELD = "p";
    private static final String API_VERSION_FIELD = "v";

    @NonNull
    private String title;
    private SearchType searchType;
    private Year year;
    private DataType responseDataType = DataType.JSON;
    private Integer page = 1;
    private Integer apiVersion = 1;

    public OmdbSearchRequest(SearchRequest searchRequest) {
        title = searchRequest.getTitle();
    }

    public String buildUriString(final UriComponentsBuilder uriComponentsBuilder) {
        UriComponentsBuilder builderCopy = uriComponentsBuilder.cloneBuilder();
        builderCopy.queryParam(TITLE_FIELD, title);
        Optional.ofNullable(searchType).ifPresent(it -> builderCopy.queryParam(SEARCH_TYPE_FIELD, it.getArgString()));
        Optional.ofNullable(year).ifPresent(it -> builderCopy.queryParam(YEAR_FIELD, it.toString()));
        Optional.ofNullable(responseDataType).ifPresent(it -> builderCopy.queryParam(RESPONSE_DATA_TYPE_FIELD, it.getArgString()));
        Optional.ofNullable(page).ifPresent(it -> builderCopy.queryParam(PAGE_TYPE_FIELD, it.toString()));
        Optional.ofNullable(apiVersion).ifPresent(it -> builderCopy.queryParam(API_VERSION_FIELD, it.toString()));
        return builderCopy.toUriString();
    }

    public void setPage(int page) {
        if (page < 1 || page > 100) {
            throw new IllegalArgumentException("Page number is out of range. Valid range: [1-100]");
        }
        this.page = page;
    }

}
