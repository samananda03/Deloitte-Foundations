import React from 'react';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const NotFound = () => {
  return (
    <div className="text-center" style={{ marginTop: '100px' }}>
      <h1>404 - Page Not Found</h1>
      <p>Sorry, the page you are looking for doesn't exist.</p>
      <Link to="/">
        <Button variant="primary">Go Back to Home</Button>
      </Link>
    </div>
  );
};

export default NotFound;
