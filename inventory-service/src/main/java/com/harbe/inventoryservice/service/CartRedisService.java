package com.harbe.inventoryservice.service;

import com.harbe.inventoryservice.dto.model.ProductDto;
import com.harbe.inventoryservice.dto.request.CartItemRequest;
import com.harbe.inventoryservice.dto.request.ProductCartDeletionRequest;
import com.harbe.inventoryservice.dto.request.UpdateCartRequest;
import com.harbe.inventoryservice.service.base.BaseRedisService;

import java.util.List;

public interface CartRedisService extends BaseRedisService {
    void addProductToCart(String userId, CartItemRequest item);
    void updateProductInCart(String userId, UpdateCartRequest item);
    void deleteProductInCart(String userId, ProductCartDeletionRequest request);
    void deleteAllProductsInCart(String userId);
    List<ProductDto> getProductsFromCart(String userId);
}
