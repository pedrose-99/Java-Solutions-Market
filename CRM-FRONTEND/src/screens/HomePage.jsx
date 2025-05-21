import React from 'react';
import { useNavigate } from 'react-router-dom';

const HomePage = () => {
  const navigate = useNavigate();

  const goToClients = () => {
    navigate('/clients');
  };

  const goToProviders = () => {
    navigate('/providers');
  };

  const goToEmployees = () => {
    navigate('/employees');
  };

  const goToInventory = () => {
    navigate('/inventory');
  };
  const goToProducts = () => {
    navigate('/products');
  };
  const goToBuys = () => {
    navigate('/buys');
  };

  const goToSales = () => {
    navigate('/sales');
  };

  return (
    <div className="container-home">
      <h1>Welcome to the CRM System</h1>
      <div className="button-container">
        <button className="crm-button" onClick={goToClients}>View Clients</button>
        <button className="crm-button" onClick={goToProviders}>View Providers</button>
        <button className="crm-button" onClick={goToEmployees}>View Employees</button>
        <button className="crm-button" onClick={goToInventory}>View inventory</button>
        <button className="crm-button" onClick={goToProducts}>View products</button>
        <button className="crm-button" onClick={goToBuys}>View buys</button>
        <button className="crm-button" onClick={goToSales}>View Sales</button>
      </div>
    </div>
  );
};


export default HomePage;
