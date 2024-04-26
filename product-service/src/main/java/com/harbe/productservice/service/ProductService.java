package com.harbe.productservice.service;

import com.harbe.productservice.dto.message.ProductResponse;
import com.harbe.productservice.dto.model.ProductDto;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductResponse getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);
    ProductDto getProductById(Long id);
    ProductDto updateProduct(ProductDto productDto, Long productId);
    void deleteProduct(Long productId);
}
