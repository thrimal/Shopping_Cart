package com.wixis360.spring.controller;

import com.wixis360.spring.dto.CategoryDTO;
import com.wixis360.spring.dto.ProductDTO;
import com.wixis360.spring.service.CategoryService;
import com.wixis360.spring.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ModelMapper mapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> addCategory(@RequestBody CategoryDTO dto){
        System.out.println("Product controller called");
        try{
            boolean b = categoryService.saveCategory(dto);
            return new ResponseEntity<>(b, HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Boolean> deleteProduct(@RequestParam String id) {
        try{
            boolean b = categoryService.deleteCategory(id);
            return new ResponseEntity<>(b,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> updateCategory(@RequestBody CategoryDTO dto) {
        try{
            boolean b = categoryService.updateCategory(dto);
            return new ResponseEntity<>(b,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity searchCategory(@PathVariable String id) {
        try{
            CategoryDTO categoryDTO = categoryService.searchCategory(id);
            return new ResponseEntity( categoryDTO,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity getAllCategories() {
        try{
            List<CategoryDTO> allCategories = categoryService.getAllCategories();
            return new ResponseEntity(allCategories,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
