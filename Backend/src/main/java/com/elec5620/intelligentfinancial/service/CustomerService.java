package com.elec5620.intelligentfinancial.service;

import com.elec5620.intelligentfinancial.dto.ChatDTO;
import com.elec5620.intelligentfinancial.dto.CustomerDTO;
import com.elec5620.intelligentfinancial.dto.CustomerIdNameDTO;
import com.elec5620.intelligentfinancial.dto.ProductDTO;
import com.elec5620.intelligentfinancial.model.Customer;
import com.elec5620.intelligentfinancial.model.Product;
import com.elec5620.intelligentfinancial.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FinancialDetailService financialDetailService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ChatService chatService;

    public List<Product> getRecommendedProducts() {
        return productService.getRecommendedProducts();
    }

    public List<ChatDTO> getChatHistory(Long customerId) {
        return chatService.getChatHistoryByCustomerId(customerId);
    }

    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return toDTO(customer.get());
        }
        return null;
    }

    public List<CustomerIdNameDTO> getAllCustomerIdsAndNames() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerIdNameDTO> customerIdNameDTOs = new ArrayList<>();

        for (Customer customer : customers) {
            CustomerIdNameDTO dto = new CustomerIdNameDTO();
            dto.setId(customer.getId());
            dto.setCustomerName(customer.getCustomerName());
            customerIdNameDTOs.add(dto);
        }
        return customerIdNameDTOs;
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = toEntity(customerDTO);
        customerRepository.save(customer);
        return toDTO(customer);
    }

    private CustomerDTO toDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setCustomerName(customer.getCustomerName());
        dto.setFinancialDetail(financialDetailService.toDTO(customer.getFinancialDetail()));
        return dto;
    }

    private Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setCustomerName(dto.getCustomerName());
        customer.setFinancialDetail(financialDetailService.toEntity(dto.getFinancialDetail(), customer));
        return customer;
    }
}
