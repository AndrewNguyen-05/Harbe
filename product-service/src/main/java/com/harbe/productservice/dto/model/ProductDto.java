package com.harbe.productservice.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

@Schema(
        description = "ProductDto Model Information"
)
@Data
public class ProductDto {
    private Long id;

    private String name;
    private String brand;
    private String description;
    private double price;
    private double discountRate;
    private String thumbnailUrl;
    private int reviewCount;
    private double ratingAverage;
    private int quantitySold;
    private String productSlug;
    private String categoryUrl;

    private Set<ProductOptionDto> options;
    private Set<ProductSpecificationDto> specifications;
}
