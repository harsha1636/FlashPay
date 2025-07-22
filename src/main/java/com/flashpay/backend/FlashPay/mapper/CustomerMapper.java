package com.flashpay.backend.FlashPay.mapper;

import com.flashpay.backend.FlashPay.dto.CustomerDTO;
import com.flashpay.backend.FlashPay.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    CustomerDTO mapToCustomerDto(Customer customer);
    Customer mapToCustomer(CustomerDTO customerDTO);

}
