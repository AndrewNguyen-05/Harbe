package com.harbe.cartservice.service.impl;

import com.harbe.cartservice.repository.CartItemRepository;
import com.harbe.cartservice.repository.CartRepository;
import com.harbe.cartservice.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;

    @Override
    public void addToCart(long userId, long productId, long quantity){

    }
}
