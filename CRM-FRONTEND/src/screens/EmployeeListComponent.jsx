import React, {useEffect, useState} from 'react'
import { listEmployees } from '../services/EmployeeService'
import { useNavigate } from 'react-router-dom'
import { deleteEmployee } from '../services/EmployeeService'

const EmployeeListComponent = () => {

    const [Employees, setEmployees] = useState([])
    
    const navigator = useNavigate();

    useEffect(() => {
        listEmployees().then((response) => {
            setEmployees(response.data);
        }).catch(error => {
            console.error(error);
        })
    }, [])

    function addNewEmployee(){
        navigator('/add-Employee')
    }

    function updateEmployee(Employee_id){
        navigator(`/update-Employee/${Employee_id}`)
    }

    function handleDeleteEmployee(Employee_id) {
        deleteEmployee(Employee_id)
          .then(() => {
            setEmployees(prev => prev.filter(c => c.Employee_id !== Employee_id));
          })
          .catch((error) => {
            console.error('Error deleting Employee:', error);
          });
      }

  return (
    <div className='container'>

        <h2 className='text-center'>List of Employees</h2>
        <button className='btn btn-primary mb-2' onClick={addNewEmployee}>Add Employee</button>
        <table className='table table-striped table-bordered'>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>LastName</th>
                    <th>Address</th>
                    <th>Email</th>
                    <th>Phone number</th>
                    <th>Type</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                {
                    Employees.map(employee =>
                        <tr key={employee.employee_id}>
                            <td>{employee.employee_id}</td>
                            <td>{employee.name}</td>
                            <td>{employee.last_name}</td>
                            <td>{employee.address}</td>
                            <td>{employee.email}</td>
                            <td>{employee.phone_number}</td>
                            <td>{employee.type}</td>
                            <td>
                                <button className='btn btn-info' onClick={() => updateEmployee(employee.employee_id)}>Update</button>
                                </td>
                                <td>
                                <button className='btn btn-danger' onClick={() => handleDeleteEmployee(employee.employee_id)}>Delete</button>
                            </td>
                        </tr>
                    )
                }
            </tbody>
        </table>
    </div>
  )
}

export default EmployeeListComponent