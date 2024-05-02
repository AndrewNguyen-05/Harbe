package com.harbe.cartservice.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartDeletionRequest {
    private List<Long> productItemId;
    private Long productId;
}
