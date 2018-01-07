package hu.zsoki.cinegrow.common;

import hu.zsoki.cinegrow.api.omdb.OmdbClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandlerControllerAdvice.class);
    private static final String OMDB_CLIENT_EXCEPTION_TEMPLATE = "Error during OMDb API request: %s (%s)";

    @ExceptionHandler(value = OmdbClientException.class)
    ResponseEntity<?> handleOmdbException(OmdbClientException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(String.format(OMDB_CLIENT_EXCEPTION_TEMPLATE, ex.getMessage(), HttpStatus.BAD_GATEWAY.getReasonPhrase()));
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<?> handleGeneralException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

}
