import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/inventary/inventary-products/';

export const listInventary = () => axios.get(REST_API_BASE_URL);

export const createInventaryProduct = (inventaryProduct) => axios.post(REST_API_BASE_URL, inventaryProduct);

export const getInventaryProduct = (inventary_product_id) => axios.get(REST_API_BASE_URL + inventary_product_id)

export const updateInventaryProduct = (inventary_product_id, inventaryProduct) => axios.put(REST_API_BASE_URL + inventary_product_id, inventaryProduct)

export const deleteInventaryProduct = (inventary_product_id) => axios.delete(REST_API_BASE_URL + inventary_product_id)

