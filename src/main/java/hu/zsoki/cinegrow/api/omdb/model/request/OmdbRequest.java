package hu.zsoki.cinegrow.api.omdb.model.request;

import org.springframework.web.util.UriComponentsBuilder;

public interface OmdbRequest {

    void appendQueryParams(UriComponentsBuilder uriComponentsBuilder);

}
