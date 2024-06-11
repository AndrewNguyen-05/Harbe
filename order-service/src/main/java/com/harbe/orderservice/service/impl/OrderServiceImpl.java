package com.harbe.orderservice.service.impl;

import com.harbe.orderservice.constant.PaypalConstants;
import com.harbe.orderservice.dto.dataOut.CancelOrderResult;
import com.harbe.orderservice.dto.dataOut.CreateOrderResultDto;
import com.harbe.orderservice.dto.dataOut.ListOrderDto;
import com.harbe.orderservice.dto.dataOut.OrderDto;
import com.harbe.orderservice.dto.dataIn.PaypalAuthDto;
import com.harbe.orderservice.dto.dataIn.paypalOrderCreate.PaypalOrderCreateDto;
import com.harbe.orderservice.dto.dataIn.AddressDto;
import com.harbe.orderservice.dto.dataIn.OrderBasicInfoDto;
import com.harbe.orderservice.dto.dataIn.ProductDto;
import com.harbe.orderservice.dto.mapper.OrderMapper;
import com.harbe.orderservice.entity.Order;
import com.harbe.orderservice.exception.HarbeAPIException;
import com.harbe.orderservice.exception.OrderExceptionHandler;
import com.harbe.orderservice.exception.PaypalAccessTokenException;
import com.harbe.orderservice.exception.ResourceNotFoundException;
import com.harbe.orderservice.repository.OrderRepository;
import com.harbe.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.json.JSONObject;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import com.harbe.orderservice.utils.CustomHeaders;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private WebClient webClient;
    private OrderMapper orderMapper;

    @Override
    public CreateOrderResultDto createOrder(long userId, OrderBasicInfoDto orderBasicInfoDto) {
//        List<AddressDto> addressDto = webClient.get()
//                .uri("http://localhost:8181/address")
//                .header(CustomHeaders.X_AUTH_USER_ID, String.valueOf(userId))
//                .retrieve()
//                .bodyToFlux(AddressDto.class)
//                .collectList().block();

        List<ProductDto> listProductInCart = webClient.get()
                .uri("http://localhost:8082/api/v1/carts")
                .header(CustomHeaders.X_AUTH_USER_ID, String.valueOf(userId))
                .retrieve()
                .bodyToFlux(ProductDto.class)
                .collectList().block();

//        System.out.println(listProductInCart);
//        System.out.println(addressDto);

        Order order = new Order();
        CreateOrderResultDto cord = new CreateOrderResultDto();


        double value = 0;
        for (ProductDto productDto: listProductInCart) {
            value += productDto.getPrice() * ((100 - productDto.getDiscountRate()) / 100) * productDto.getQuantity();
        }
        value = Math.ceil(value);

        order.setStatus("PREPARING");
        order.setShippingFee(20000);
        order.setTotal(value + order.getShippingFee());
        order.setNote(orderBasicInfoDto.getNote());
        order.setCreatedAt(LocalDateTime.now());
        order.setAddressId(orderBasicInfoDto.getAddressId());
        order.setUserId(userId);

        Order orderResult = null;
        switch (orderBasicInfoDto.getPaymentMethod()) {
            case "PAYPAL" : {
                if (orderBasicInfoDto.getReturnUrl().isBlank() || orderBasicInfoDto.getCancelUrl().isBlank()){
                    cord.setResult("NEED_RETURN_AND_CANCEL_URL");
                    return cord;
                }
                order.setPaymentMethod("PAYPAL");
                order.setPaymentStatus("PENDING");
                PaypalOrderCreateDto pocd = createPaypalOrder(orderBasicInfoDto, value);
                cord.setPaypalLink(pocd.links.stream()
                        .filter(paypalOrderLink -> {
                    return  paypalOrderLink.rel.equals("payer-action");
                        })
                        .collect(Collectors.toList())
                        .get(0).getHref());
                cord.setResult("PENDING_PAYPAL");
                order.setPaypalId(pocd.id);
                orderResult = orderRepository.save(order);
            }
            break;
            case "COD" : {
                order.setPaymentMethod("COD");
                order.setPaymentStatus("PENDING");
                cord.setResult("CREATED");
                orderResult = orderRepository.save(order);
            }
            break;
            default: {
                cord.setResult("NO_PAYMENT_METHOD_SELECTED");
                return cord;
            }
        }

        cord.setOrderDto(orderMapper.mapToDto(orderResult));

        return cord;
    }

    @Override
    public CreateOrderResultDto capturePaypalOrder(long orderId) {
        CreateOrderResultDto cord = new CreateOrderResultDto();

        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Order", "Id", orderId));
        if (order.getPaypalId() == null) {
            cord.setResult("FAILED");
            return cord;
        }
        PaypalOrderCreateDto pocd = webClient.post()
                .uri(PaypalConstants.BASE_URI + "/v2/checkout/orders/" +
                        order.getPaypalId() + "/capture")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(header -> header.setBearerAuth(getAccessToken()))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error( new HarbeAPIException(HttpStatus.FORBIDDEN, "Can't capture order") )
                )
                .bodyToMono(PaypalOrderCreateDto.class)
                .block();

        if (pocd.status.equals("COMPLETED")){
            order.setPaymentStatus("COMPLETED");
            orderRepository.save(order);
            cord.setOrderDto(orderMapper.mapToDto(order));
            cord.setResult("CAPTURED");
        }
        else {
            cord.setResult("FAILED");
        }
        return cord;
    }

    @Override
    public CancelOrderResult cancelCapturePaypal(long orderId) {
        orderRepository.deleteById(orderId);
        CancelOrderResult cor = new CancelOrderResult();
        cor.setMessage("Canceled");
        cor.setSuccess(true);
        return cor;
    }

    public PaypalOrderCreateDto createPaypalOrder(OrderBasicInfoDto orderBasicInfoDto, double vndValue) {
        double value = vndValue / 25000;
        final double fee = 0.05;
        value = value * (1 + fee);
        String returnUrl = orderBasicInfoDto.getReturnUrl();
        String cancelUrl = orderBasicInfoDto.getCancelUrl();
        String ppRequest =
                String.format("""
                {
                    "intent": "CAPTURE",
                    "purchase_units": [
                        {
                            "amount": {
                                "currency_code": "USD",
                                "value": "%.2f"
                            }
                        }
                    ],
                    "payment_source": {
                        "paypal": {
                            "experience_context": {
                                "payment_method_preference": "IMMEDIATE_PAYMENT_REQUIRED",
                                "brand_name": "HARBE",
                                "locale": "vi-VN",
                                "landing_page": "LOGIN",
                                "user_action": "PAY_NOW",
                                "return_url": "%s",
                                "cancel_url": "%s"
                            }
                        }
                    }
                }"""
                        , value, returnUrl, cancelUrl);

        System.out.println(ppRequest);

        JSONObject ppNewOrder = new JSONObject(ppRequest);
        System.out.println("--------");
        System.out.println(ppNewOrder);
        PaypalOrderCreateDto orderCreateDto = null;
        try{
            orderCreateDto = webClient.post()
                    .uri(PaypalConstants.BASE_URI + "/v2/checkout/orders")
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(header -> header.setBearerAuth(getAccessToken()))
                    .body(BodyInserters.fromValue(ppNewOrder.toString()))
                    .retrieve()
                    .bodyToMono(PaypalOrderCreateDto.class)
                    .block();

        } catch (WebClientResponseException e) {
            System.out.println(e);
            System.out.println(e.getResponseBodyAsString());
        }

        System.out.println(orderCreateDto);

        return orderCreateDto;
    }

    @Override
    public ListOrderDto getAllOrder(int pageNo, int pageSize, String sortBy, String sortDir) {
        Page<Order> page = orderRepository.findAll(PageRequest.of(pageNo, pageSize,
                sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending()));
        ListOrderDto lod = new ListOrderDto();
        lod.setOrderList(page.getContent().stream()
                        .map(order -> orderMapper.mapToDto(order))
                        .collect(Collectors.toList()));
        lod.setLast(page.isLast());
        lod.setPageNo(page.getNumber());
        lod.setPageSize(page.getSize());
        lod.setTotalElements(page.getTotalElements());
        lod.setTotalPages(page.getTotalPages());
        return lod;
    }

    @Override
    public OrderDto getOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        return orderMapper.mapToDto(order);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        order.setTotal(orderDto.getTotal());
        order.setStatus(orderDto.getStatus());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setPaymentStatus(orderDto.getPaymentStatus());
        order.setShippingFee(orderDto.getShippingFee());
        order.setNote(orderDto.getNote());
        order.setCreatedAt(orderDto.getCreatedAt());
        order.setAddressId(orderDto.getAddressId());
        order.setUserId(orderDto.getUserId());
        order.setPaypalId(orderDto.getPaypalId());
        Order orderResult = orderRepository.save(order);
        return orderMapper.mapToDto(orderResult);
    }

    @Override
    public String cancelOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        order.setStatus("CANCELED");
        Order orderResult = orderRepository.save(order);
        return "Order canceled successfully";
    }

    private String getAccessToken(){
        PaypalAuthDto result = webClient.post()
                .uri(PaypalConstants.BASE_URI + "/v1/oauth2/token")
                .headers(headers
                        -> headers.setBasicAuth(PaypalConstants.CLIENT_ID, PaypalConstants.CLENT_SECRET))
                .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
                .retrieve()
                .bodyToMono(PaypalAuthDto.class)
                .block();
        if (result.access_token.isEmpty())
            throw new PaypalAccessTokenException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Can't get access token from paypal client_id/secret");
        return result.access_token;
    }
}
