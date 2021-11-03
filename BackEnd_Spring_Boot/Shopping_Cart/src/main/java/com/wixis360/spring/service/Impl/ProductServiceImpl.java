package com.wixis360.spring.service.Impl;

import com.wixis360.spring.dto.ProductDTO;
import com.wixis360.spring.entity.Products;
import com.wixis360.spring.repo.ProductsRepo;
import com.wixis360.spring.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public boolean saveProduct(ProductDTO dto) {
        if (!productsRepo.existsById(dto.getProductId())) {
            System.out.println("Call Product service");
            Products products = mapper.map(dto, Products.class);
            productsRepo.save(products);
            return true;
        } else {
            throw new RuntimeException("Products already exist..!");
        }
    }

    @Override
    public boolean updateProduct(ProductDTO dto) {
        if (productsRepo.existsById(dto.getProductId())) {
            Products products = mapper.map(dto, Products.class);
            productsRepo.save(products);
            return true;
        } else {
            throw new RuntimeException("No such Product for update..!");
        }
    }

    @Override
    public ProductDTO searchProduct(String id) {
        Optional<Products> products = productsRepo.findById(id);
        if (products.isPresent()) {
            return mapper.map(products.get(), ProductDTO.class);
        } else {
            throw new RuntimeException("No Product for id: " + id);
        }
    }

    @Override
    public boolean deleteProduct(String id) {
        if (productsRepo.existsById(id)) {
            productsRepo.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("No Product for delete ID: " + id);
        }
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Products> all = productsRepo.findAll();
        return mapper.map(all, new TypeToken<List<ProductDTO>>() {
        }.getType());
    }
}
