package com.harbe.productservice.dto.message;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class CategoryResponseDto {
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;
    private String urlKey;
    private String thumbnailUrl;
    private Long parentId;
}
