package com.flashpay.backend.FlashPay.service.impl;

import com.flashpay.backend.FlashPay.dto.CustomerDTO;
import com.flashpay.backend.FlashPay.entity.Customer;
import com.flashpay.backend.FlashPay.exception.ResourceNotFoundException;
import com.flashpay.backend.FlashPay.mapper.CustomerMapper;
import com.flashpay.backend.FlashPay.repository.CustomerRepository;
import com.flashpay.backend.FlashPay.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO){
        Customer customer = customerMapper.mapToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.mapToCustomerDto(savedCustomer);
    }

    @Override
    public CustomerDTO getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("User is not existed with the given ID : " + customerId));
        return customerMapper.mapToCustomerDto(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::mapToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO updatedCustomer) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("User is not existed with the given ID : " + customerId));

        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhoneNumber(updatedCustomer.getPhoneNumber());

        Customer updatedCustomerObj = customerRepository.save(customer);
        return customerMapper.mapToCustomerDto(updatedCustomerObj);
    }

    @Override
    public CustomerDTO deactivateCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("User is not existed with the given ID : " + customerId));
        customer.setStatus(false);
        Customer deactivedCustomer = customerRepository.save(customer);
        return customerMapper.mapToCustomerDto(deactivedCustomer);
    }
}
