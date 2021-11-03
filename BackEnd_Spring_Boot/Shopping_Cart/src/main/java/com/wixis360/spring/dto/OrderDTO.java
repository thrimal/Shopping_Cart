package com.wixis360.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String orderId;
    private String customerId;
    private String date;
    private String status;
    private double total;
    private List<LineItemDTO> lineItemDTOS;
}
