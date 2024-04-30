package com.harbe.productservice.dto.mapper;

import com.harbe.productservice.dto.model.ProductDto;
import com.harbe.productservice.dto.response.ProductWithOptionDto;
import com.harbe.productservice.entity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductWithOptionMapper {

    private final ModelMapper mapper;
    public ProductWithOptionDto mapToProductOptionDto(Product product){
        ProductWithOptionDto productWithOptionDto = mapper.map(product, ProductWithOptionDto.class);
        return productWithOptionDto;
    }
}
