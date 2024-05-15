package com.harbe.cartservice.service;

import com.harbe.cartservice.dto.request.CartItemRequest;
import com.harbe.cartservice.dto.request.ProductCartDeletionRequest;
import com.harbe.cartservice.dto.request.UpdateCartRequest;
import com.harbe.cartservice.dto.model.ProductDto;
import com.harbe.cartservice.service.base.BaseRedisService;

import java.util.List;

public interface CartRedisService extends BaseRedisService {
    void addProductToCart(String userId, CartItemRequest item);
    void updateProductInCart(String userId, UpdateCartRequest item);
    void deleteProductInCart(String userId, ProductCartDeletionRequest request);
    void deleteAllProductsInCart(String userId);
    List<ProductDto> getProductsFromCart(String userId);
}
