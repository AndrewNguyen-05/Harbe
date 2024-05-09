package com.harbe.orderservice.service;

import com.harbe.orderservice.dto.dataOut.CreateOrderResultDto;
import com.harbe.orderservice.dto.dataOut.ListOrderDto;
import com.harbe.orderservice.dto.dataOut.OrderDto;
import com.harbe.orderservice.dto.dataIn.OrderBasicInfoDto;

public interface OrderService {
    CreateOrderResultDto createOrder(long userId, OrderBasicInfoDto orderBasicInfoDto);
    ListOrderDto getAllOrder(int pageNo, int pageSize, String sortBy, String sortDir);
    OrderDto getOrderById(long id);
    OrderDto updateOrder(OrderDto orderDto, long id);
    String cancelOrderById(long id);
    String checkoutOrderById(long id);


}
