package com.harbe.cartservice.service;

public interface CartService {
    void addToCart(long userId, long productId, long quantity);
}
