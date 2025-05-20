import React, { useEffect, useState } from 'react'
import { createInventoryProduct, getInventoryProduct, updateInventoryProduct } from '../services/InventoryService'
import { useNavigate, useParams } from 'react-router-dom'

const InventoryComponent = () => {
    
    const [name, setName] = useState('')
    const [description, setDescription] = useState('')
    const [price, setPrice] = useState('')
    const [stock, setStock] = useState('')
    
    const navigator = useNavigate();

    const {product_id} = useParams();

    function handleName(e){
        setName(e.target.value);
    }
    function handleDescription(e){
        setDescription(e.target.value);
    }
    function handlestock(e){
        setStock(e.target.value);
    }

    function handleprice(e){
        setPrice(e.target.value);
    }

    useEffect(() => {
        if (product_id){
            getInventoryProduct(product_id).then((response) => {
                setName(response.data.name);
                setDescription(response.data.description);
                setPrice(response.data.price);
                setStock(response.data.stock);
            }).catch(error => {
                console.error(error);
            })
        }
    }, [product_id])


    function saveorUpdateInventoryProduct(e){
        e.preventDefault();

        const inventoryProduct = {name, description, price: parseFloat(price), stock: parseInt(stock)}
        console.log(inventoryProduct)
        if (product_id)
        {
            updateInventoryProduct(product_id, inventoryProduct).then((response) =>{
                alert('inventory has been updated')
                console.log(response.data);
                navigator('/inventory');
            }).catch(error => {
                console.error(error);
            })
        }
        else
        {
            createInventoryProduct(inventoryProduct).then((response) => {
                alert('product has been added to inventory')
                console.log(response.data);
                navigator('/inventory')
            }).catch(error => {
                console.error(error);
            })
        }
    }

    function pageTitle(){
        if (product_id){
            return <h2 className='text-center'>Update product from Inventory</h2>
        }
        return <h2 className='text-center'>Add product from Inventory</h2>
    }
    return (
    <div className='container'>
        <div className='row'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                {
                    pageTitle()
                }
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Name: </label>
                            <input type='text' placeholder='Enter inventoryProduct Name' name='name' value={name} className='form-control' onChange={handleName}>
                            </input>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Description: </label>
                            <input type='text' placeholder='Enter description' name='description' value={description} className='form-control' onChange={handleDescription}>
                            </input>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>stock: </label>
                            <input type='number' placeholder='Enter stock' name='stock' value={stock} className='form-control' onChange={handlestock}>
                            </input>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>price: </label>
                            <input type='number' placeholder='Enter price' name='price' value={price} className='form-control' onChange={handleprice}>
                            </input>
                        </div>

                        <button className='btn btn-success' onClick={saveorUpdateInventoryProduct}>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default InventoryComponent