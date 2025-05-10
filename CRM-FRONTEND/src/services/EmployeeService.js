import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/employee/employees/';

export const listEmployees = () => axios.get(REST_API_BASE_URL);

export const createEmployee = (employee) => axios.post(REST_API_BASE_URL, employee);

export const getEmployee = (employee_id) => axios.get(REST_API_BASE_URL + employee_id)

export const updateEmployee = (employee_id, employee) => axios.put(REST_API_BASE_URL + employee_id, employee)

export const deleteEmployee = (employee_id) => axios.delete(REST_API_BASE_URL + employee_id)

