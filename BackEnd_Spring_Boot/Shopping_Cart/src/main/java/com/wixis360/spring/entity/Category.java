package com.wixis360.spring.entity;

import com.wixis360.spring.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category {
    @Id
    private String categoryId;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Products> products = new ArrayList<>();
}
