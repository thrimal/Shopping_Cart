package com.wixis360.spring.service;

import com.wixis360.spring.dto.CustomerDTO;
import com.wixis360.spring.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {
    boolean saveCustomer(CustomerDTO dto);
    boolean updateCustomer(CustomerDTO dto);
    CustomerDTO searchCustomer(String id);
    boolean deleteCustomer(String id);
    List<CustomerDTO> getAllCustomers();
}
