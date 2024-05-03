package com.harbe.orderservice.service;

import com.harbe.orderservice.dto.dataObject.ListOrderDto;
import com.harbe.orderservice.dto.dataObject.OrderDto;

public interface OrderService {
    OrderDto createOrder(String userId);
    ListOrderDto getAllOrder(int pageNo, int pageSize, String sortBy, String sortDir);
    OrderDto getOrderById(long id);
    OrderDto updateOrder(OrderDto orderDto, long id);
    String cancelOrderById(long id);
    String checkoutOrderById(long id);


}
