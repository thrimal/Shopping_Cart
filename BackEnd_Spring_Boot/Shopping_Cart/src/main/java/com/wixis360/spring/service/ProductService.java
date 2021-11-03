package com.wixis360.spring.service;

import com.wixis360.spring.dto.CustomerDTO;
import com.wixis360.spring.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    boolean saveProduct(ProductDTO dto);
    boolean updateProduct(ProductDTO dto);
    ProductDTO searchProduct(String id);
    boolean deleteProduct(String id);
    List<ProductDTO> getAllProducts();
}
