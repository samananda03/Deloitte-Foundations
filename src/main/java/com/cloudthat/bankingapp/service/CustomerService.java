package com.cloudthat.bankingapp.service;

import com.cloudthat.bankingapp.model.CustomerModel;
import com.cloudthat.bankingapp.model.CustomerRequest;

import java.util.List;

public interface CustomerService {
    CustomerModel saveCustomer(CustomerRequest customerRequest);
    List<CustomerModel> getCustomers();
    CustomerModel getCustomer(Long customerId);
    CustomerModel updateCustomer(Long customerId, CustomerRequest customerRequest);
    void deleteCustomer(Long customerId);
}
