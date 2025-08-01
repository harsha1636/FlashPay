package com.flashpay.backend.FlashPay.service;

import com.flashpay.backend.FlashPay.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> createBatch (List<CustomerDTO> customerDTOList);
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(String customerId);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO updateCustomer(String customerId, CustomerDTO updatedCustomer);
    void deactivateCustomer(String customerId);

}

