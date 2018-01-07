package hu.zsoki.cinegrow.api.omdb.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Year;
import java.util.Optional;

@Data
public class OmdbSearchRequest implements OmdbRequest {

    @NonNull
    private String title;

    @Setter(AccessLevel.NONE) // Implemented through setPage
    private Integer page = 1;

    private SearchType searchType;
    private Year year;
    private DataType responseDataType = DataType.JSON;
    private Integer apiVersion = 1;

    public OmdbSearchRequest(String title) {
        this.title = title;
    }

    // FIXME in-out parameter
    public void appendQueryParams(UriComponentsBuilder uriComponentsBuilder) {
        uriComponentsBuilder.queryParam("s", title);
        Optional.ofNullable(searchType)
                .ifPresent(it -> uriComponentsBuilder.queryParam("type", it.name().toLowerCase()));
        Optional.ofNullable(year)
                .ifPresent(it -> uriComponentsBuilder.queryParam("y", it.toString()));
        Optional.ofNullable(responseDataType)
                .ifPresent(it -> uriComponentsBuilder.queryParam("r", it.name().toLowerCase()));
        Optional.ofNullable(page)
                .ifPresent(it -> uriComponentsBuilder.queryParam("p", it.toString()));
        Optional.ofNullable(apiVersion)
                .ifPresent(it -> uriComponentsBuilder.queryParam("v", it.toString()));
    }

    public void setPage(int page) {
        if (page < 1 || page > 100) {
            throw new IllegalArgumentException("Page number is out of range. Valid range: [1-100]");
        }
        this.page = page;
    }

}
