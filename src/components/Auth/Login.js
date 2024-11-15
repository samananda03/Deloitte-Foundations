import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import authService from '../../services/authService';  // Ensure the path is correct

const Login = () => {
  const [username, setUsername] = useState('');  // Changed to 'username' if backend expects 'username'
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const credentials = { username, password };  // Send 'username' instead of 'email'
    try {
      // Call login method from authService
      const data = await authService.login(credentials);

      // Assuming response contains the token and role
      if (data.token) {
        // Save token and role in localStorage
        localStorage.setItem('user', JSON.stringify(data));  // Store token and role in localStorage

        // Navigate based on the user's role
        const role = data.role; // Get the role from the response

        if (role === 'admin') {
          navigate('/admin');  // Redirect to admin page
        } else {
          navigate('/profile');  // Redirect to profile page for regular users
        }
      } else {
        setError(data.message || 'Invalid credentials');
      }
    } catch (err) {
      setError('An error occurred during login. Please try again.');
      console.error(err); // Log error for debugging
    }
  };

  return (
    <div>
      <h2>Login</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>} {/* Display error message if exists */}
      <form onSubmit={handleSubmit}>
        <div>
          <input
            type="text"  // changed to 'text' if you're using 'username' instead of 'email'
            placeholder="Username"  // changed placeholder to 'Username'
            value={username}  // use 'username' state here
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
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default Login;
