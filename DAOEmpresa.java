package Interfaces;

import Clases.Empresa;

import java.util.List;

public interface DAOEmpresa {
    void insertar(Empresa empresa) throws Exception;
    List<Empresa> listarEmpresas() throws Exception;
}
