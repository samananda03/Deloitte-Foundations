package com.cloudthat.bankingapp.model;

import lombok.Data;

@Data
public class CustomerModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String accountNumber;
    private String bankName;
    private String branchName;
    private double accountBalance;
}
