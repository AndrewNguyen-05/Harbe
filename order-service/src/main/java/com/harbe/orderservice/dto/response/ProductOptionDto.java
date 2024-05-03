package com.harbe.orderservice.dto.response;

import lombok.Data;

@Data
public class ProductOptionDto {
    private Long id;

    private String name;
    private String value;
}
