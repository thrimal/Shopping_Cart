package com.wixis360.spring.repo;

import com.wixis360.spring.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,String> {
}
