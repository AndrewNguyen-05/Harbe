package com.harbe.cartservice.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartDeletionRequest {
    private Long productItemId;
    private Long productId;
}
