import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/buys/buys/';

export const listBuys = () => axios.get(REST_API_BASE_URL);

export const createBuy = (buy) => axios.post(REST_API_BASE_URL, buy);

export const getBuy = (buy_id) => axios.get(REST_API_BASE_URL + buy_id);

export const updateBuy = (buy_id, buy) => axios.put(REST_API_BASE_URL + buy_id, buy);

export const deleteBuy = (buy_id) => axios.delete(REST_API_BASE_URL + buy_id);