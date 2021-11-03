package com.wixis360.spring.repo;

import com.wixis360.spring.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,String> {
}
