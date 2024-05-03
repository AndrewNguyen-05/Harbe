package com.harbe.orderservice.dto.mapper;

import com.harbe.orderservice.dto.dataObject.OrderDto;
import com.harbe.orderservice.entity.Order;
import org.modelmapper.ModelMapper;

public class OrderMapper {

    private ModelMapper mapper;

    private OrderDto mapToDto(Order order){
        OrderDto orderDto = mapper.map(order, OrderDto.class);
        return orderDto;
    }

    private Order mapToEntity(OrderDto orderDto){
        Order order = mapper.map(orderDto, Order.class);
        return order;
    }
}
