import React from 'react';
import { Route, Routes } from 'react-router-dom'; // Remove BrowserRouter import, as it's in index.js
import NavigationBar from './Navbar';
import Home from './pages/Home';
import Login from './components/Auth/Login';
import Register from './components/Auth/Register';
import CustomerList from './components/Customer/CustomerList';
import CustomerDashboard from './components/Customer/CustomerDashboard';
import CustomerDetails from './components/Customer/CustomerDetails';
import AddEditCustomer from './components/Customer/AddEditCustomer';
import Profile from './components/Auth/Profile';
import NotFound from './pages/NotFound';

function App() {
  return (
    <div>
      <NavigationBar />
      <div className="container mt-4">
        <Routes>
          {/* Route for the homepage */}
          <Route path="/" element={<Home />} />
          {/* Route for login page */}
          <Route path="/login" element={<Login />} />
          {/* Route for register page */}
          <Route path="/register" element={<Register />} />
          {/* Route for profile page */}
          <Route path="/profile" element={<Profile />} />
          {/* Route for customer list */}
          <Route path="/customers" element={<CustomerList />} />
          {/* Route for customer dashboard */}
          <Route path="/customers/dashboard" element={<CustomerDashboard />} />
          {/* Route for customer details */}
          <Route path="/customers/:id" element={<CustomerDetails />} />
          {/* Route for adding a new customer */}
          <Route path="/customers/add" element={<AddEditCustomer />} />
          {/* Route for editing an existing customer */}
          <Route path="/customers/edit/:id" element={<AddEditCustomer />} />
          {/* Catch-all route for non-existing routes */}
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
