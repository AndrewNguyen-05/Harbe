package com.harbe.orderservice.dto.dataIn.paypalOrderCreate;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PaypalOrderCreateDto {
    public String id;
    public String status;
    public ArrayList<PaypalOrderLink> links;
}
