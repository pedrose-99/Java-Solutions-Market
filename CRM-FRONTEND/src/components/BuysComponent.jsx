import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom";
import { listEmployees } from "../services/EmployeeService";
import {listProducts} from "../services/ProductService"
import { createBuy } from "../services/BuysService";



const BuysComponent = () =>
{
    const [products, setProducts] = useState([]);
    const [selectedProduct, setSelectedProduct] = useState('');
    const [employees, setEmployees] = useState([]);
    const [selectedEmployee, setSelectedEmployee] = useState('');
    const [totalPrice, setTotalPrice] = useState(0);
    const [quantity, setQuantity] = useState(1);
    const [maxQuantity, setMaxQuantity] = useState(1);

    const navigate = useNavigate();

    useEffect(() => 
    {
        listProducts().then((response) => setProducts(response.data));
        listEmployees().then((response) => setEmployees(response.data));
    }, []);
    

    const handleQuantityChange = (e) => 
    {
        const selectedQuantity = parseInt(e.target.value);
        setQuantity(selectedQuantity);

        const selectedProductData = products.find((product) => product.product_id === parseInt(selectedProduct));
        if (selectedProductData) 
        {
            setTotalPrice(selectedProductData.price * selectedQuantity);
        }
    };

    const handleProductChange = (e) => 
    {
        const productId = e.target.value;
        setSelectedProduct(productId);

        const selectedProductData = products.find((product) => product.product_id === parseInt(productId));
        if (selectedProductData) 
        {
        const stock = selectedProductData.stock;
        setMaxQuantity(stock);
        setQuantity(1);
        setTotalPrice(selectedProductData.price);
        }
    };

    const handleSubmit = (e) => 
    {
        e.preventDefault();

        if (!selectedProduct || !selectedEmployee || quantity <= 0) 
        {
            alert('Please fill in all fields.');
            return;
        }
        const buyData = 
        {
            total_price: totalPrice,
            stock: quantity,
            
            product:
            {
            "product_id": selectedProduct
            },
            employee:
            {
            "employee_id": selectedEmployee
            }
        };

        createBuy(buyData)
            .then((response) => 
            {
                console.log('Compra realizada:', response.data);
                alert('Buy has been successfully created!');
                navigate('/buys');
            })
            .catch((error) => 
            {
                console.error('Error al realizar la compra:', error);
                alert('Error creating the buy. Please try again.');
            });
        }; 
        
        
        return (
        <div className='container'>
        <div className='row'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
            <h2>Make a Buy</h2>
            <div className='card-body'>

                <form onSubmit={handleSubmit}>

                <div className='form-group mb-2'>
                    <label>Producto</label>
                    <select className='form-control mb-2' onChange={handleProductChange} value={selectedProduct}>
                    <option value=''>Select a product</option>
                    {products
                    .filter((product) => product.stock > 0)
                    .map((product) => (
                        <option key={product.product_id} value={product.product_id}>
                        {product.name}
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
                        <option key={employee.employee_id} value={employee.employee_id}>
                        {employee.name}
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
                    <label>Precio Total: {totalPrice} €</label>
                </div>

                <button type='submit' className='btn btn-success mb-2'>
                    Make a buy
                </button>
                </form>
            </div>
            </div>
        </div>
        </div>
    );
};

export default BuysComponent;