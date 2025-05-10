import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/inventory/inventory-products/';

export const listInventory = () => axios.get(REST_API_BASE_URL);

export const createInventoryProduct = (inventory) => axios.post(REST_API_BASE_URL, inventory);

export const getInventoryProduct = (product_id) => axios.get(REST_API_BASE_URL + product_id)

export const updateInventoryProduct = (product_id, inventory) => axios.put(REST_API_BASE_URL + product_id, inventory)

export const deleteInventoryProduct = (product_id) => axios.delete(REST_API_BASE_URL + product_id)

