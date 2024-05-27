package com.harbe.orderservice.controller;


import com.harbe.orderservice.dto.dataIn.OrderBasicInfoDto;
import com.harbe.orderservice.dto.dataOut.*;
import com.harbe.orderservice.service.OrderService;
import com.harbe.orderservice.utils.AppConstants;
import com.harbe.orderservice.utils.CustomHeaders;
import jakarta.validation.Valid;
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

     @PostMapping("/paypal_capture/{orderId}")
     public ResponseEntity<CreateOrderResultDto> capturePaypalOrder(@PathVariable long orderId) {
         return new ResponseEntity<>(orderService.capturePaypalOrder(orderId), HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable long orderId, @RequestBody @Valid OrderDto orderDto){
        return new ResponseEntity<>(orderService.updateOrder(orderDto, orderId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListOrderDto> getAllOrder(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return new ResponseEntity<>(orderService.getAllOrder(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable long orderId){
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }


    @DeleteMapping("/paypal_capture/{orderId}")
    public ResponseEntity<CancelOrderResult> deleteCapturePaypalOrder(@PathVariable long orderId) {
        return new ResponseEntity<>(orderService.cancelCapturePaypal(orderId), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable long orderId) {
         return new ResponseEntity<>(orderService.cancelOrderById(orderId), HttpStatus.OK);
    }




}
