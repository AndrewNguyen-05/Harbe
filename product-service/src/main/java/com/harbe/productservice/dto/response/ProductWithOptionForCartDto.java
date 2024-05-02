package com.harbe.productservice.dto.response;

import com.harbe.productservice.dto.model.ProductOptionDto;
import com.harbe.productservice.dto.model.ProductSpecificationDto;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ProductWithOptionForCartDto {
    private Long id;

    private String name;

    private String brand;

    private double price;

    private double discountRate;

    private String thumbnailUrl;

    private List<ProductOptionDto> option;
}
