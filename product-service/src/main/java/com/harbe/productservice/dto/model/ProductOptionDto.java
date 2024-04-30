package com.harbe.productservice.dto.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductOptionDto {
    private Long id;

    private String name;
    private String value;
}
