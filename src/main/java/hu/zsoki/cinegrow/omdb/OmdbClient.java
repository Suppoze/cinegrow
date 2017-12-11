package hu.zsoki.cinegrow.omdb;

import hu.zsoki.cinegrow.omdb.model.OmdbResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OmdbClient {

    @Value("${omdbApiKey}")
    private String apiKey;

    @Value("${omdb.url}")
    private String omdbUrl;

    public OmdbResponse search(String phrase) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(omdbUrl)
                .queryParam("apikey", apiKey)
                .queryParam("t", phrase);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(builder.toUriString(), OmdbResponse.class);
    }

}
