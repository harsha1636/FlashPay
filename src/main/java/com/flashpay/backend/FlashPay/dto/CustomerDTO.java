package com.flashpay.backend.FlashPay.dto;

import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String phoneNumber;

    // true = active, false = inactive
    @Builder.Default
    private Boolean status = true;
}
