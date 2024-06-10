package com.harbe.notiservice.service;

import com.harbe.notiservice.dto.model.NotiDto;
import com.harbe.notiservice.dto.response.NotiResponseDto;
import com.harbe.notiservice.dto.response.ObjectResponse;

public interface NotiService {
    NotiDto getNotiWithProduct(long productId);
    ObjectResponse<NotiDto> getAllNotifications(int pageNo, int pageSize, String sortBy, String sortDir);
    NotiDto getNotificationById(long id);
    NotiDto createNotification(NotiResponseDto notiDto);
    NotiDto updateNotification(long notiId, NotiResponseDto notiDto);
    void deleteNotification(long id);
}
