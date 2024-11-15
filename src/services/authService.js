import axios from 'axios';

// Set the correct URL for the backend API
const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080'; // Backend URL

// Register a new user
const register = async (userData) => {
  try {
    const response = await axios.post(`${API_URL}/register`, userData);  // Correct endpoint for registration
    return response.data;
  } catch (error) {
    console.error('Error during registration:', error);
    throw error.response ? error.response.data : error.message;
  }
};

// Log in a user
const login = async (credentials) => {
  try {
    const response = await axios.post(`${API_URL}/login`, credentials);  // Ensure POST request for login
    if (response.data.token) {
      localStorage.setItem('user', JSON.stringify(response.data)); // Save JWT and other info
    }
    return response.data;
  } catch (error) {
    console.error('Error during login:', error);
    throw error.response ? error.response.data : error.message;
  }
};

// Log out a user
const logout = () => {
  // Remove token, user data, and role from localStorage upon logout
  localStorage.removeItem('user');
  window.location.href = '/login'; // Redirect to login page after logout
};

// Get the current logged-in user
const getCurrentUser = () => {
  // Check if user data exists in localStorage
  return JSON.parse(localStorage.getItem('user'));
};

// Get the user profile using the userId
const getUserProfile = async (userId) => {
  const token = getCurrentUser()?.token;  // Retrieve token from localStorage
  try {
    const response = await axios.get(`${API_URL}/api/v1/user/${userId}/profile`, {
      headers: {
        'Authorization': `Bearer ${token}`, // Pass token in headers for authenticated requests
      },
    });
    return response.data.data; // Assuming the response contains user profile under 'data' key
  } catch (error) {
    // Handle error and throw the message
    console.error('Error fetching user profile:', error);  // Log error for debugging
    throw error.response ? error.response.data : error.message;
  }
};

// Update user profile
const updateUserProfile = async (userId, profileData) => {
  const token = getCurrentUser()?.token;  // Retrieve token from localStorage
  try {
    const response = await axios.put(`${API_URL}/api/v1/user/profile/${userId}`, profileData, {
      headers: {
        'Authorization': `Bearer ${token}`, // Pass token in headers for authenticated requests
      },
    });
    return response.data.data; // Assuming the updated profile is returned under 'data' key
  } catch (error) {
    // Handle error and throw the message
    console.error('Error updating user profile:', error);  // Log error for debugging
    throw error.response ? error.response.data : error.message;
  }
};

// Create an auth service object
const authService = {
  register,
  login,
  logout,
  getCurrentUser,
  getUserProfile,
  updateUserProfile,
};

export default authService;
