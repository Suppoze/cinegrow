package hu.zsoki.cinegrow.api.omdb;

import hu.zsoki.cinegrow.api.omdb.model.response.OmdbResponse;
import org.springframework.stereotype.Component;

@Component
class OmdbResponseVerifier {

    <T extends OmdbResponse> void verify(T omdbResponse) {
        if (omdbResponse.hasError()) {
            throw new OmdbClientException(omdbResponse.getErrorMessage());
        }
    }

}
