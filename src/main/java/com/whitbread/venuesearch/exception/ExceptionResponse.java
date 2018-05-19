package com.whitbread.venuesearch.exception;

import org.springframework.http.HttpStatus;

/**
 * class to contain helpful/useful information on standard exceptions thrown by the rest api and this response
 * object will be sent back to the client of the API when an exception occurs in this service.
 */
public class ExceptionResponse {

    private HttpStatus status;
    private String message;
    private String debugMessage;
    // we can have a useful time stamp, app sepcific error codes, list of other sub-exceptions etc. in here too....


    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() { return debugMessage; }
    public void setDebugMessage(String debugMessage) { this.debugMessage = debugMessage; }

    public HttpStatus getStatus() { return status; }
    public void setStatus(HttpStatus status) { this.status = status; }
}
