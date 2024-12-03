package com.harbe.inventoryservice.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateCartRequest {
    private List<Long> productItemId;
    private Long productId;
    private long delta;
}
