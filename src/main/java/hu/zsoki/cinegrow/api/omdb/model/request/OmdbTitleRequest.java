package hu.zsoki.cinegrow.api.omdb.model.request;

import lombok.Data;
import lombok.NonNull;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Year;
import java.util.Optional;

@Data
public class OmdbTitleRequest implements OmdbRequest {

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

    // FIXME in-out parameter
    public void appendQueryParams(UriComponentsBuilder uriComponentsBuilder) {
        uriComponentsBuilder.queryParam("t", title);
        Optional.ofNullable(searchType)
                .ifPresent(it -> uriComponentsBuilder.queryParam("type", it.name().toLowerCase()));
        Optional.ofNullable(year)
                .ifPresent(it -> uriComponentsBuilder.queryParam("y", it.toString()));
        Optional.ofNullable(plotLength)
                .ifPresent(it -> uriComponentsBuilder.queryParam("plot", it.name().toLowerCase()));
        Optional.ofNullable(responseDataType)
                .ifPresent(it -> uriComponentsBuilder.queryParam("r", it.name().toLowerCase()));
        Optional.ofNullable(apiVersion)
                .ifPresent(it -> uriComponentsBuilder.queryParam("v", it.toString()));
    }

}
