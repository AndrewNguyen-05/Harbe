package com.harbe.productservice.dto.model;

import lombok.Data;

import java.util.Set;

@Data
public class CategoryDto {
    private Long id;

    private String name;
    private String urlKey;
    private String thumbnailUrl;
    private Long parentId;
    private boolean isPrimary;

    private Set<ProductDto> products;
}
