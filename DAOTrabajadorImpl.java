package DAO;

import Clases.Conexion;
import Clases.Empresa;
import Clases.Trabajador;
import Interfaces.DAOTrabajador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOTrabajadorImpl implements DAOTrabajador{
    private Conexion conexion = new Conexion();
    private Connection getCon = conexion.getConexion();

    public DAOTrabajadorImpl() throws SQLException {}

    @Override
    public List<Trabajador> listarTrabajadores() throws Exception {
        DAOEmpresaImpl daoEmpresa = new DAOEmpresaImpl();
        Trabajador trabajador = new Trabajador();
        List<Trabajador> list = new ArrayList<>();
        PreparedStatement ps = getCon.prepareStatement("SELECT trab.*, trabEmp.CIF_Empresa FROM Trabajador AS trab" +
                " INNER JOIN Traj_Empresa AS trabEmp ON trabEmp.DNI_Trabajador = trab.DNI");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Empresa empresa = new Empresa();
            empresa.setCIF(rs.getInt(5));
            trabajador = new Trabajador(rs.getString(1), rs.getString(2), rs.getString(3),
                     rs.getInt(4), empresa);
            trabajador.setEmpresa(daoEmpresa.getEmpresa(trabajador));
            list.add(trabajador);
        }
        return list;
    }
}
