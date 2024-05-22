package com.harbe.productservice.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Schema(
        description = "ProductDto Model Information"
)
@Data
public class ProductDto {
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, message = "Product name should have at least 2 characters!")
    private String name;

    @NotEmpty(message = "Brand should not be empty")
    @Size(min = 2, message = "Product brand should have at least 2 characters!")
    private String brand;

    @NotEmpty(message = "Description should not be empty")
    @Size(min = 10, message = "Product Description should have at least 2 characters!")
    private String description;

    @NotNull(message = "Price should not be empty")
    @Positive(message = "Price must be greater than zero")
    private double price;

    private double discountRate;

    @NotEmpty(message = "thumbnail should not be empty")
    private String thumbnailUrl;
    private int reviewCount;
    private double ratingAverage;
    private int quantitySold;
    private String productSlug;
    private String categoryUrl;

    private List<ProductOptionDto> options;
    private Set<ProductSpecificationDto> specifications;
}
