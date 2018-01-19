package hu.zsoki.cinegrow.common;

import hu.zsoki.cinegrow.api.omdb.model.request.OmdbRequest;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbSearchRequest;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbTitleRequest;
import hu.zsoki.cinegrow.search.model.SearchRequest;
import org.springframework.stereotype.Component;

@Component
public class SearchRequestMapper {

    // TODO move to OmdbRequests.
    public <T extends OmdbRequest> OmdbRequest map(SearchRequest from, Class<T> toType) {
        if (toType == OmdbTitleRequest.class) {
            return new OmdbTitleRequest(from.getTitle());
        }
        if (toType == OmdbSearchRequest.class) {
            return new OmdbSearchRequest(from.getTitle());
        }
        throw new IllegalArgumentException(String.format("Cannot map SearchRequest to type %s", toType));
    }

}
