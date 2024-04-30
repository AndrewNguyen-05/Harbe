package com.harbe.cartservice.controller;

import com.harbe.cartservice.dto.model.ProductDto;
import com.harbe.cartservice.service.CartRedisService;
import com.harbe.commons.utils.CustomHeaders;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {
    private CartRedisService cartRedisService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProductsFromCart(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) String userId){
        return new ResponseEntity<>(this.cartRedisService.getProductsFromCart(userId), HttpStatus.OK);
    }

    @GetMapping("/info")
    public String getUserInfo(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) String userId,
                              @RequestHeader(CustomHeaders.X_AUTH_USER_AUTHORITIES) String authorities) {
        // Xử lý logic dựa trên thông tin người dùng trong header của yêu cầu
        return "User ID: " + userId + ", Authorities: " + authorities;
    }
}
