package com.wixis360.spring.service.Impl;

import com.wixis360.spring.service.CustomerService;
import com.wixis360.spring.dto.CustomerDTO;
import com.wixis360.spring.entity.Customer;
import com.wixis360.spring.repo.CustomerRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    ModelMapper mapper;


//    @Override
//    public boolean saveCustomer(MultipartFile[] multipartFiles, CustomerDTO customerDTO) {
//        if (!customerRepo.existsById(customerDTO.getCustomerId())) {
//            String customerPic= customerDTO.getCustomerPic();
//            String[] names = new String[]{customerPic};
//            for (int i = 0; i < names.length; i++) {
//                System.out.println(names[i]);
//            }
//            String directoryName = FileUploadLocations.CUSTOMER + customerDTO.getCustomerId();
//            File directory = new File(directoryName);
//            if (!directory.exists()) {
//                System.out.println(directoryName);
//                directory.mkdirs();
//            }
//            List<String> urls = new ArrayList<>();
//            for (int i = 0; i < names.length; i++) {
//                try {
//                    File convertFile = new File(directoryName, names[i]);
//                    convertFile.createNewFile();
//                    FileOutputStream fout = new FileOutputStream(convertFile);
//                    fout.write(multipartFiles[i].getBytes());
//                    fout.close();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            System.out.println("Customer service called");
//            Customer customer = mapper.map(customerDTO, Customer.class);
//            customerRepo.save(customer);
//            return true;
//        } else {
//            throw new RuntimeException("Customer already exist !");
//        }
//    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) {
        if (!customerRepo.existsById(dto.getCustomerId())) {
            Customer c = mapper.map(dto, Customer.class);
            customerRepo.save(c);
            return true;
        } else {
            throw new RuntimeException("Customer already exist..!");
        }
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) {
        if (customerRepo.existsById(dto.getCustomerId())) {
            Customer customer = mapper.map(dto, Customer.class);
            customerRepo.save(customer);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public CustomerDTO searchCustomer(String id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            return mapper.map(customer.get(), CustomerDTO.class);
        } else {
            throw new RuntimeException("No customer for id: " + id);
        }
    }

    @Override
    public boolean deleteCustomer(String id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> all = customerRepo.findAll();
        return mapper.map(all, new TypeToken<List<CustomerDTO>>() {
        }.getType());
    }
}
