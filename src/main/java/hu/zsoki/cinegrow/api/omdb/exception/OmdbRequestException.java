package hu.zsoki.cinegrow.api.omdb.exception;

public class OmdbRequestException extends RuntimeException {

    public OmdbRequestException() {
        super("Invalid OMDb request.");
    }

}
