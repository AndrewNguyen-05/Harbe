package com.harbe.productservice.service;

import com.harbe.commons.response.ObjectResponse;
import com.harbe.productservice.dto.model.ProductDto;
import com.harbe.productservice.dto.response.ProductWithOptionDto;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ObjectResponse<ProductDto> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);
    ProductDto getProductById(Long id);
    ProductDto updateProduct(ProductDto productDto, Long productId);
    void deleteProduct(Long productId);

    ProductWithOptionDto getProductByProductOptionId(long productOptionId);
}
