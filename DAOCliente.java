package Interfaces;

import Clases.Cliente;

import java.util.List;

public interface DAOCliente {
    public void insertar (Cliente cliente) throws Exception;
    public List<Cliente> listarClientes () throws Exception;
}
