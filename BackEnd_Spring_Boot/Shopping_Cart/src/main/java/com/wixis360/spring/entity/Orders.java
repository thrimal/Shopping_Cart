package com.wixis360.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Orders implements Serializable {
    @Id
    private String orderId;
    private String customerId;
    private String date;
    private String status;
    private double total;


    @ManyToOne//(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customerId",referencedColumnName = "customerId",insertable = false,updatable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private List<LineItem> lineItems = new ArrayList<>();
}
