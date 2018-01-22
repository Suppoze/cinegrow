package hu.zsoki.cinegrow.api.omdb.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class OmdbMovieRatingEntry {

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Value")
    private String value;

}
