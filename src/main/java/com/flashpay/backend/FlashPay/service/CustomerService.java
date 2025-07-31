package com.flashpay.backend.FlashPay.service;

import com.flashpay.backend.FlashPay.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Long customerId);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO updateCustomer(Long customerId, CustomerDTO updatedCustomer);
    CustomerDTO deactivateCustomer(Long customerId);

}

