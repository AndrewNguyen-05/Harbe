package com.harbe.orderservice.dto.dataIn;

import lombok.Data;

@Data
public class PaypalAuthDto {
    public String scope;
    public String access_token;
    public String token_type;
    public String app_id;
    public int expires_in;
    public String nonce;
}
