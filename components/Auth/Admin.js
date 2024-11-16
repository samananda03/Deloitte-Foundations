import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import authService from '../../services/authService';

const Admin = () => {
  const [data, setData] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const currentUser = authService.getCurrentUser();
    
    if (!currentUser || currentUser.role !== 'admin') {
      navigate('/login');  // Redirect if the user is not logged in or not an admin
    } else {
      // Fetch any admin-specific data here
      setData('Admin Dashboard data loaded');
    }
  }, [navigate]);

  return (
    <div>
      <h2>Admin Dashboard</h2>
      {data ? (
        <div>
          <p>{data}</p>
        </div>
      ) : (
        <p>Loading admin data...</p>
      )}
    </div>
  );
};

export default Admin;
