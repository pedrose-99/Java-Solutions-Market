import { useEffect, useState } from "react"
import { createProduct, getProduct , updateProduct } from "../services/ProductService"
import { useNavigate, useParams } from "react-router-dom"

const ProductComponent = () =>
{
    const [name, setName] = useState('')
    const [stock, setStock] = useState('')
    const [description, setDescription] = useState('')
    const [price, setPrice] = useState('')

    const navigator = useNavigate();

    const {product_id} = useParams();

    function handleName(e)
    {
        setName(e.target.value);
    }
    function handleStock(e)
    {
        setStock(e.target.value);
    }
    function handleDescription(e)
    {
        setDescription(e.target.value);
    }
    function handlePrice(e)
    {
        setPrice(e.target.value);
    }

    useEffect(() =>
    {
        if(product_id)
        {
            getProduct(product_id).then((response) =>
            {
                setName(response.data.name);
                setStock(response.data.stock);
                setDescription(response.data.description);
                setPrice(response.data.price);

            }).catch(error =>
            {
                console.error(error);
            })
        }
    }, [product_id])

    function saveorUpdateProduct(e)
    {
        e.preventDefault();

        const product = {name, stock, description, price}
        console.log(product);
        if(product_id)
        {
            updateProduct(product_id, product).then((response) =>
            {
                console.log(response.data)
                alert('Product has been updated');
                navigator('/products')
            }).catch(error =>
            {
                alert('Error');
                console.error(error);
            })
        }
        else
        {
            createProduct(product).then((response) =>
            {
                console.log(response.data);
                alert('Product has been created');
                navigator('/products')
            }).catch(error =>
            {
                console.error(error);
            })
        }
    }

    function pageTitle()
    {
        if(product_id)
        {
            return <h2 className="text-center">Update Product</h2>
        }
        return <h2 className="text-center">Add Product</h2>
    }
    return(
        <div className="container">
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    {
                        pageTitle()
                    }
                    <div className="card body">
                        <form>
                            <div className="form-group mb-2">
                                <label className="form-label">Name: </label>
                                <input type="text" placeholder="Enter Product name" name='name' value={name} className="form-control" onChange={handleName}>
                                </input>
                            </div>

                            <div className="form-group mb-2">
                                <label className="form-label">Stock: </label>
                                <input type="number" placeholder="Enter Product stock" name='stock' value={stock} className="form-control" onChange={handleStock}>
                                </input>
                            </div>

                            <div className="form-group mb-2">
                                <label className="form-label">Description: </label>
                                <input type="text" placeholder="Enter Product description" name='description' value={description} className="form-control" onChange={handleDescription}>
                                </input>
                            </div>

                            <div className="form-group mb-2">
                                <label className="form-label">Price: </label>
                                <input type="text" placeholder="Enter Product price" name='price' value={price} className="form-control" onChange={handlePrice}>
                                </input>
                            </div>

                            <button className="btn btn-success" onClick={saveorUpdateProduct}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )

}
export default ProductComponent