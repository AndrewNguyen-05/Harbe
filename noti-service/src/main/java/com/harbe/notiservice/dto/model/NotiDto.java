package com.harbe.notiservice.dto.model;

import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class NotiDto {
    private long id;
    private String message;
    private String title;
    private Date timestamp;
}
