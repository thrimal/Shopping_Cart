package com.wixis360.spring.entity;

import com.wixis360.spring.dto.CartDTO;
import com.wixis360.spring.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer implements Serializable {
    @Id
    private String customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;
    private String customerPic;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders = new ArrayList<>();

    @OneToMany(mappedBy = "customer1")
    private List<Cart> cartItems;

}


