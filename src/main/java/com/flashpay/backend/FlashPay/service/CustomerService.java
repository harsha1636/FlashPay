package com.flashpay.backend.FlashPay.service;

import com.flashpay.backend.FlashPay.dto.CustomerDTO;

public class CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);
    EmployeeDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);
    void deleteEmployee(Long employeeId);

}

