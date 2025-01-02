package com.harbe.orderservice.dto.dataIn.paypalOrderCreate;

import lombok.Data;

@Data
public class PaypalOrderLink {
    public String href;
    public String rel;
    public String method;
}
