package Interfaces;

import Clases.Presupuesto;

import java.util.List;

public interface DAOPresupuesto {
    void insertar(Presupuesto presup) throws Exception;
    void eliminar(Presupuesto presup) throws Exception;
    void actualizar(Presupuesto presup) throws Exception;
    List<Presupuesto> listarPresupuestos() throws Exception;
}
