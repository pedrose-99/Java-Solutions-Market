import React, {useEffect, useState} from 'react'
import { listClients } from '../services/ClientService'
import { useNavigate } from 'react-router-dom'
import { deleteClient } from '../services/ClientService'

const ClientListComponent = () => {

    const [clients, setClients] = useState([])
    
    const navigator = useNavigate();

    useEffect(() => {
        listClients().then((response) => {
            setClients(response.data);
        }).catch(error => {
            console.error(error);
        })
    }, [])

    function addNewClient(){
        navigator('/add-client')
    }

    function updateClient(client_id){
        navigator(`/update-client/${client_id}`)
    }

    function handleDeleteClient(client_id) {
        deleteClient(client_id)
          .then(() => {
            setClients(prev => prev.filter(c => c.client_id !== client_id));
          })
          .catch((error) => {
            console.error('Error deleting client:', error);
          });
      }

  return (
    <div className='container'>

        <h2 className='text-center'>List of Clients</h2>
        <button className='btn btn-primary mb-2' onClick={addNewClient}>Add Client</button>
        <table className='table table-striped table-bordered'>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>LastName</th>
                    <th>Address</th>
                    <th>Email</th>
                    <th>Phone number</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                {
                    clients.map(client =>
                        <tr key={client.client_id}>
                            <td>{client.client_id}</td>
                            <td>{client.name}</td>
                            <td>{client.last_name}</td>
                            <td>{client.address}</td>
                            <td>{client.email}</td>
                            <td>{client.phone_number}</td>
                            <td>
                                <button className='btn btn-info' onClick={() => updateClient(client.client_id)}>Update</button>
                                </td>
                                <td>
                                <button className='btn btn-danger' onClick={() => handleDeleteClient(client.client_id)}>Delete</button>
                            </td>
                        </tr>
                    )
                }
            </tbody>
        </table>
    </div>
  )
}

export default ClientListComponent