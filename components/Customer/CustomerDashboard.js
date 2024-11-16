import React, { useEffect, useState } from "react";
import { Table, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import customerService from '../../services/customerService'; // Correct import

const CustomerDashboard = () => {
  const [customers, setCustomers] = useState([]); // State to hold the customer data

  useEffect(() => {
    // Fetch customers from API
    const fetchCustomers = async () => {
      try {
        const response = await customerService.getCustomers(); // Correct API call
        setCustomers(response); // Assuming response is the customer data directly
      } catch (error) {
        console.error('Failed to fetch customers:', error);
      }
    };

    fetchCustomers(); // Invoke function to fetch customer data
  }, []); // Empty dependency array to run the effect only once on component mount

  const handleDelete = async (id) => {
    try {
      await customerService.deleteCustomer(id); // Call delete API
      setCustomers(customers.filter((customer) => customer.id !== id)); // Remove deleted customer from the state
    } catch (error) {
      console.error("Failed to delete customer:", error);
    }
  };

  return (
    <div>
      <h2>Customer Dashboard</h2>
      <Link to="/add-customer">
        <Button variant="primary" className="mb-3">
          Add Customer
        </Button>
      </Link>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {customers.map((customer) => (
            <tr key={customer.id}>
              <td>{customer.firstName}</td>
              <td>{customer.lastName}</td>
              <td>{customer.email}</td>
              <td>
                <Link to={`/customer-details/${customer.id}`}>
                  <Button variant="info" className="mr-2">
                    View
                  </Button>
                </Link>
                <Link to={`/edit-customer/${customer.id}`}>
                  <Button variant="warning" className="mr-2">
                    Edit
                  </Button>
                </Link>
                <Button
                  variant="danger"
                  onClick={() => handleDelete(customer.id)}
                >
                  Delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default CustomerDashboard;
