package com.harbe.notificationservice.exception;

import org.springframework.http.HttpStatus;

public class HarbeAPIException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public HarbeAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HarbeAPIException(String message, HttpStatus status, String message1) {
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
