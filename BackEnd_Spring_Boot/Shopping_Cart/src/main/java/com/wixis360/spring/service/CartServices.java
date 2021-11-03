package com.wixis360.spring.service;

import com.wixis360.spring.dto.CartDTO;

import java.util.List;

public interface CartServices {
    boolean saveCart(CartDTO dto);
    boolean updateCart(CartDTO dto);
    boolean deleteCart(String id);
    List<CartDTO> getAllCart();
    CartDTO searchCart(String id);
}
