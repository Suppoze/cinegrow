package hu.zsoki.cinegrow.api.omdb;

import hu.zsoki.cinegrow.api.omdb.model.response.OmdbResponse;
import org.springframework.stereotype.Component;

@Component
class OmdbResponseVerifier {

    public <T extends OmdbResponse> void verify(T omdbResponse) throws OmdbClientException {
        if (omdbResponse.hasError()) {
            throw new OmdbClientException(omdbResponse.getErrorMessage());
        }
    }

}
