package com.flashpay.backend.FlashPay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Long Id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    // true = active, false = inactive
    private Boolean status;
}
