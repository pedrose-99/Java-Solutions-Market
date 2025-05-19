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
  }

  const goToAddSale = () => {
    navigate('/add-sale');
  };
  const goToSales = () => {
    navigate('/sales');
  };

  return (
    <div style={styles.container}>
      <h1>Welcome to the CRM System</h1>
      <div style={styles.buttonContainer}>
        <button style={styles.button} onClick={goToClients}>View Clients</button>
        <button style={styles.button} onClick={goToProviders}>View Providers</button>
        <button style={styles.button} onClick={goToEmployees}>View Employees</button>
        <button style={styles.button} onClick={goToInventory}>View inventory</button>
        <button style={styles.button} onClick={goToProducts}>View products</button>
        <button style={styles.button} onClick={goToSales}>View Sales</button>
        <button style={styles.button} onClick={goToAddSale}>Make a Sale</button>
      </div>
    </div>
  );
};

const styles = {
  container: {
    textAlign: 'center',
    marginTop: '100px'
  },
  buttonContainer: {
    marginTop: '40px',
    display: 'flex',
    justifyContent: 'center',
    gap: '20px',
    flexDirection: 'column'
  },
  button: {
    padding: '15px 30px',
    fontSize: '16px',
    cursor: 'pointer'
  }
};

export default HomePage;
