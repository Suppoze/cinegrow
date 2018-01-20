package hu.zsoki.cinegrow.api.omdb.model.request;

import org.springframework.web.util.UriComponentsBuilder;

public interface OmdbRequest {

    String buildUriString(final UriComponentsBuilder uriComponentsBuilder);

}
