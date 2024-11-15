import React, { useEffect, useState } from "react";
import { Table, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import customerService from '../../services/customerService'; // Correct import

const CustomerList = () => {
  const [customers, setCustomers] = useState([]);

  useEffect(() => {
    customerService.getCustomers()
      .then((data) => setCustomers(data))
      .catch((error) => {
        console.error("Failed to fetch customers:", error);
      });
  }, []);

  const handleDelete = (id) => {
    customerService.deleteCustomer(id)
      .then(() => {
        setCustomers(customers.filter((customer) => customer.id !== id));
      })
      .catch((error) => {
        console.error("Failed to delete customer:", error);
      });
  };

  return (
    <div>
      <h2>Customer List</h2>
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
                  <Button variant="info" className="mr-2">View</Button>
                </Link>
                <Link to={`/edit-customer/${customer.id}`}>
                  <Button variant="warning" className="mr-2">Edit</Button>
                </Link>
                <Button variant="danger" onClick={() => handleDelete(customer.id)}>Delete</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default CustomerList;
