package com.harbe.notificationservice.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NotiResponseDto {

    @NotEmpty(message = "Message should not be empty")
    @Size(min = 10, message = "Notification message should have at least 2 characters!")
    private String message;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, message = "Notification title should have at least 2 characters!")
    private String title;
}
