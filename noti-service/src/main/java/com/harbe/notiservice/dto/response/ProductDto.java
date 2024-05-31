package com.harbe.notiservice.dto.response;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private double price;
    private double discountRate;
}
