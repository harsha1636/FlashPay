package com.flashpay.backend.FlashPay.service.impl;

import com.flashpay.backend.FlashPay.dto.CustomerDTO;
import com.flashpay.backend.FlashPay.entity.Customer;
import com.flashpay.backend.FlashPay.exception.ResourceNotFoundException;
import com.flashpay.backend.FlashPay.mapper.CustomerMapper;
import com.flashpay.backend.FlashPay.repository.CustomerRepository;
import com.flashpay.backend.FlashPay.service.CustomerService;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public List<CustomerDTO> createBatch(List<CustomerDTO> customerDTOList) {
        List<Customer> customers = customerDTOList.stream().map(customerDTO -> {
            if(customerDTO.getStatus()==null) customerDTO.setStatus(true);
            if(customerDTO.getId()==null || customerDTO.getId().isEmpty()){
                String generateID = "FPAY" + UUID.randomUUID().toString()
                        .replace("-","")
                        .substring(0,6).toUpperCase();
                customerDTO.setId(generateID);
            }

            return customerMapper.mapToCustomer(customerDTO);
        }).collect(Collectors.toList());
        List<Customer> savedCustomers = customerRepository.saveAll(customers);

        return savedCustomers.stream()
                .map(customerMapper::mapToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO){
        String generateID = "FPAY" + UUID.randomUUID().toString()
                .replace("-","")
                .substring(0,6).toUpperCase();
        customerDTO.setId(generateID);

        if (customerDTO.getStatus() == null) customerDTO.setStatus(true);

        Customer customer = CustomerMapper.mapToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.mapToCustomerDto(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO getCustomerById(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("User is not existed with the given ID : " + customerId));
        return customerMapper.mapToCustomerDto(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::mapToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerDTO updateCustomer(String customerId, CustomerDTO updatedCustomer) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("User is not existed with the given ID : " + customerId));

        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setAge(updatedCustomer.getAge());
        customer.setPhoneNumber(updatedCustomer.getPhoneNumber());

        Customer updatedCustomerObj = customerRepository.save(customer);
        return customerMapper.mapToCustomerDto(updatedCustomerObj);
    }

    @Override
    @Transactional
    public void deactivateCustomer(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("User is not existed with the given ID : " + customerId));
        customer.setStatus(false);
        Customer deactivedCustomer = customerRepository.save(customer);
        customerMapper.mapToCustomerDto(deactivedCustomer);
    }
}
