package com.harbe.orderservice.service.impl;

import com.harbe.orderservice.dto.dataObject.ListOrderDto;
import com.harbe.orderservice.dto.dataObject.OrderDto;
import com.harbe.orderservice.dto.response.ProductDto;
import com.harbe.orderservice.repository.OrderRepository;
import com.harbe.orderservice.service.OrderService;
import org.springframework.web.reactive.function.client.WebClient;
import com.harbe.commons.utils.CustomHeaders;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private WebClient webClient;

    @Override
    public OrderDto createOrder(String userId) {
        List<ProductDto> listProductInCart = webClient.get()
                .uri("https://localhost:8080/api/v1/carts")
                .header(CustomHeaders.X_AUTH_USER_ID, userId).retrieve()
                .bodyToFlux(ProductDto.class)
                .collectList().block();

        return null;
    }

    @Override
    public ListOrderDto getAllOrder(int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public OrderDto getOrderById(long id) {
        return null;
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, long id) {
        return null;
    }

    @Override
    public String cancelOrderById(long id) {
        return null;
    }

    @Override
    public String checkoutOrderById(long id) {
        return null;
    }
}
