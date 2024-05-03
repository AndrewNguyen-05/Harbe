package com.harbe.orderservice.dto.response;


import lombok.Data;


@Data
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private double discountRate;
    private String thumbnailUrl;
    private ProductOptionDto option;
}

