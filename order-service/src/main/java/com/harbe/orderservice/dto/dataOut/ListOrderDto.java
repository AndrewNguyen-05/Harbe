package com.harbe.orderservice.dto.dataOut;

import lombok.Data;

import java.util.List;

@Data
public class ListOrderDto {
    private List<OrderDto> orderList;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
