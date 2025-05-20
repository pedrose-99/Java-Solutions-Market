import { useNavigate } from "react-router-dom"
import { useState, useEffect } from "react";
import { listProducts, deleteProduct } from "../services/ProductService";

const ProductListComponent = () =>
{
    const [products, setProducts] = useState([])

    const navigator = useNavigate();

    useEffect(() =>
    {
        listProducts().then((response) =>
        {
            setProducts(response.data);
        }).catch(error =>
        {
            console.error(error);
        }
        )
    }, [])

    function addNewProduct()
    {
        navigator('/add-product')
    }


    function handleDeleteProduct(product_id) {
        deleteProduct(product_id)
            .then(() => {
                setProducts(prev => prev.filter(c => c.product_id !== product_id));
            })
            .catch((error) => {
                console.error('Error deleting product:', error);
            });
    }


    return (
        <div className='container'>
            <h2 className='text-center'>List of Products</h2>
            <button className='btn btn-primary mb-2' onClick={addNewProduct}>Add Product</button>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Name</th>
                        <th>Stock</th>
                        <th>Decription</th>
                        <th>Price</th>
                        <th>Provider id</th>
                        <th>Provider name</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        products.map(product =>
                            <tr key={product.product_id}>
                                <td>{product.product_id}</td>
                                <td>{product.name}</td>
                                <td>{product.stock}</td>
                                <td>{product.description}</td>
                                <td>{product.price}</td>
                                <td>{product.provider.provider_id}</td>
                                <td>{product.provider.name}</td>
                                <td>
                                    <button className='btn btn-danger' onClick={() => handleDeleteProduct(product.product_id)}>Delete</button>

                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default ProductListComponent