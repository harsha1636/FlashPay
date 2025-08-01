package com.flashpay.backend.FlashPay.mapper;

import com.flashpay.backend.FlashPay.dto.CustomerDTO;
import com.flashpay.backend.FlashPay.entity.Customer;

import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {

    public CustomerDTO mapToCustomerDto(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .age(customer.getAge())
                .status(customer.getStatus()) // Use getStatus() for Boolean
                .build();
    }
    public static Customer mapToCustomer(CustomerDTO customerDTO){
        return Customer.builder()
                .id(customerDTO.getId())
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .email(customerDTO.getEmail())
                .phoneNumber(customerDTO.getPhoneNumber())
                .age(customerDTO.getAge())
                .status(customerDTO.getStatus()) // Use getStatus()
                .build();
    }
}
