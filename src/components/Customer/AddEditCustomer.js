import React, { useEffect, useState } from "react";
import { Form, Button } from "react-bootstrap";
import { useParams, useNavigate } from "react-router-dom";
import customerService from '../../services/customerService'; // Correctly import service functions

const AddEditCustomer = () => {
  const [customer, setCustomer] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phoneNumber: "",
    address: "",
    accountNumber: "",
    bankName: "",
    branchName: "",
    accountBalance: "",
  });
  
  const { customerId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    if (customerId) {
      // Fetch existing customer data if editing
      customerService.getCustomerById(customerId).then((response) => {
        setCustomer(response.data); // Assuming response.data contains the customer data
      }).catch((error) => {
        console.error("Error fetching customer data", error);
      });
    }
  }, [customerId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCustomer({
      ...customer,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (customerId) {
      // Update customer if customerId is present
      customerService.updateCustomer(customerId, customer).then(() => {
        navigate("/customer-dashboard"); // Redirect to customer dashboard
      }).catch((error) => {
        console.error("Failed to update customer:", error);
      });
    } else {
      // Add new customer if no customerId
      customerService.addCustomer(customer).then(() => {
        navigate("/customer-dashboard"); // Redirect to customer dashboard
      }).catch((error) => {
        console.error("Failed to add customer:", error);
      });
    }
  };

  return (
    <div>
      <h2>{customerId ? "Edit" : "Add"} Customer</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formFirstName">
          <Form.Label>First Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter first name"
            name="firstName"
            value={customer.firstName}
            onChange={handleChange}
          />
        </Form.Group>
        <Form.Group controlId="formLastName">
          <Form.Label>Last Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter last name"
            name="lastName"
            value={customer.lastName}
            onChange={handleChange}
          />
        </Form.Group>
        {/* Add more form fields for other customer attributes */}
        <Button variant="primary" type="submit">
          Submit
        </Button>
      </Form>
    </div>
  );
};

export default AddEditCustomer;
