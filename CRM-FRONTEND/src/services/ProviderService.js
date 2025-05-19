import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/provider/providers/';

export const listProviders = () => axios.get(REST_API_BASE_URL);

export const createProvider = (provider) => axios.post(REST_API_BASE_URL, provider);

export const getProvider= (provider_id) => axios.get(REST_API_BASE_URL + provider_id)

export const updateProvider = (provider_id, provider) => axios.put(REST_API_BASE_URL + provider_id, provider)

export const deleteProvider = (provider_id) => axios.delete(REST_API_BASE_URL + provider_id)