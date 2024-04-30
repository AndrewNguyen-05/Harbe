package com.harbe.cartservice.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CartItemRequest {
    private Long productItemId;
    private Long productId;
    private int quantity;
}
