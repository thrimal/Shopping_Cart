package com.wixis360.spring.service;

import com.wixis360.spring.dto.CategoryDTO;
import com.wixis360.spring.dto.CustomerDTO;

import java.util.List;

public interface CategoryService {
    boolean saveCategory(CategoryDTO dto);
    boolean updateCategory(CategoryDTO dto);
    CategoryDTO searchCategory(String id);
    boolean deleteCategory(String id);
    List<CategoryDTO> getAllCategories();
}
