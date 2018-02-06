package hu.zsoki.cinegrow.common;

import hu.zsoki.cinegrow.api.omdb.exception.OmdbClientException;
import hu.zsoki.cinegrow.api.omdb.exception.OmdbRequestException;
import hu.zsoki.cinegrow.api.omdb.model.request.OmdbRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String OMDB_CLIENT_EXCEPTION_TEMPLATE = "Error during OMDb API request: %s (%s)";
    private static final String OMDB_REQUEST_EXCEPTION_TEMPLATE = "Error with OMDb API request: %s (%s)";

    @ExceptionHandler(value = OmdbClientException.class)
    ResponseEntity<String> handleOmdbClientException(OmdbClientException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(String.format(OMDB_CLIENT_EXCEPTION_TEMPLATE, ex.getMessage(), HttpStatus.BAD_GATEWAY.getReasonPhrase()));
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<String> handleGeneralException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

}
