package com.harbe.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class PaypalAccessTokenException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public PaypalAccessTokenException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public PaypalAccessTokenException(String message, HttpStatus status, String message1) {
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
