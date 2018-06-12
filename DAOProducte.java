package Interfaces;

import Clases.Producto;

import java.util.List;

public interface DAOProducte {
    void insertar(Producto prod) throws Exception;
    void eliminar(Producto prod) throws Exception;
    void actualizar(Producto prod) throws Exception;
    List<Producto> listarProductos() throws Exception;
}
