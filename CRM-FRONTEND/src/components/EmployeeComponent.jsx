import React, { useEffect, useState } from 'react'
import { createEmployee, getEmployee, updateEmployee } from '../services/EmployeeService'
import { useNavigate, useParams } from 'react-router-dom'

const EmployeeComponent = () => {
    
    const [name, setName] = useState('')
    const [last_name, setLastName] = useState('')
    const [address, setAddress] = useState('')
    const [email, setEmail] = useState('')
    const [phone_number, setPhoneNumber] = useState('')
    const [type, setType] = useState('')
    
    const navigator = useNavigate();

    const {employee_id} = useParams();

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
    function handleType(e){
        setType(e.target.value);
    }

    useEffect(() => {
        if (employee_id){
            getEmployee(employee_id).then((response) => {
                setName(response.data.name);
                setLastName(response.data.last_name);
                setAddress(response.data.address);
                setEmail(response.data.email);
                setPhoneNumber(response.data.phone_number);
                setType(response.data.type);
            }).catch(error => {
                console.error(error);
            })
        }
    }, [employee_id])


    function saveorUpdateEmployee(e){
        e.preventDefault();

        const employee = {name, last_name, address, email, phone_number, type}
        console.log(employee)
        if (employee_id)
        {
            updateEmployee(employee_id, employee).then((response) =>{
                console.log(response.data);
                alert('Employee has been created');
                navigator('/employees');
            }).catch(error => {
                alert('Employee error');
                console.error(error);
            })
        }
        else
        {
            createEmployee(employee).then((response) => {
                console.log(response.data);
                alert('Employee has been updated');
                navigator('/employees')
            }).catch(error => {
                alert('Employee error');
                console.error(error);
            })
        }
    }

    function pageTitle(){
        if (employee_id){
            return <h2 className='text-center'>Update employee</h2>
        }
        return <h2 className='text-center'>Add employee</h2>
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
                            <input type='text' placeholder='Enter employee Name' name='name' value={name} className='form-control' onChange={handleName}>
                            </input>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Last Name: </label>
                            <input type='text' placeholder='Enter employee Last Name' name='lastName' value={last_name} className='form-control' onChange={handleLastName}>
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
                        <div className='form-group mb-2'>
                            <label className='form-label'>Type: </label>
                            <input type='text' placeholder='Enter Type' name='type' value={type} className='form-control' onChange={handleType}>
                            </input>
                        </div>

                        <button className='btn btn-success' onClick={saveorUpdateEmployee}>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default EmployeeComponent