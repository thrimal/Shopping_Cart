package com.wixis360.spring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cart implements Serializable {
    @Id
    private String cartId;
    private String customerId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId",referencedColumnName = "customerId",insertable = false,updatable = false)
    private Customer customer1;

    @OneToMany(mappedBy = "cart")
    private List<LineItem> lineItems = new ArrayList<>();
}
