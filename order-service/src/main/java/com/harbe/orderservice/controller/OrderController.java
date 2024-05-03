package com.harbe.orderservice.controller;


import com.harbe.commons.utils.CustomHeaders;
import com.harbe.orderservice.dto.dataObject.OrderDto;
import com.harbe.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
     private OrderService orderService;

     @PostMapping
     public ResponseEntity<String> createOrder(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) long userId) {
         orderService.createOrder(userId);
         return new ResponseEntity<>("a"
                 , HttpStatus.OK);
     }

}
