package com.harbe.inventoryservice.dto.model;

import lombok.Data;

import java.util.List;


@Data
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private double discountRate;
    private String thumbnailUrl;
    private List<ProductOptionDto> option;
    private int quantity;
}

