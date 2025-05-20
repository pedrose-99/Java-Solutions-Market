import React, { useEffect, useState } from 'react';
import { listClients } from '../services/ClientService';
import { listInventory } from '../services/InventoryService';
import { listEmployees } from '../services/EmployeeService';
import { useNavigate } from 'react-router-dom';
import { createSale } from '../services/SalesService';

const SalesComponent = () => {
  const [clients, setClients] = useState([]);
  const [products, setProducts] = useState([]);
  const [employees, setEmployees] = useState([]);
  const [selectedClient, setSelectedClient] = useState('');
  const [selectedProduct, setSelectedProduct] = useState('');
  const [selectedEmployee, setSelectedEmployee] = useState('');
  const [quantity, setQuantity] = useState(1);
  const [maxQuantity, setMaxQuantity] = useState(1);
  const [totalPrice, setTotalPrice] = useState(0);

  const navigate = useNavigate();

  useEffect(() => {
    listClients().then((response) => setClients(response.data));
    listInventory().then((response) => setProducts(response.data));
    listEmployees().then((response) => setEmployees(response.data));
  }, []);

  const handleQuantityChange = (e) => {
    const selectedQuantity = parseInt(e.target.value);
    setQuantity(selectedQuantity);

    const selectedProductData = products.find((product) => product.product_id === parseInt(selectedProduct));
    if (selectedProductData) {
      setTotalPrice(selectedProductData.price * selectedQuantity);
    }
  };

  const handleProductChange = (e) => {
    const productId = e.target.value;
    setSelectedProduct(productId);

    const selectedProductData = products.find((product) => product.product_id === parseInt(productId));
    if (selectedProductData) {
      const stock = selectedProductData.stock;
      setMaxQuantity(stock);
      setQuantity(1);
      setTotalPrice(selectedProductData.price);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!selectedClient || !selectedProduct || !selectedEmployee || quantity <= 0) {
      alert('Please fill in all fields.');
      return;
    }

    const saleData = {
      total_price: totalPrice,
      total_sold: quantity,
      client:
      {
        "client_id": selectedClient
      },
      product:
      {
        "product_id": selectedProduct
      },
      employee:
      {
        "employee_id": selectedEmployee
      }
    };

    createSale(saleData)
      .then((response) => {
        console.log('Venta realizada:', response.data);
        alert('Sale has been successfully done');
        navigate('/sales');
      })
      .catch((error) => {
        console.error('Error al realizar la venta:', error);
        alert('Error creating the sale. Please try again.');
      });
  };

  return (
    <div className='container'>
      <div className='row'>
        <div className='card col-md-6 offset-md-3 offset-md-3'>
          <h2>Make a Sale</h2>
          <div className='card-body'>
            <form onSubmit={handleSubmit}>
              <div className='form-group mb-2'>
                <label>Client</label>
                <select className='form-control mb-2' onChange={(e) => setSelectedClient(e.target.value)} value={selectedClient}>
                  <option value=''>Select a client</option>
                  {clients.map((client) => (
                    <option key={client.client_id} value={client.client_id}>
                      {client.name}
                    </option>
                  ))}
                </select>
              </div>

              <div className='form-group mb-2'>
                <label>Product</label>
                <select className='form-control mb-2' onChange={handleProductChange} value={selectedProduct}>
                  <option value=''>Select a product</option>
                  {products.map((product) => (
                    <option key={product.product_id} value={product.product_id}>
                      {product.name} - {product.price} € - {product.stock} stock
                    </option>
                  ))}
                </select>
              </div>

              <div className='form-group mb-2'>
                <label>Quantity</label>
                <select
                  className='form-control mb-2'
                  value={quantity}
                  onChange={handleQuantityChange}
                >
                  {[...Array(maxQuantity)].map((_, i) => (
                    <option key={i + 1} value={i + 1}>
                      {i + 1}
                    </option>
                  ))}
                </select>
              </div>

              <div className='form-group mb-2'>
                <label>Employee</label>
                <select
                  className='form-control mb-2'
                  onChange={(e) => setSelectedEmployee(e.target.value)}
                  value={selectedEmployee}
                >
                  <option value=''>Select an employee</option>
                  {employees.map((employee) => (
                    <option key={employee.id} value={employee.employee_id}>
                      {employee.name}
                    </option>
                  ))}
                </select>
              </div>

              <div className='form-group mb-2'>
                <label>Precio Total: {totalPrice} €</label>
              </div>

              <button type='submit' className='btn btn-success mb-2'>
                Make a sale
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SalesComponent;
