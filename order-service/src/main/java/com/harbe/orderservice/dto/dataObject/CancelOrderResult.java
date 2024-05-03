package com.harbe.orderservice.dto.dataObject;


import lombok.Data;

@Data
public class CancelOrderResult {
    String message;
    boolean isSuccess;
}
