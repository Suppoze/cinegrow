package hu.zsoki.cinegrow.api.omdb;

import hu.zsoki.cinegrow.api.omdb.model.request.OmdbRequest;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbSearchRequest;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbTitleRequest;
import hu.zsoki.cinegrow.api.omdb.model.request.SearchType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Component
class OmdbQueryBuilder {

    @Value("${omdb.url}")
    private String omdbUrl;

    @Value("${omdbApiKey}") // Environment variable
    private String apiKey;

    String buildUriString(OmdbRequest omdbRequest) {
        if (omdbRequest instanceof OmdbSearchRequest) {
            return buildUriString((OmdbSearchRequest) omdbRequest);
        }
        if (omdbRequest instanceof OmdbTitleRequest) {
            return buildUriString((OmdbTitleRequest) omdbRequest);
        }
        throw new IllegalArgumentException("Cannot build OMDb API query: unknown request.");
    }

    private String buildUriString(OmdbSearchRequest searchRequest) {
        UriComponentsBuilder uriComponentsBuilder = getDefaultUriComponentsBuilder();

        uriComponentsBuilder.queryParam(OmdbSearchRequest.SEARCH_TITLE_FIELD, searchRequest.getTitle());

        Optional.ofNullable(searchRequest.getSearchType())
                .ifPresent(it -> uriComponentsBuilder
                        .queryParam(OmdbSearchRequest.SEARCH_SEARCH_TYPE_FIELD, it.getArgString()));

        Optional.ofNullable(searchRequest.getYear())
                .ifPresent(it -> uriComponentsBuilder
                        .queryParam(OmdbSearchRequest.SEARCH_YEAR_FIELD, it.toString()));

        Optional.ofNullable(searchRequest.getResponseDataType())
                .ifPresent(it -> uriComponentsBuilder
                        .queryParam(OmdbSearchRequest.SEARCH_RESPONSE_DATA_TYPE_FIELD, it.getArgString()));

        Optional.ofNullable(searchRequest.getPage())
                .ifPresent(it -> uriComponentsBuilder
                        .queryParam(OmdbSearchRequest.SEARCH_PAGE_TYPE_FIELD, it.toString()));

        Optional.ofNullable(searchRequest.getApiVersion())
                .ifPresent(it -> uriComponentsBuilder
                        .queryParam(OmdbSearchRequest.SEARCH_API_VERSION_FIELD, it.toString()));

        return uriComponentsBuilder.toUriString();
    }

    private String buildUriString(OmdbTitleRequest titleRequest) {
        UriComponentsBuilder uriComponentsBuilder = getDefaultUriComponentsBuilder();

        uriComponentsBuilder.queryParam(OmdbTitleRequest.TITLE_TITLE_FIELD, titleRequest.getTitle());

        Optional.ofNullable(titleRequest.getSearchType())
                .ifPresent((SearchType it) -> uriComponentsBuilder
                        .queryParam(OmdbTitleRequest.TITLE_SEARCH_TYPE_FIELD, it.getArgString()));

        Optional.ofNullable(titleRequest.getYear())
                .ifPresent(it -> uriComponentsBuilder
                        .queryParam(OmdbTitleRequest.TITLE_YEAR_FIELD, it.toString()));

        Optional.ofNullable(titleRequest.getPlotLength())
                .ifPresent(it -> uriComponentsBuilder
                        .queryParam(OmdbTitleRequest.TITLE_PLOT_LENGTH_FIELD, it.getArgString()));

        Optional.ofNullable(titleRequest.getResponseDataType())
                .ifPresent(it -> uriComponentsBuilder
                        .queryParam(OmdbTitleRequest.TITLE_RESPONSE_DATA_TYPE_FIELD, it.getArgString()));

        Optional.ofNullable(titleRequest.getApiVersion())
                .ifPresent(it -> uriComponentsBuilder
                        .queryParam(OmdbTitleRequest.TITLE_API_VERSION_FIELD, it.toString()));

        return uriComponentsBuilder.toUriString();
    }

    private UriComponentsBuilder getDefaultUriComponentsBuilder() {
        return UriComponentsBuilder
                .fromUriString(omdbUrl)
                .queryParam("apikey", apiKey);
    }

}
