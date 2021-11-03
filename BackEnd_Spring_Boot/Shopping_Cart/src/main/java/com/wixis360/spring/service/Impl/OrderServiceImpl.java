package com.wixis360.spring.service.Impl;

import com.wixis360.spring.dto.OrderDTO;
import com.wixis360.spring.entity.Orders;
import com.wixis360.spring.repo.*;
import com.wixis360.spring.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private LineItemRepo lineItemRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public boolean saveOrder(OrderDTO dto) {
        if (customerRepo.existsById(dto.getCustomerId())) {
            boolean b = customerRepo.existsById(dto.getCustomerId());
            System.out.println(b);
            if (!orderRepo.existsById(dto.getOrderId())) {
                Orders order = mapper.map(dto, Orders.class);
                orderRepo.save(order);
                return true;
            }else{
                throw new RuntimeException("Plz add Order .....?");
            }
        }else {
            throw new RuntimeException("Plz add Customer .....?");
        }
    }


    @Override
    public boolean updateOrder(OrderDTO dto) {
        if (orderRepo.existsById(dto.getOrderId())) {
            Orders map = mapper.map(dto, Orders.class);
            orderRepo.save(map);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public OrderDTO searchOrder(String id) {
        Optional<Orders> order= orderRepo.findById(id);
        if (order.isPresent()) {
            return mapper.map(order.get(), OrderDTO.class);
        } else {
            throw new RuntimeException("No Order for id: " + id);
        }
    }

    @Override
    public boolean deleteOrder(String id) {
        if (orderRepo.existsById(id)) {
            lineItemRepo.deleteByOrderId(id);
            orderRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<OrderDTO> getAllOrder() {
        List<Orders> all = orderRepo.findAll();
        return mapper.map(all, new TypeToken<List<OrderDTO>>() {
        }.getType());
    }

}
