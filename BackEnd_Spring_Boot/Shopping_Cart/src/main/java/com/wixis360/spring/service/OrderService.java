package com.wixis360.spring.service;

import com.wixis360.spring.dto.CartDTO;
import com.wixis360.spring.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    boolean saveOrder(OrderDTO dto);
    boolean updateOrder(OrderDTO dto);
    OrderDTO searchOrder(String id);
    boolean deleteOrder(String id);
    List<OrderDTO> getAllOrder();
}
