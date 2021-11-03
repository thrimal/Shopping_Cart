package com.wixis360.spring.service.Impl;

import com.wixis360.spring.dto.CartDTO;

import com.wixis360.spring.dto.LineItemDTO;
import com.wixis360.spring.entity.*;


import com.wixis360.spring.repo.CartRepo;
import com.wixis360.spring.repo.CustomerRepo;

import com.wixis360.spring.repo.LineItemRepo;
import com.wixis360.spring.repo.ProductsRepo;
import com.wixis360.spring.service.CartServices;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServicesImpl implements CartServices {

    @Autowired
    private LineItemRepo lineItemRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public boolean saveCart(CartDTO dto) {
        if (customerRepo.existsById(dto.getCustomerId())) {
            boolean b = customerRepo.existsById(dto.getCustomerId());
            System.out.println(b);
            if (!cartRepo.existsById(dto.getCartId())) {
                Cart cart = mapper.map(dto, Cart.class);
                cartRepo.save(cart);
                if (dto.getLineItemDTOS().size() > 0) {
                    for (LineItemDTO lineItemDTO : dto.getLineItemDTOS()) {
                        System.out.println(dto.getLineItemDTOS());
                        LineItem lineItem = new LineItem();
                        lineItem.setLineItemId(lineItemDTO.getLineItemId());//my
                        lineItem.setCartId(lineItemDTO.getCartId());
                        lineItem.setQuantity(lineItemDTO.getQuantity());
                        lineItem.setPrice(lineItemDTO.getPrice()); // my
                        lineItem.setOrderId(lineItemDTO.getOrderId());
                        lineItem.setItemCode(lineItemDTO.getItemCode());
                        lineItem.setQuantity(lineItemDTO.getQuantity());
                        lineItemRepo.save(lineItem);
                        Products products = productsRepo.getOne(lineItemDTO.getItemCode());
                        int qty = products.getQtyOnHand() - lineItemDTO.getQuantity();
                        products.setQtyOnHand(qty);

                    }
                    return true;
                } else {
                    throw new RuntimeException("Plz check Cart Details .....?");
                }
            }else{
                throw new RuntimeException("Plz add Cart .....?");
            }
        }else {
            throw new RuntimeException("Plz add Customer .....?");
        }
    }

    @Override
    public boolean updateCart(CartDTO dto) {
        if (cartRepo.existsById(dto.getCartId())) {
            Cart c = mapper.map(dto, Cart.class);
            cartRepo.save(c);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public CartDTO searchCart(String id) {
        Optional<Cart> c = cartRepo.findById(id);
        if (c.isPresent()) {
            return mapper.map(c.get(), CartDTO.class);
        } else {
            throw new RuntimeException("No customer for id: " + id);
        }
    }


    @Override
    public boolean deleteCart(String id) {
        if (cartRepo.existsById(id)){
            cartRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<CartDTO> getAllCart() {
        List<Cart> all = cartRepo.findAll();
        return mapper.map(all, new TypeToken<List<CartDTO>>() {
        }.getType());
    }

}
