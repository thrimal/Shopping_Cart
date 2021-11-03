package com.wixis360.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class LineItem implements Serializable {
    @Id
    private int lineItemId;
    private int quantity;
    private double price;
    private String orderId;
    private String itemCode;
    private String cartId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId", referencedColumnName = "orderId",insertable = false,updatable = false)
    private Orders orders;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "productId",insertable = false,updatable = false)
    private Products products;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId", referencedColumnName = "cartId",insertable = false,updatable = false)
    private Cart cart;

}
