package com.flashpay.backend.FlashPay.controller;

import com.flashpay.backend.FlashPay.dto.CustomerDTO;
import com.flashpay.backend.FlashPay.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers/batch")
    public ResponseEntity<List<CustomerDTO>> createBatch (@RequestBody List<CustomerDTO> customerDTO){
        List<CustomerDTO> customerDetails = customerService.createBatch(customerDTO);
        return  new ResponseEntity<>(customerDetails , HttpStatus.CREATED);

    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerDTO> createCustomer (@RequestBody CustomerDTO customerDTO){
        CustomerDTO saveUser = customerService.createCustomer(customerDTO);
        return  new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> getCustomer (@PathVariable("id") String customerId){
        CustomerDTO getCustomer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(getCustomer);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
        List<CustomerDTO> customerDetails = customerService.getAllCustomers();
        return  ResponseEntity.ok(customerDetails);
    }

    @PutMapping("customer/{id}")
    public ResponseEntity<CustomerDTO> updatedCustomer(@PathVariable("id") String customerId,
                                                       @RequestBody CustomerDTO updatedCustomer){
        CustomerDTO customerDTO = customerService.updateCustomer(customerId,updatedCustomer);
        return ResponseEntity.ok(customerDTO);
    }

    @PatchMapping("customer/{id}/deactivate")
    public ResponseEntity<CustomerDTO> deactivateCustomer (@PathVariable("id") String customerId){
        customerService.deactivateCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

}
