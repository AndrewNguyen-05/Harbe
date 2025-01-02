package com.harbe.orderservice.dto.dataOut;

import lombok.Data;

@Data
public class CreateOrderResultDto {
    // CREATED (for COD), PENDING_PAYPAL, NEED_RETURN_URL (for paypal result)
    private String result;
    private String paypalLink;
    private OrderDto orderDto;
}
