import React, { useEffect, useState } from 'react'
import { createClient, getClient, updateClient } from '../services/ClientService'
import { useNavigate, useParams } from 'react-router-dom'

const ClientComponent = () => {
    
    const [name, setName] = useState('')
    const [last_name, setLastName] = useState('')
    const [address, setAddress] = useState('')
    const [email, setEmail] = useState('')
    const [phone_number, setPhoneNumber] = useState('')
    
    const navigator = useNavigate();

    const {client_id} = useParams();

    function handleName(e){
        setName(e.target.value);
    }
    function handleLastName(e){
        setLastName(e.target.value);
    }
    function handleEmail(e){
        setEmail(e.target.value);
    }
    function handlePhoneNumber(e){
        setPhoneNumber(e.target.value);
    }
    function handleAddress(e){
        setAddress(e.target.value);
    }

    useEffect(() => {
        if (client_id){
            getClient(client_id).then((response) => {
                setName(response.data.name);
                setLastName(response.data.last_name);
                setAddress(response.data.address);
                setEmail(response.data.email);
                setPhoneNumber(response.data.phone_number);
            }).catch(error => {
                console.error(error);
            })
        }
    }, [client_id])


    function saveorUpdateClient(e){
        e.preventDefault();

        const client = {name, last_name, address, email, phone_number}
        console.log(client)
        if (client_id)
        {
            updateClient(client_id, client).then((response) =>{
                console.log(response.data);
                navigator('/clients');
            }).catch(error => {
                console.error(error);
            })
        }
        else
        {
            createClient(client).then((response) => {
                console.log(response.data);
                navigator('/clients')
            }).catch(error => {
                console.error(error);
            })
        }
    }

    function pageTitle(){
        if (client_id){
            return <h2 className='text-center'>Update Client</h2>
        }
        return <h2 className='text-center'>Add Client</h2>
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
                            <input type='text' placeholder='Enter Client Name' name='name' value={name} className='form-control' onChange={handleName}>
                            </input>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Last Name: </label>
                            <input type='text' placeholder='Enter Client Last Name' name='lastName' value={last_name} className='form-control' onChange={handleLastName}>
                            </input>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Email: </label>
                            <input type='text' placeholder='Enter Email' name='email' value={email} className='form-control' onChange={handleEmail}>
                            </input>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Phone Number: </label>
                            <input type='text' placeholder='Enter Phone number' name='phoneNumber' value={phone_number} className='form-control' onChange={handlePhoneNumber}>
                            </input>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Address: </label>
                            <input type='text' placeholder='Enter Address' name='address' value={address} className='form-control' onChange={handleAddress}>
                            </input>
                        </div>

                        <button className='btn btn-success' onClick={saveorUpdateClient}>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default ClientComponent