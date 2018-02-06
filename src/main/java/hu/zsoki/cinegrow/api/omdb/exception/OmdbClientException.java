package hu.zsoki.cinegrow.api.omdb.exception;

public class OmdbClientException extends RuntimeException {

    OmdbClientException(String message) {
        super(message);
    }

}
