import React, { useEffect, useState } from 'react'
import { listSales } from '../services/SalesService'
import { useNavigate } from 'react-router-dom'
import { deleteSale } from '../services/SalesService'

const SalesListComponent = () => {

    const [sales, setSales] = useState([])

    const navigator = useNavigate();

    useEffect(() => {
        listSales().then((response) => {
            setSales(response.data);
        }).catch(error => {
            console.error(error);
        })
    }, [])

    function addNewSale() {
        navigator('/add-sale')
    }


    function handleDeleteSale(sales_id) {
        deleteSale(sales_id)
            .then(() => {
                setSales(prev => prev.filter(sale => sale.sales_id !== sales_id));
            })
            .catch((error) => {
                console.error('Error deleting Sale:', error);
            });
    }

    return (
        <div className='container'>

            <h2 className='text-center'>List of Sales</h2>
            <button className='btn btn-primary mb-2' onClick={addNewSale}>Add Sale</button>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Sale ID</th>
                        <th>Client</th>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Total Price</th>
                        <th>Employee</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        sales.map(sale =>
                            <tr key={sale.sales_id}>
                                <td>{sale.sales_id}</td>
                                <td>{sale.client.name}</td>
                                <td>{sale.product.name}</td>
                                <td>{sale.total_sold}</td>
                                <td>{sale.total_price} €</td>
                                <td>{sale.employee.name}</td>
                                <td>
                                    <button className='btn btn-danger' onClick={() => handleDeleteSale(sale.sales_id)}>Delete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default SalesListComponent
