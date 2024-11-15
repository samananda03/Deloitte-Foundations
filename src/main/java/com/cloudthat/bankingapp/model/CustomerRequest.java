package com.cloudthat.bankingapp.model;

import lombok.Data;

@Data
public class CustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String accountNumber;
    private String bankName;
    private String branchName;
    private double initialDeposit; // Use this for account creation to set initial balance
}
