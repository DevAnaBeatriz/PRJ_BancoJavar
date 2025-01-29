import React, { useEffect, useState } from "react";
import { getClientes, deleteCliente } from "../services/clienteService";
import ClienteForm from "./ClienteForm";

const ClienteList = () => {
    const [clientes, setClientes] = useState([]);
    const [clienteEditando, setClienteEditando] = useState(null);

    useEffect(() => {
        loadClientes();
    }, []);

    const loadClientes = async () => {
        try {
            const response = await getClientes();
            setClientes(response.data);
        } catch (error) {
            console.error("Erro ao carregar clientes:", error);
        }
    };

    const handleEditar = (cliente) => {
        setClienteEditando(cliente);
    };

    const handleCancelarEdicao = () => {
        setClienteEditando(null);
    };

    const handleDeletar = async (id) => {
        try {
            await deleteCliente(id);
            loadClientes();
        } catch (error) {
            console.error("Erro ao deletar cliente:", error);
        }
    };

    return (
        <div className="container">
            <ClienteForm onClienteAdded={loadClientes} clienteEditando={clienteEditando} onCancelarEdicao={handleCancelarEdicao} />

            <h2>Lista de Clientes</h2>
            <table>
                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th>Correntista</th>
                    <th>Saldo</th>
                    <th>Score de Crédito</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {clientes.map((cliente) => (
                    <tr key={cliente.id}>
                        <td>{cliente.nome}</td>
                        <td>{cliente.telefone}</td>
                        <td>{cliente.correntista ? "Sim" : "Não"}</td>
                        <td>{cliente.saldoCc ?? "-"}</td>
                        <td>{cliente.scoreCredito ?? "-"}</td>
                        <td>
                            <button className="edit" onClick={() => handleEditar(cliente)}>Editar</button>
                            <button className="delete" onClick={() => handleDeletar(cliente.id)}>Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default ClienteList;
