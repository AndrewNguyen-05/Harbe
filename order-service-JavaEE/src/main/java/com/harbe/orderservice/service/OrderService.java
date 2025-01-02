package com.harbe.orderservice.service;

import com.harbe.orderservice.dto.dataIn.OrderBasicInfoDto;
import com.harbe.orderservice.dto.dataOut.CancelOrderResult;
import com.harbe.orderservice.dto.dataOut.CreateOrderResultDto;
import com.harbe.orderservice.dto.dataOut.ListOrderDto;
import com.harbe.orderservice.dto.dataOut.OrderDto;

public interface OrderService {
    CreateOrderResultDto createOrder(long userId, OrderBasicInfoDto orderBasicInfoDto);
    CreateOrderResultDto capturePaypalOrder(long orderId);
    CancelOrderResult cancelCapturePaypal(long orderId);
    ListOrderDto getAllOrder(int pageNo, int pageSize, String sortBy, String sortDir);
    OrderDto getOrderById(long id);
    OrderDto updateOrder(OrderDto orderDto, long id);
    String cancelOrderById(long id);

}
