package com.whitbread.venuesearch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * NOTE: We could handle various exceptions here and convert them into
 * appropriate error response object. We can override methods from ResponseEntityExceptionHandler
 * and create ErrorResponse object in them.
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({HttpClientErrorException.class})
    protected ResponseEntity<ExceptionResponse> handleBadClientRequest(HttpClientErrorException ex) {
        final ExceptionResponse er = new ExceptionResponse();
        er.setDebugMessage("Bad Request returned by foursquare API.");
        er.setMessage(ex.getMessage());
        er.setStatus(HttpStatus.BAD_REQUEST);

        return buildResponseEntity(er);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex) {
        final ExceptionResponse er = new ExceptionResponse();
        er.setDebugMessage("General run-time exception.");
        er.setMessage(ex.getMessage());
        er.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return buildResponseEntity(er);
    }

    private ResponseEntity<ExceptionResponse> buildResponseEntity(ExceptionResponse exceptionResponse) {
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }
}
