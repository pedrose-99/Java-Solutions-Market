import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/client/clients/';

export const listClients = () => axios.get(REST_API_BASE_URL);

export const createClient = (client) => axios.post(REST_API_BASE_URL, client);

export const getClient = (client_id) => axios.get(REST_API_BASE_URL + client_id)

export const updateClient = (client_id, client) => axios.put(REST_API_BASE_URL + client_id, client)

export const deleteClient = (client_id) => axios.delete(REST_API_BASE_URL + client_id)