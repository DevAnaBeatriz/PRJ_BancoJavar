import axios from 'axios';

const API_URL = 'http://localhost:8081/api/clientes';
const USERNAME = 'admin';
const PASSWORD = 'ana123';

const axiosInstance = axios.create({
    baseURL: API_URL,
    auth: {
        username: USERNAME,
        password: PASSWORD
    },
    headers: {
        'Content-Type': 'application/json'
    }
});

export const getClientes = async () => {
    return axiosInstance.get('');
};

export const createCliente = async (cliente) => {
    return axiosInstance.post('', cliente);
};

export const updateCliente = async (id, cliente) => {
    return axiosInstance.put(`${id}`, cliente);
};

export const deleteCliente = async (id) => {
    return axiosInstance.delete(`${id}`);
};
