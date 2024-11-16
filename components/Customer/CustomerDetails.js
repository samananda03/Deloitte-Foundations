import React, { useEffect, useState } from "react";
import { Card, Button } from "react-bootstrap";
import { useParams, useNavigate } from "react-router-dom"; // Correct import
import customerService from '../../services/customerService'; // Import the service

const CustomerDetails = () => {
  const [customer, setCustomer] = useState(null); // State to hold customer data
  const { customerId } = useParams(); // Get the customerId from URL params
  const navigate = useNavigate(); // Use useNavigate instead of useHistory

  useEffect(() => {
    // Fetch customer details by ID
    const fetchCustomer = async () => {
      try {
        const response = await customerService.getCustomerById(customerId); // Correct API call
        setCustomer(response.data); // Assuming the response has 'data' field
      } catch (error) {
        console.error("Error fetching customer:", error);
      }
    };

    fetchCustomer(); // Invoke the function to fetch customer data
  }, [customerId]); // Re-run when customerId changes

  const handleEdit = () => {
    // Redirect to the edit customer page
    navigate(`/edit-customer/${customerId}`); // Use navigate instead of history.push
  };

  return (
    <div>
      {customer ? (
        <Card>
          <Card.Body>
            <Card.Title>Customer Details</Card.Title>
            <Card.Text>
              <strong>First Name:</strong> {customer.firstName}
            </Card.Text>
            <Card.Text>
              <strong>Last Name:</strong> {customer.lastName}
            </Card.Text>
            <Card.Text>
              <strong>Email:</strong> {customer.email}
            </Card.Text>
            <Card.Text>
              <strong>Phone Number:</strong> {customer.phoneNumber}
            </Card.Text>
            <Card.Text>
              <strong>Address:</strong> {customer.address}
            </Card.Text>
            <Card.Text>
              <strong>Account Number:</strong> {customer.accountNumber}
            </Card.Text>
            <Card.Text>
              <strong>Bank Name:</strong> {customer.bankName}
            </Card.Text>
            <Card.Text>
              <strong>Branch Name:</strong> {customer.branchName}
            </Card.Text>
            <Card.Text>
              <strong>Account Balance:</strong> ${customer.accountBalance}
            </Card.Text>
            <Button variant="warning" onClick={handleEdit}>
              Edit
            </Button>
          </Card.Body>
        </Card>
      ) : (
        <p>Loading...</p> // Show loading message while the data is being fetched
      )}
    </div>
  );
};

export default CustomerDetails;
