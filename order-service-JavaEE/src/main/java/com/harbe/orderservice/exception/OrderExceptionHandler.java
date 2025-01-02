package com.harbe.orderservice.exception;


import com.harbe.orderservice.dto.exception.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

public class OrderExceptionHandler extends ResponseEntityExceptionHandler{

    // handle specific exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> harbeOrderExcpetionHanlder(Exception exception,
                                                                   WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

