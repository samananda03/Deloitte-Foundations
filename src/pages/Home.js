// src/pages/Home.js

import React from 'react';
import { Card } from 'react-bootstrap';

const Home = () => {
  return (
    <div>
      <Card className="text-center">
        <Card.Body>
          <Card.Title>Welcome to the Banking App</Card.Title>
          <Card.Text>
            Manage your transactions and customer accounts easily.
          </Card.Text>
        </Card.Body>
      </Card>
    </div>
  );
};

export default Home;
