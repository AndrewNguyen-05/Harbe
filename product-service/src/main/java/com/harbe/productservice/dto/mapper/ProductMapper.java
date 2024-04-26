package com.harbe.productservice.dto.mapper;

import com.harbe.productservice.dto.model.ProductDto;
import com.harbe.productservice.entity.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductMapper {
    private ModelMapper mapper;
    public Product mapToEntity(ProductDto productDto){
        Product product = mapper.map(productDto, Product.class);
        return product;
    }

    public ProductDto mapToDto(Product product){
        ProductDto productDto = mapper.map(product, ProductDto.class);
        return productDto;
    }
}
