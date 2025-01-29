import React, { useEffect } from "react";
import { createCliente, updateCliente } from "../services/clienteService";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";

const schema = yup.object().shape({
    nome: yup
        .string()
        .matches(/^[a-zA-ZÀ-ÿ\s]+$/, "O nome deve conter apenas letras e espaços")
        .min(3, "O nome deve ter pelo menos 3 caracteres")
        .max(50, "O nome pode ter no máximo 50 caracteres")
        .required("O nome é obrigatório"),

    telefone: yup
        .string()
        .matches(/^\d+$/, "O telefone deve conter apenas números")
        .min(10, "O telefone deve ter pelo menos 10 dígitos")
        .required("O telefone é obrigatório"),

    correntista: yup.boolean(),

    saldoCc: yup
        .number()
        .transform((value, originalValue) => {
            if (typeof originalValue === "string") {
                return originalValue.trim() === "" ? null : Number(originalValue);
            }
            return originalValue;
        })
        .typeError("O saldo deve ser um número válido")
        .min(0, "O saldo não pode ser negativo")
        .when("correntista", {
            is: true,
            then: (schema) => schema.required("O saldo é obrigatório"),
            otherwise: (schema) => schema.notRequired().nullable(),
        }),
});

const ClienteForm = ({ onClienteAdded, clienteEditando, onCancelarEdicao }) => {
    const {
        register,
        handleSubmit,
        setValue,
        watch,
        formState: { errors },
        reset,
    } = useForm({
        resolver: yupResolver(schema),
        defaultValues: {
            nome: "",
            telefone: "",
            correntista: false,
            saldoCc: "",
        },
    });

    const isCorrentista = watch("correntista");

    useEffect(() => {
        if (clienteEditando) {
            setValue("nome", clienteEditando.nome);
            setValue("telefone", clienteEditando.telefone);
            setValue("correntista", clienteEditando.correntista);
            setValue("saldoCc", clienteEditando.saldoCc);
        } else {
            reset();
        }
    }, [clienteEditando, setValue, reset]);

    const onSubmit = async (data) => {
        try {
            if (!data.correntista) {
                data.saldoCc = null;
            }

            if (clienteEditando) {
                await updateCliente(clienteEditando.id, data);
            } else {
                await createCliente(data);
            }
            onClienteAdded();
            reset();
        } catch (error) {
            console.error("Erro ao salvar cliente:", error);
        }
    };

    return (
        <form onSubmit={handleSubmit(onSubmit)} className="cliente-form">
            <h3>{clienteEditando ? "Editar Cliente" : "Novo Cliente"}</h3>

            <input {...register("nome")} placeholder="Nome" />
            <p className="error">{errors.nome?.message}</p>

            <input {...register("telefone")} placeholder="Telefone" />
            <p className="error">{errors.telefone?.message}</p>

            <label className="checkbox-label">
                <input type="checkbox" {...register("correntista")} />
                Correntista
            </label>
            <p className="error">{errors.correntista?.message}</p>

            {isCorrentista && (
                <>
                    <input {...register("saldoCc")} placeholder="Saldo" />
                    <p className="error">{errors.saldoCc?.message}</p>
                </>
            )}

            <div className="button-group">
                <button type="submit">{clienteEditando ? "Atualizar" : "Criar"}</button>
                {clienteEditando && (
                    <button type="button" onClick={onCancelarEdicao} className="cancel">
                        Voltar
                    </button>
                )}
            </div>
        </form>
    );
};

export default ClienteForm;
