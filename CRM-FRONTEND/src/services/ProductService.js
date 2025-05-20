import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/products/products/';

export const listProducts = () => axios.get(REST_API_BASE_URL);

export const createProduct = (product) => axios.post(REST_API_BASE_URL, product);

export const getProduct = (product_id) => axios.get(REST_API_BASE_URL + product_id);

export const updateProduct = (product_id, product) => axios.put(REST_API_BASE_URL + product_id, product);

export const deleteProduct = (product_id) => axios.delete(REST_API_BASE_URL + product_id);