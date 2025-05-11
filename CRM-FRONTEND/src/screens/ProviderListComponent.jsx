import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { listProviders } from "../services/ProviderService"
import { deleteProvider } from "../services/ProviderService"

const ProviderListComponent = () =>
{
    const [providers, setProviders] = useState([])

    const navigator = useNavigate();

    useEffect(() =>
    {
        listProviders().then((response) =>
        {
            setProviders(response.data);
        }).catch(error =>
        {
            console.error(error);
        })
    }, []);

    function addNewProvider()
    {
        navigator('/add-provider');
    }
    function updateProvider(provider_id)
    {
        navigator(`/update-provider/${provider_id}`)
    }
    function deleteProvider(provider_id)
    {
        deleteProvider(provider_id)
        .then(() =>
        {
            setProviders(prev => prev.filter(p => p.provider_id !== provider_id));
        })
        .catch((error) =>
        {
            console.error('Error deleting provider: ', error);
        })
    }

    return (
        <div className = 'container'>
            <h2 className = 'text-center'>List of Providers</h2>
            <button className='btn btn-primary mb-2' onClick={addNewProvider}>Add Provider</button>
            <table className = 'table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>LastName</th>
                        <th>Address</th>
                        <th>Phone number</th>
                        <th>Email</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        providers.map(provider =>
                            <tr key={provider.provider_id}>
                                <td>{provider.provider_id}</td>
                                <td>{provider.name}</td>
                                <td>{provider.last_name}</td>
                                <td>{provider.address}</td>
                                <td>{provider.email}</td>
                                <td>{provider.phone_number}</td>
                                <td>
                                    <button className='btn btn-info' onClick={() => updateProvider(provider.provider_id)}>Update</button>
                                </td>
                                <td>
                                    <button className = 'btn btn-danger' onClick={() => deleteProvider(provider.provider_id)}>Delete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default ProviderListComponent