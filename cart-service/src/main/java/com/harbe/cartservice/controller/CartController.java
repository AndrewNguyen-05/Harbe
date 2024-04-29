package com.harbe.cartservice.controller;

import com.harbe.cartservice.service.CartService;
import com.harbe.commons.utils.CustomHeaders;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {
    private CartService cartService;

    @GetMapping
    public String helloWorldCart(){
        return "Hello World Cart Services";
    }

    @GetMapping("/info")
    public String getUserInfo(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) String userId,
                              @RequestHeader(CustomHeaders.X_AUTH_USER_AUTHORITIES) String authorities) {
        // Xử lý logic dựa trên thông tin người dùng trong header của yêu cầu
        return "User ID: " + userId + ", Authorities: " + authorities;
    }
}
