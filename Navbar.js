import React from 'react';
import { Navbar, Nav, Container } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import authService from './services/authService'; // Import the authService for logout functionality

const NavigationBar = () => {
  const navigate = useNavigate(); // Using navigate hook to programmatically navigate
  const currentUser = JSON.parse(localStorage.getItem('user')); // Get the current user data from localStorage
  const role = currentUser?.role; // Access the role from user data

  // Handle logout
  const handleLogout = () => {
    authService.logout(); // Call logout function from authService
    navigate('/login'); // Redirect to login page after logout
  };

  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Container>
        <Navbar.Brand as={Link} to="/">Banking App</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/">Home</Nav.Link>
            {currentUser && (
              <>
                <Nav.Link as={Link} to="/customers">Customers</Nav.Link>
                <Nav.Link as={Link} to="/profile">Profile</Nav.Link>
              </>
            )}
            {/* Optionally, you can add role-based links here */}
            {role === 'admin' && (
              <Nav.Link as={Link} to="/admin">Admin Dashboard</Nav.Link>
            )}
          </Nav>
          <Nav>
            {!currentUser ? (
              <>
                <Nav.Link as={Link} to="/login">Login</Nav.Link>
                <Nav.Link as={Link} to="/register">Register</Nav.Link>
              </>
            ) : (
              <Nav.Link onClick={handleLogout}>Logout</Nav.Link>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default NavigationBar;
