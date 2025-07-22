package com.flashpay.backend.FlashPay.repository;

import com.flashpay.backend.FlashPay.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
