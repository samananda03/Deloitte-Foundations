import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import authService from '../../services/authService';  // Ensure authService path is correct

const Profile = () => {
  const [user, setUser] = useState(null);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  // Use effect to fetch profile data when the component mounts
  useEffect(() => {
    const getCurrentUserProfile = async () => {
      const currentUser = authService.getCurrentUser();  // Ensure this returns the logged-in user object
      if (currentUser) {
        try {
          const profileData = await authService.getUserProfile(currentUser.id);  // Make sure this API exists
          setUser(profileData);
        } catch (err) {
          setError('Failed to fetch profile');
        }
      } else {
        navigate('/login');  // Redirect to login if no user is logged in
      }
    };

    getCurrentUserProfile();
  }, [navigate]);  // Only depend on navigate since it is passed from react-router-dom

  // Logout function
  const handleLogout = () => {
    authService.logout();  // Make sure this clears user session properly
    navigate('/login');  // Redirect to login after logout
  };

  return (
    <div>
      <h2>User Profile</h2>
      {error && <p>{error}</p>}
      {user ? (
        <div>
          <p><strong>First Name:</strong> {user.firstName}</p>
          <p><strong>Last Name:</strong> {user.lastName}</p>
          <p><strong>Email:</strong> {user.email}</p>
          <button onClick={handleLogout}>Logout</button>
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
};

export default Profile;
