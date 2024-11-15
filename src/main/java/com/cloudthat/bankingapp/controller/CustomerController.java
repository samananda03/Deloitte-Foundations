package com.cloudthat.bankingapp.controller;

import com.cloudthat.bankingapp.model.ApiResponse;
import com.cloudthat.bankingapp.model.CustomerModel;
import com.cloudthat.bankingapp.model.CustomerRequest;
import com.cloudthat.bankingapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerModel customerModel = customerService.saveCustomer(customerRequest);
        return new ResponseEntity<>(new ApiResponse(true,"Customer Created Successfully", customerModel), HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public ResponseEntity<ApiResponse> getAllCustomers(){
        List<CustomerModel> customerModels = customerService.getCustomers();
        return new ResponseEntity<>(new ApiResponse(true,"Customers Fetched Successfully", customerModels), HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<ApiResponse> getCustomer(@PathVariable("customerId") Long customerId){
        CustomerModel customerModel = customerService.getCustomer(customerId);
        return new ResponseEntity<>(new ApiResponse(true,"Customer Fetch Successful", customerModel), HttpStatus.OK);
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable("customerId") Long customerId, @RequestBody CustomerRequest customerRequest){
        CustomerModel customerModel = customerService.updateCustomer(customerId, customerRequest);
        return new ResponseEntity<>(new ApiResponse(true,"Customer Update Successful", customerModel), HttpStatus.OK);
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("customerId") Long customerId){
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(new ApiResponse(true,"Customer Delete Successful", ""), HttpStatus.OK);
    }
}
