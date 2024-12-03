package com.harbe.productservice.dto.mapper;

import com.harbe.productservice.dto.message.ProductDetailResponseDto;
import com.harbe.productservice.dto.message.ProductResponseDto;
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

    public Product mapToResponseEntity(ProductResponseDto productResponseDto){
        Product product = mapper.map(productResponseDto, Product.class);
        return product;
    }

    public ProductResponseDto mapToResponseDto(Product product){
        ProductResponseDto productResponseDto = mapper.map(product, ProductResponseDto.class);
        return productResponseDto;
    }

    public Product mapToResponseDetailEntity(ProductDetailResponseDto productDetailResponseDto){
        Product product = mapper.map(productDetailResponseDto, Product.class);
        return product;
    }

    public ProductDetailResponseDto mapToResponseDetailDto(Product product){
        ProductDetailResponseDto productDetailResponseDto = mapper.map(product, ProductDetailResponseDto.class);
        return productDetailResponseDto;
    }
}
