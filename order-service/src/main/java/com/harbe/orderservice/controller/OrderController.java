package com.harbe.orderservice.controller;


import com.harbe.orderservice.dto.dataIn.OrderBasicInfoDto;
import com.harbe.orderservice.dto.dataOut.CreateOrderResultDto;
import com.harbe.orderservice.service.OrderService;
import com.harbe.orderservice.utils.CustomHeaders;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
     private OrderService orderService;

     @PostMapping
     public ResponseEntity<CreateOrderResultDto> createOrder(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) long userId,
                                                             @RequestBody OrderBasicInfoDto orderBasicInfoDto) {

         return new ResponseEntity<>(orderService.createOrder(userId, orderBasicInfoDto), HttpStatus.OK);
     }

}
