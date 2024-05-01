package com.harbe.cartservice.service;

import com.harbe.cartservice.dto.Request.CartItemRequest;
import com.harbe.cartservice.dto.Request.ProductCartDeletionRequest;
import com.harbe.cartservice.dto.model.ProductDto;
import com.harbe.cartservice.service.base.BaseRedisService;

import java.util.List;

public interface CartRedisService extends BaseRedisService {
    void addProductToCart(String userId, CartItemRequest item);
    void updateProductInCart(String userId, CartItemRequest items);
    void deleteProductInCart(String userId, ProductCartDeletionRequest request);
    List<ProductDto> getProductsFromCart(String userId);
}
