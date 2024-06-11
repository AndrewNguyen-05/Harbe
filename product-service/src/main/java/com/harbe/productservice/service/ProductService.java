package com.harbe.productservice.service;

import com.harbe.productservice.dto.message.ProductDetailResponseDto;
import com.harbe.productservice.dto.message.ProductResponseDto;
import com.harbe.productservice.dto.response.ObjectResponse;
import com.harbe.productservice.dto.model.ProductDto;
import com.harbe.productservice.dto.response.ProductWithOptionForCartDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ObjectResponse<ProductDto> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);
    ProductDetailResponseDto getProductById(Long id);
    ProductDto updateProduct(ProductDto productDto, Long productId);
    void deleteProduct(Long productId);

    ProductWithOptionForCartDto getProductByProductOptionId(String productOptionId);
    ObjectResponse<ProductDto> searchProduct(String name, int pageNo, int pageSize, String sortBy, String sortDir);
}
