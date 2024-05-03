package com.harbe.cartservice.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CartItemRequest {
    private List<Long> productItemId;
    private Long productId;
    private int quantity;
}
