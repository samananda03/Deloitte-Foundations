import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import authService from '../../services/authService';  // Ensure the path is correct

const Login = () => {
  // State for username, password, and role
  const [username, setUsername] = useState('');  // 'username' state instead of 'email'
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('user');  // Default role set to 'user'
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent default form behavior (which might be causing a GET request)
    console.log('Submitting login with credentials:', { username, password, role }); // Debugging line

    const credentials = { username, password, role };  // Include 'role' in the credentials object
    try {
      // Call login method from authService
      const data = await authService.login(credentials);

      // Check if the response contains the token
      if (data.jwtToken) {
        // Save token and user data in localStorage
        localStorage.setItem('user', JSON.stringify(data));

        // Retrieve the user's role from the response
        const userRole = data.role || 'user'; // Default to 'user' if no role is returned

        // Redirect based on the user's role
        if (userRole === 'admin') {
          navigate('/admin');  // Redirect to admin page
        } else {
          navigate('/profile');  // Redirect to profile page for regular users
        }
      } else {
        setError(data.message || 'Invalid credentials');
      }
    } catch (err) {
      setError('An error occurred during login. Please try again.');
      console.error('Login error:', err); // Log error for debugging
    }
  };

  return (
    <div>
      <h2>Login</h2>
      {/* Display error message if exists */}
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <input
            type="text"  // Use 'text' input for 'username'
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div>
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div>
          {/* Role selection - this can be a dropdown or text input */}
          <select
            value={role}
            onChange={(e) => setRole(e.target.value)}
            required
          >
            <option value="user">User</option>
            <option value="admin">Admin</option>
          </select>
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default Login;
