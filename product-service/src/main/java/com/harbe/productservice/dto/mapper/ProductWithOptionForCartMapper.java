package com.harbe.productservice.dto.mapper;

import com.harbe.productservice.dto.response.ProductWithOptionForCartDto;
import com.harbe.productservice.entity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductWithOptionForCartMapper {

    private final ModelMapper mapper;
    public ProductWithOptionForCartDto mapToProductOptionDto(Product product){
        ProductWithOptionForCartDto productWithOptionDto = mapper.map(product, ProductWithOptionForCartDto.class);
        return productWithOptionDto;
    }
}
