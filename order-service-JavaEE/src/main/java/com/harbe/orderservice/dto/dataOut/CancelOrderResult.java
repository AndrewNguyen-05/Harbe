package com.harbe.orderservice.dto.dataOut;


import lombok.Data;

@Data
public class CancelOrderResult {
    String message;
    boolean isSuccess;
}
