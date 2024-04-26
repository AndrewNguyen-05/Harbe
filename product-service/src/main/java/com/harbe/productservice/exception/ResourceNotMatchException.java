package com.harbe.productservice.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotMatchException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public ResourceNotMatchException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResourceNotMatchException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
