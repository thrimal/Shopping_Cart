package com.wixis360.spring.controller;

import com.wixis360.spring.dto.OrderDTO;
import com.wixis360.spring.service.CartServices;
import com.wixis360.spring.service.LineItemService;
import com.wixis360.spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartServices cartServices;

    @Autowired
    private LineItemService lineItemService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> addOrder(@RequestBody OrderDTO dto){
//        System.out.println(dto);
//        System.out.println("order controller called");
//        System.out.println("order controller id "+dto.getOrderId());
        try {
            boolean b = orderService.saveOrder(dto);
            return new ResponseEntity<>(b, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Boolean> deleteOrder(@RequestParam String id) {
        try{
            System.out.println(id);
            boolean b = orderService.deleteOrder(id);
            return new ResponseEntity<>(b,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> updateOrder(@RequestBody OrderDTO dto) {
        try{
            boolean b = orderService.updateOrder(dto);
            return new ResponseEntity<>(b,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity searchOrder(@PathVariable String id) {
        try{
            OrderDTO orderDTO = orderService.searchOrder(id);
            return new ResponseEntity(orderDTO,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity getAllOrders() {
        try{
            List<OrderDTO> orderDTOS = orderService.getAllOrder();
            return new ResponseEntity(orderDTOS,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
