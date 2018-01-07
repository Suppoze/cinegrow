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

    // Environment variable
    @Value("${omdbApiKey}")
    private String apiKey;

    @Value("${omdb.url}")
    private String omdbUrl;
    private OmdbResponseVerifier verifier;

    @Autowired
    public OmdbClient(OmdbResponseVerifier verifier) {
        this.verifier = verifier;
    }

    public <T extends OmdbRequest, R extends OmdbResponse> R executeOmdbRequest(T omdbRequest, Class<R> responseType)
            throws OmdbClientException {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(omdbUrl)
                .queryParam("apikey", apiKey);
        omdbRequest.appendQueryParams(builder);

        RestTemplate restTemplate = new RestTemplate();
        R omdbResponse = restTemplate.getForObject(builder.toUriString(), responseType);

        verifier.verify(omdbResponse);

        return omdbResponse;
    }

}
