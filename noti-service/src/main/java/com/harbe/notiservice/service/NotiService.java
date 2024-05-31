package com.harbe.notiservice.service;

import com.harbe.notiservice.dto.model.NotiDto;

public interface NotiService {
    NotiDto getNotiWithProduct(long productId);
}
