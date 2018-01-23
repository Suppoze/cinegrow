package hu.zsoki.cinegrow.api.omdb;

import hu.zsoki.cinegrow.api.omdb.model.request.OmdbRequest;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OmdbClient {

    // TODO: consider moving to constructor
    @Value("${omdb.url}")
    private String omdbUrl;

    @Value("${omdbApiKey}") // Environment variable
    private String apiKey;

    private final OmdbResponseVerifier verifier;

    @Autowired
    public OmdbClient(OmdbResponseVerifier verifier) {
        this.verifier = verifier;
    }

    public <T extends OmdbResponse> T executeOmdbRequest(OmdbRequest omdbRequest, Class<T> responseType) {
        String uriString = omdbRequest.buildUriString(getDefaultUriComponentsBuilder());

        RestTemplate restTemplate = new RestTemplate();
        T omdbResponse = restTemplate.getForObject(uriString, responseType);

        verifier.verify(omdbResponse);

        return omdbResponse;
    }

    private UriComponentsBuilder getDefaultUriComponentsBuilder() {
        return UriComponentsBuilder
                .fromUriString(omdbUrl)
                .queryParam("apikey", apiKey);
    }

}
