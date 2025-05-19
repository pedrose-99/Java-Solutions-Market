import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { createProvider, getProvider, updateProvider } from "../services/ProviderService"

const ProviderComponent = () =>
{
    const [name, setName] = useState('')
    const [last_name, setLastName] = useState('')
    const [address, setAddress] = useState('')
    const [email, setEmail] = useState('')
    const [phone_number, setPhoneNumber] = useState('')

    const navigator = useNavigate();

    const {provider_id} = useParams();

    function handleName(e)
    {
        setName(e.target.value);
    }
    function handleLastName(e)
    {
        setLastName(e.target.value);
    }
    function handleAddress(e)
    {
        setAddress(e.target.value);
    }
    function handleEmail(e)
    {
        setEmail(e.target.value);
    }
    function handlePhoneNumber(e)
    {
        setPhoneNumber(e.target.value);
    }

    useEffect(() =>
    {
        if(provider_id)
        {
            getProvider(provider_id).then((response) =>
            {
                setName(response.data.name);
                setLastName(response.data.last_name);
                setAddress(response.data.address);
                setEmail(response.data.email);
                setPhoneNumber(response.data.phone_number);
            }).catch(error =>
            {
                console.error(error);
            }
            )
        }
    }, [provider_id])

    function saveorUpdateProvider(e)
    {
        e.preventDefault();

        const provider = {name, last_name, address, email, phone_number}
        console.log(provider);
        if(provider_id)
        {
            updateProvider(provider_id, provider).then((response) =>
            {
                console.log(response.data)
                alert('Provider has been updated');
                navigator('/providers')
        
            }).catch(error =>
            {
                alert('Error!');
                console.error(error);
            }) 
        }
        else
        {
            createProvider(provider).then((response) =>
            {
                console.log(response.data);
                alert('Provider has been created');
                navigator('/providers')
            }).catch(error =>
            {
                console.error(error);
            })
        }
    }

    function pageTitle()
    {
        if(provider_id)
        {
            return <h2 className="text-center">Update Provider</h2>
        }
        return <h2 className="text-center">Add Provider</h2>
    }
    return(
        <div className='container'>
            <div className='row'>
                <div className= 'card col-md-6 offset-md-3 offset-md-3'>
                    {
                        pageTitle()
                    }
                    <div className= 'card body'>
                        <form>
                            <div className = 'form-group mb-2'>
                                <label className='form-label'>Name: </label>
                                <input type= 'text'placeholder = 'Enter Provider Name' name='name' value={name} className='form-control' onChange={handleName}> 
                                </input>
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Last Name: </label>
                                <input type= 'text'placeholder = 'Enter Provider Last Name' name='lastName' value={last_name} className='form-control' onChange={handleLastName}> 
                                </input>
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Address: </label>
                                <input type= 'text'placeholder = 'Enter Provider Address' name='address' value={address} className='form-control' onChange={handleAddress}> 
                                </input>
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Email: </label>
                                <input type= 'text'placeholder = 'Enter Provider Email' name='email' value={email} className='form-control' onChange={handleEmail}> 
                                </input>
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Phone Number: </label>
                                <input type= 'text'placeholder = 'Enter Provider Phone number' name='phoneNumber' value={phone_number} className='form-control' onChange={handlePhoneNumber}> 
                                </input>
                            </div>

                            <button className='btn btn-success' onClick={saveorUpdateProvider}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default ProviderComponent