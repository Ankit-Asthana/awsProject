package com.example.AMS.exception;


import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ApiException extends ResponseStatusException {
    public ApiException(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public ApiException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
    //    private final HttpStatus status;
//    public ApiException(String message, HttpStatus status){
//        super(message);
//        this.status=status;
//    }
}
