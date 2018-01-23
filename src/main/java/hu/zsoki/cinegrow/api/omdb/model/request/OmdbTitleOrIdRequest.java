package hu.zsoki.cinegrow.api.omdb.model.request;

import hu.zsoki.cinegrow.api.omdb.model.request.enums.DataType;
import hu.zsoki.cinegrow.api.omdb.model.request.enums.PlotLength;
import hu.zsoki.cinegrow.api.omdb.model.request.enums.SearchType;
import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Year;
import java.util.Optional;

@Data
public class OmdbTitleOrIdRequest implements OmdbRequest {

    public static final Builder builder = new Builder();

    private static final String TITLE_FIELD = "t";
    private static final String ID_FIELD = "i";
    private static final String SEARCH_TYPE_FIELD = "type";
    private static final String YEAR_FIELD = "y";
    private static final String PLOT_LENGTH_FIELD = "p";
    private static final String RESPONSE_DATA_TYPE_FIELD = "r";
    private static final String API_VERSION_FIELD = "v";

    private String title;
    private String id;
    private SearchType searchType;
    private Year year;
    private PlotLength plotLength = PlotLength.SHORT;
    private DataType responseDataType = DataType.JSON;
    private Integer apiVersion = 1;

    private OmdbTitleOrIdRequest() {
    }

    @Override
    public String buildUriString(final UriComponentsBuilder uriComponentsBuilder) {
        UriComponentsBuilder builderCopy = uriComponentsBuilder.cloneBuilder();
        Optional.ofNullable(title).ifPresent(it -> builderCopy.queryParam(TITLE_FIELD, it));
        Optional.ofNullable(id).ifPresent(it -> builderCopy.queryParam(ID_FIELD, it));
        Optional.ofNullable(searchType).ifPresent(it -> builderCopy.queryParam(SEARCH_TYPE_FIELD, it.getArgString()));
        Optional.ofNullable(year).ifPresent(it -> builderCopy.queryParam(YEAR_FIELD, it.toString()));
        Optional.ofNullable(plotLength).ifPresent(it -> builderCopy.queryParam(PLOT_LENGTH_FIELD, it.getArgString()));
        Optional.ofNullable(responseDataType).ifPresent(it -> builderCopy.queryParam(RESPONSE_DATA_TYPE_FIELD, it.getArgString()));
        Optional.ofNullable(apiVersion).ifPresent(it -> builderCopy.queryParam(API_VERSION_FIELD, it.toString()));
        return builderCopy.toUriString();
    }

    public static class Builder {

        public OmdbTitleOrIdRequest buildWithTitle(String title) {
            OmdbTitleOrIdRequest newInstance = new OmdbTitleOrIdRequest();
            newInstance.setTitle(title);
            newInstance.setId(null);
            return newInstance;
        }

        public OmdbTitleOrIdRequest buildWithId(String id) {
            OmdbTitleOrIdRequest newInstance = new OmdbTitleOrIdRequest();
            newInstance.setId(id);
            newInstance.setTitle(null);
            return newInstance;
        }

    }
}
