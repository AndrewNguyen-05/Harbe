package com.harbe.notificationservice.dto.response;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private double price;
    private double discountRate;
}
