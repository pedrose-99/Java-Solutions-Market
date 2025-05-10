
import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/sales/sales/';

export const listSales = () => axios.get(REST_API_BASE_URL);

export const createSale = (sale) => axios.post(REST_API_BASE_URL, sale);

export const getSale = (sales_id) => axios.get(REST_API_BASE_URL + sales_id);

export const updateSale = (sales_id, sale) => axios.put(REST_API_BASE_URL + sales_id, sale);

export const deleteSale = (sales_id) => axios.delete(REST_API_BASE_URL + sales_id);
