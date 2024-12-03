package com.harbe.notificationservice.service;

import com.harbe.notificationservice.dto.model.NotiDto;
import com.harbe.notificationservice.dto.response.NotiResponseDto;
import com.harbe.notificationservice.dto.response.ObjectResponse;

public interface NotiService {
    NotiDto getNotiWithProduct(long productId);
    ObjectResponse<NotiDto> getAllNotifications(int pageNo, int pageSize, String sortBy, String sortDir);
    NotiDto getNotificationById(long id);
    NotiDto createNotification(NotiResponseDto notiDto);
    NotiDto updateNotification(long notiId, NotiResponseDto notiDto);
    void deleteNotification(long id);
    ObjectResponse<NotiDto> searchNoti(String title, int pageNo, int pageSize, String sortBy, String sortDir);

}
