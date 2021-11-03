package com.wixis360.spring.repo;

import com.wixis360.spring.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders,String> {
}
