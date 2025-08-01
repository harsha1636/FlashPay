package com.flashpay.backend.FlashPay.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String phoneNumber;

    // true = active, false = inactive
    @Builder.Default
    private Boolean status = true;

    @PrePersist
    public void generateID(){
        this.id="FPAY" + UUID.randomUUID().toString().replace("-","").substring(0,6).toUpperCase();
    }
}
