import axios from 'axios';

// Assuming you store the token in localStorage or sessionStorage after login
const API_URL = 'http://localhost:8080/api/customers';

// Get the token from localStorage or sessionStorage
const getToken = () => {
  return localStorage.getItem('token'); // or sessionStorage.getItem('token')
};

// Get all customers
const getCustomers = async () => {
  try {
    const token = getToken(); // Get the token
    const response = await axios.get(API_URL, {
      headers: {
        Authorization: `Bearer ${token}`  // Add the token to the headers
      }
    });
    return response.data; // Assuming the list of customers is in response.data
  } catch (error) {
    console.error("Error fetching customers", error);
    throw error; // Rethrow error to be handled in the component
  }
};

// Get customer by ID
const getCustomerById = async (id) => {
  try {
    const token = getToken(); // Get the token
    const response = await axios.get(`${API_URL}/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`  // Add the token to the headers
      }
    });
    return response.data; // Assuming the customer data is in response.data
  } catch (error) {
    console.error(`Error fetching customer with ID: ${id}`, error);
    throw error; // Rethrow error to be handled in the component
  }
};

// Add a new customer
const addCustomer = async (customer) => {
  try {
    const token = getToken(); // Get the token
    const response = await axios.post(API_URL, customer, {
      headers: {
        Authorization: `Bearer ${token}`  // Add the token to the headers
      }
    });
    return response.data; // Assuming the added customer data is in response.data
  } catch (error) {
    console.error("Error adding customer", error);
    throw error; // Rethrow error to be handled in the component
  }
};

// Update existing customer
const updateCustomer = async (id, customer) => {
  try {
    const token = getToken(); // Get the token
    const response = await axios.put(`${API_URL}/${id}`, customer, {
      headers: {
        Authorization: `Bearer ${token}`  // Add the token to the headers
      }
    });
    return response.data; // Assuming the updated customer data is in response.data
  } catch (error) {
    console.error(`Error updating customer with ID: ${id}`, error);
    throw error; // Rethrow error to be handled in the component
  }
};

// Delete customer
const deleteCustomer = async (id) => {
  try {
    const token = getToken(); // Get the token
    const response = await axios.delete(`${API_URL}/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`  // Add the token to the headers
      }
    });
    return response.data; // Assuming response contains confirmation or success data
  } catch (error) {
    console.error(`Error deleting customer with ID: ${id}`, error);
    throw error; // Rethrow error to be handled in the component
  }
};

// Create a customer service object
const customerService = {
  getCustomers,
  getCustomerById,
  addCustomer,
  updateCustomer,
  deleteCustomer,
};

export default customerService;
