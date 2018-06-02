package Interfaces;

import Clases.Producte;

import java.util.List;

public interface DAOProducte {
    public void insertar (Producte prod) throws Exception;
    public void eliminar (Producte prod) throws Exception;
    public void actualizar (Producte prod) throws Exception;
    public List<Producte> listarProductos () throws Exception;
}
