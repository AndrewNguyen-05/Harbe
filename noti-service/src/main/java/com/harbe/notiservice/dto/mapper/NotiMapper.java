package com.harbe.notiservice.dto.mapper;

import com.harbe.notiservice.dto.model.NotiDto;
import com.harbe.notiservice.dto.response.NotiResponseDto;
import com.harbe.notiservice.entity.Notification;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotiMapper {
    private ModelMapper mapper;

    public NotiDto mapToDto(Notification notification){
        NotiDto notiDto = mapper.map(notification, NotiDto.class);
        return notiDto;
    }

    public Notification mapToEntity(NotiDto notiDto){
        Notification notification = mapper.map(notiDto, Notification.class);
        return notification;
    }

    public NotiResponseDto mapToResponseDto(Notification notification){
        NotiResponseDto notiResponseDto = mapper.map(notification, NotiResponseDto.class);
        return notiResponseDto;
    }

    public Notification mapToResponseEntity(NotiResponseDto notiResponseDto){
        Notification notification = mapper.map(notiResponseDto, Notification.class);
        return notification;
    }
}
