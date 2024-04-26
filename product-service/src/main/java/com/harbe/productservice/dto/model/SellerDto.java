package com.harbe.productservice.dto.model;

import lombok.Data;

@Data
public class SellerDto {
    private Long id;

    private String name;
    private String logo;
    private String slug;
}
