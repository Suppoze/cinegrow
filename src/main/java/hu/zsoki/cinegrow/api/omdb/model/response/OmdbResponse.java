package hu.zsoki.cinegrow.api.omdb.model.response;

public interface OmdbResponse {

    boolean hasError();

    String getErrorMessage();

}
