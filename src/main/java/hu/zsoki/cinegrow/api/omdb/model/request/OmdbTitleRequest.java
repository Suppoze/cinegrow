package hu.zsoki.cinegrow.api.omdb.model.request;

import lombok.Data;
import lombok.NonNull;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Year;
import java.util.Optional;

@Data
public class OmdbTitleRequest implements OmdbRequest {

    public static final String TITLE_TITLE_FIELD = "t";
    public static final String TITLE_SEARCH_TYPE_FIELD = "type";
    public static final String TITLE_YEAR_FIELD = "y";
    public static final String TITLE_PLOT_LENGTH_FIELD = "p";
    public static final String TITLE_RESPONSE_DATA_TYPE_FIELD = "r";
    public static final String TITLE_API_VERSION_FIELD = "v";

    @NonNull
    private String title;
    private SearchType searchType;
    private Year year;
    private PlotLength plotLength = PlotLength.SHORT;
    private DataType responseDataType = DataType.JSON;
    private Integer apiVersion = 1;

    public OmdbTitleRequest(String title) {
        this.title = title;
    }

}
