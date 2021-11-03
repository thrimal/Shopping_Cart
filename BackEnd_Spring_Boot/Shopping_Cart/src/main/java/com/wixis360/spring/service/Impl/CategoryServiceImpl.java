package com.wixis360.spring.service.Impl;

import com.wixis360.spring.dto.CategoryDTO;
import com.wixis360.spring.dto.ProductDTO;
import com.wixis360.spring.entity.Category;
import com.wixis360.spring.entity.Products;
import com.wixis360.spring.repo.CategoryRepo;
import com.wixis360.spring.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public boolean saveCategory(CategoryDTO dto) {
        if (!categoryRepo.existsById(dto.getCategoryId())) {
            System.out.println("Call Product service");
            Category  category= mapper.map(dto, Category.class);
            categoryRepo.save(category);
            return true;
        } else {
            throw new RuntimeException("Category already exist..!");
        }
    }

    @Override
    public boolean updateCategory(CategoryDTO dto) {
        if (categoryRepo.existsById(dto.getCategoryId())) {
            Category  category = mapper.map(dto, Category.class);
            categoryRepo.save(category);
            return true;
        } else {
            throw new RuntimeException("No such Category for update..!");
        }
    }

    @Override
    public CategoryDTO searchCategory(String id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isPresent()) {
            return mapper.map(category.get(), CategoryDTO.class);
        } else {
            throw new RuntimeException("No Category for id: " + id);
        }
    }

    @Override
    public boolean deleteCategory(String id) {
        if (categoryRepo.existsById(id)) {
            categoryRepo.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("No  Category for delete ID: " + id);
        }
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> all = categoryRepo.findAll();
        return mapper.map(all, new TypeToken<List<CategoryDTO>>() {
        }.getType());
    }
}
