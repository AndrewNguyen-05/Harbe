package com.harbe.orderservice.dto.dataIn;

import lombok.Data;

@Data
public class OrderBasicInfoDto {
    private long addressId;
    private String note;
    private String paymentMethod; //PAYPAL, COD
    private String returnUrl; // for paypal success payment FE
    private String cancelUrl; // for paypal cancel payment FE
}
