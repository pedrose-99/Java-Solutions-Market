import React, { useEffect, useState } from 'react'
import { listInventory } from '../services/InventoryService'
import { useNavigate } from 'react-router-dom'
import { deleteInventoryProduct } from '../services/InventoryService'

const InventoryListComponent = () => {

    const [inventory, setInventory] = useState([])

    const navigator = useNavigate();

    useEffect(() => {
        listInventory().then((response) => {
            setInventory(response.data);
        }).catch(error => {
            console.error(error);
        })
    }, [])

    function addNewInventory() {
        navigator('/add-inventory-product')
    }

    function updateInventory(product_id) {
        navigator(`/update-inventory-product/${product_id}`)
    }

    function handleDeleteInventory(product_id) {
        deleteInventoryProduct(product_id)
            .then(() => {
                setInventory(prev => prev.filter(c => c.product_id !== product_id));
            })
            .catch((error) => {
                console.error('Error deleting inventory:', error);
            });
    }

    return (
        <div className='container'>

            <h2 className='text-center'>List of Products in inventory</h2>
            <button className='btn btn-primary mb-2' onClick={addNewInventory}>Add Product</button>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>price</th>
                        <th>stock</th>
                        <th>description</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        inventory.map(inventory =>
                            <tr key={inventory.product_id}>
                                <td>{inventory.product_id}</td>
                                <td>{inventory.name}</td>
                                <td>{inventory.price}</td>
                                <td>{inventory.stock}</td>
                                <td>{inventory.description}</td>
                                <td>
                                    <button className='btn btn-info' onClick={() => updateInventory(inventory.product_id)}>Update</button>
                                </td>
                                <td>
                                    <button className='btn btn-danger' onClick={() => handleDeleteInventory(inventory.product_id)}>Delete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default InventoryListComponent