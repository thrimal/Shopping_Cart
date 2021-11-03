package com.wixis360.spring.controller;

import com.wixis360.spring.dto.ProductDTO;
import com.wixis360.spring.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ModelMapper mapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> addProduct(@RequestBody ProductDTO dto){
        System.out.println("Product controller called");
        try{
            boolean b = productService.saveProduct(dto);
            return new ResponseEntity<>(b, HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Boolean> deleteProduct(@RequestParam String id) {
        try{
            boolean b = productService.deleteProduct(id);
            return new ResponseEntity<>(b,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> updateProduct(@RequestBody ProductDTO dto) {
        try{
            boolean b = productService.updateProduct(dto);
            return new ResponseEntity<>(b,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity searchProduct(@PathVariable String id) {
        try{
            ProductDTO productDTO = productService.searchProduct(id);
            return new ResponseEntity( productDTO,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        try{
            List<ProductDTO> allProducts = productService.getAllProducts();
            return new ResponseEntity(allProducts,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
