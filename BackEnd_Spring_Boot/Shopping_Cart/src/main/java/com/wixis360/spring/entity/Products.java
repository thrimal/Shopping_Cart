package com.wixis360.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Products implements Serializable {
    @Id
    private String productId;
    private String description;
    private String categoryId;
    private double price;
    private int qtyOnHand;

    @OneToMany(mappedBy = "products")
    private List<LineItem> lineItems = new ArrayList<>();

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId",referencedColumnName = "categoryId",insertable = false,updatable = false)
    private Category category;
}

