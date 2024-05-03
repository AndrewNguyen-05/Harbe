package com.harbe.orderservice.service.impl;

import com.harbe.orderservice.dto.dataObject.ListOrderDto;
import com.harbe.orderservice.dto.dataObject.OrderDto;
import com.harbe.orderservice.dto.response.AddressDto;
import com.harbe.orderservice.dto.response.ProductDto;
import com.harbe.orderservice.repository.OrderRepository;
import com.harbe.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.harbe.commons.utils.CustomHeaders;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private WebClient webClient;

    @Override
    public OrderDto createOrder(long userId) {
        List<ProductDto> listProductInCart = webClient.get()
                .uri("https://localhost:8082/api/v1/carts")
                .header(CustomHeaders.X_AUTH_USER_ID, String.valueOf(userId)).retrieve()
                .bodyToFlux(ProductDto.class)
                .collectList().block();

        AddressDto addressDto = webClient.get()
                .uri("https://localhost:8181/api/v1/address")
                .header(CustomHeaders.X_AUTH_USER_ID, String.valueOf(userId)).retrieve()
                .bodyToMono(AddressDto.class).block();

        System.out.println(listProductInCart);
        System.out.println(addressDto);


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
