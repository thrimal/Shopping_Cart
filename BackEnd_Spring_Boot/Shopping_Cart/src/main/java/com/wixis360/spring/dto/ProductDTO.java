package com.wixis360.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String productId;
    private String description;
    private double price;
    private String categoryId;
    private int qtyOnHand;


}
