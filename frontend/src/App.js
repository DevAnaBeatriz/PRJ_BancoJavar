import React, {useEffect, useState} from "react";
import ClienteList from "./components/ClienteList";
import ClienteForm from "./components/ClienteForm";
import Header from "./components/Header";

const App = () => {
  useEffect(() => {
    document.title = "Gerenciamento de Clientes";
  }, []);

  const [clienteEdit, setClienteEdit] = useState(null);

  const handleEdit = (cliente) => {
    setClienteEdit(cliente);
  };

  const handleSave = () => {
    setClienteEdit(null);
  };

  return (
      <div>
        <ClienteList onEdit={handleEdit} />
      </div>
  );
};

export default App;
