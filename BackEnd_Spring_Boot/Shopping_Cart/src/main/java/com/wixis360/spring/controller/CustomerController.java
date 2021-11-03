package com.wixis360.spring.controller;

import com.wixis360.spring.dto.CustomerDTO;
import com.wixis360.spring.service.CustomerService;
import com.wixis360.spring.util.FileUploadHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin
public class CustomerController {


    @Autowired
    FileUploadHelper fileUploadHelper;

    @Autowired
    CustomerService customerService;
    @Autowired
    ModelMapper mapper;

//    @PostMapping
//    public ResponseEntity<Boolean> addCustomer(@RequestParam("customerId") String customerId,
//                                               @RequestParam("customerName") String customerName,
//                                               @RequestParam("customerAddress") String customerAddress,
//                                               @RequestParam("customerSalary") double customerSalary,
//                                               @RequestParam(value = "customerPic", required = false) MultipartFile file) {
//
//        try {
//
//            fileUploadHelper.uploadFile(file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String filename = customerId + file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4);
//
//        CustomerDTO customerDTO = new CustomerDTO(customerId, customerName, customerAddress, customerSalary, filename);
//        boolean b = customerService.saveCustomer(customerDTO);
//        return new ResponseEntity(b, HttpStatus.CREATED);
//    }

//    @PostMapping
//    public ResponseEntity<Boolean> addCustomer(@RequestParam("customerId") String customerId,
//                                               @RequestParam("customerName") String customerName,
//                                               @RequestParam("customerAddress") String customerAddress,
//                                               @RequestParam("customerSalary") double customerSalary) {
//        CustomerDTO customerDTO = new CustomerDTO(customerId, customerName, customerAddress, customerSalary, "jpeg");
//        boolean b = customerService.saveCustomer(customerDTO);
//        return new ResponseEntity(b, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Boolean> addCustomer2(@RequestBody CustomerDTO dto){
        try {
            dto.setCustomerPic("jpeg");
            boolean b = customerService.saveCustomer(dto);
            return new ResponseEntity(b,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Boolean> deleteCustomer(@RequestParam String id) {
        try {
            boolean b = customerService.deleteCustomer(id);
            return new ResponseEntity<>(b, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> updateCustomer(@RequestBody CustomerDTO dto) {
        try {
            dto.setCustomerPic("jpeg");
            boolean b = customerService.updateCustomer(dto);
            return new ResponseEntity<>(b, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity searchCustomer(@PathVariable String id) {
        try {
            CustomerDTO customerDTO = customerService.searchCustomer(id);
            return new ResponseEntity(customerDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity getAllCustomers() {
        try {
            List<CustomerDTO> allCustomers = customerService.getAllCustomers();
            return new ResponseEntity(allCustomers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
