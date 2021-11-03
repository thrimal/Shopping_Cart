package com.wixis360.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItemDTO {
    private int lineItemId;
    private int quantity;
    private double price; //my
    private String orderId;
    private String itemCode;
    private String cartId;
}
