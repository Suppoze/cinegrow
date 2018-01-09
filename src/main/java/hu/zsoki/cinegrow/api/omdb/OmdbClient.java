package hu.zsoki.cinegrow.api.omdb;

import hu.zsoki.cinegrow.api.omdb.model.request.OmdbRequest;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OmdbClient {

    private final OmdbQueryBuilder omdbQueryBuilder;
    private final OmdbResponseVerifier verifier;

    @Autowired
    public OmdbClient(OmdbQueryBuilder omdbQueryBuilder, OmdbResponseVerifier verifier) {
        this.omdbQueryBuilder = omdbQueryBuilder;
        this.verifier = verifier;
    }

    public <T extends OmdbResponse> T executeOmdbRequest(OmdbRequest omdbRequest, Class<T> responseType) {
        String uriString = omdbQueryBuilder.buildUriString(omdbRequest);

        RestTemplate restTemplate = new RestTemplate();
        T omdbResponse = restTemplate.getForObject(uriString, responseType);

        verifier.verify(omdbResponse);

        return omdbResponse;
    }

}
