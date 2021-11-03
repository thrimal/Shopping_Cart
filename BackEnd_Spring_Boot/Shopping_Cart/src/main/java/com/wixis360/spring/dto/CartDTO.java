package com.wixis360.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private String cartId;
    private String customerId;
    private List<LineItemDTO> lineItemDTOS;
}
