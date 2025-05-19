import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { listBuys, deleteBuy } from "../services/BuysService"

const BuysListComponent = () =>
{
    const [buys, setBuys] = useState([])

    const navigator = useNavigate();7

    useEffect(() =>
    {
        listBuys().then((response) =>
        {
            setBuys(response.data);
        })
        .catch(error =>
        {
            console.error(error);
        })
    }, [])

    function addNewBuy()
    {
        navigator('/add-buy')
    }

    function handleDeleteBuy(buy_id)
    {
        deleteBuy(buy_id)
        .then(() =>
        {
            setBuys(prev => prev.filter(buy => buy.buy_id !== buy_id));
        })
        .catch((error) =>
        {
            console.error('Error deleting buy: ', error);
        })
    }

    return(
        <div className="container">
            <h2 className='text-center'>List of Buys</h2>
            <button className='btn btn-primary mb-2' onClick={addNewBuy}>Add Buy</button>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Buy ID</th>
                        <th>Employee</th>
                        <th>Product</th>
                        <th>Stock</th>
                        <th>Description</th>
                        <th>Total price</th>
                        <th>Delete</th>
                    </tr>
                </thead>    
                <tbody>
                    {
                        buys.map(buy =>
                            <tr key={buy.buy_id}>
                                <td>{buy.buy_id}</td>
                                <td>{buy.employee.name}</td>
                                <td>{buy.product.name}</td>
                                <td>{buy.stock}</td>
                                <td>{buy.description}</td>
                                <td>{buy.total_price}</td>
                                <td>
                                    <button className='btn btn-danger' onClick={() => handleDeleteBuy(buy.buy_id)}>Delete</button>
                                </td>
                            </tr>
                        )
                    }
            </tbody>
        </table>
    </div>
    )
}

export default BuysListComponent