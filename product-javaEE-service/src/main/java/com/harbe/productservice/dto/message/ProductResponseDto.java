package com.harbe.productservice.dto.message;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;

    private String name;
    private String brand;
    private double price;

    private double discountRate;
    private String thumbnailUrl;
    private int reviewCount;
    private double ratingAverage;
    private int quantitySold;
    private String productSlug;

}
