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

    public static final String SEARCH_TITLE_FIELD = "s";
    public static final String SEARCH_SEARCH_TYPE_FIELD = "type";
    public static final String SEARCH_YEAR_FIELD = "y";
    public static final String SEARCH_RESPONSE_DATA_TYPE_FIELD = "r";
    public static final String SEARCH_PAGE_TYPE_FIELD = "p";
    public static final String SEARCH_API_VERSION_FIELD = "v";

    @NonNull
    private String title;
    private SearchType searchType;
    private Year year;
    private DataType responseDataType = DataType.JSON;

    @Setter(AccessLevel.NONE) // Implemented through setPage
    private Integer page = 1;
    private Integer apiVersion = 1;

    public OmdbSearchRequest(String title) {
        this.title = title;
    }

    public void setPage(int page) {
        if (page < 1 || page > 100) {
            throw new IllegalArgumentException("Page number is out of range. Valid range: [1-100]");
        }
        this.page = page;
    }

}
