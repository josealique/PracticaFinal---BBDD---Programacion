package Interfaces;

import Clases.Trabajador;
import java.util.List;

public interface DAOTrabajador {
     List<Trabajador> listarTrabajadores () throws Exception;
}
