package com.wixis360.spring.repo;

import com.wixis360.spring.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Products,String> {
}
