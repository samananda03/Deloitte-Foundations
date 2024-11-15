package com.cloudthat.bankingapp.service.impl;

import com.cloudthat.bankingapp.entity.Customer;
import com.cloudthat.bankingapp.model.CustomerModel;
import com.cloudthat.bankingapp.model.CustomerRequest;
import com.cloudthat.bankingapp.repository.CustomerRepository;
import com.cloudthat.bankingapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerModel saveCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setAddress(customerRequest.getAddress());
        customer.setAccountNumber(customerRequest.getAccountNumber());
        customer.setBankName(customerRequest.getBankName());
        customer.setBranchName(customerRequest.getBranchName());
        customer.setAccountBalance(customerRequest.getInitialDeposit());

        customer = customerRepository.save(customer);
        return convertToModel(customer);
    }

    @Override
    public List<CustomerModel> getCustomers() {
        return customerRepository.findAll().stream().map(this::convertToModel).collect(Collectors.toList());
    }

    @Override
    public CustomerModel getCustomer(Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        return customerOpt.map(this::convertToModel).orElse(null);
    }

    @Override
    public CustomerModel updateCustomer(Long customerId, CustomerRequest customerRequest) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.setFirstName(customerRequest.getFirstName());
            customer.setLastName(customerRequest.getLastName());
            customer.setEmail(customerRequest.getEmail());
            customer.setPhoneNumber(customerRequest.getPhoneNumber());
            customer.setAddress(customerRequest.getAddress());
            customer.setAccountNumber(customerRequest.getAccountNumber());
            customer.setBankName(customerRequest.getBankName());
            customer.setBranchName(customerRequest.getBranchName());
            customer.setAccountBalance(customerRequest.getInitialDeposit()); // Update if needed

            customer = customerRepository.save(customer);
            return convertToModel(customer);
        }
        return null;
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    private CustomerModel convertToModel(Customer customer) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(customer.getId());
        customerModel.setFirstName(customer.getFirstName());
        customerModel.setLastName(customer.getLastName());
        customerModel.setEmail(customer.getEmail());
        customerModel.setPhoneNumber(customer.getPhoneNumber());
        customerModel.setAddress(customer.getAddress());
        customerModel.setAccountNumber(customer.getAccountNumber());
        customerModel.setBankName(customer.getBankName());
        customerModel.setBranchName(customer.getBranchName());
        customerModel.setAccountBalance(customer.getAccountBalance());

        return customerModel;
    }
}
