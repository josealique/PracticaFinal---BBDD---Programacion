package DAO;

import Clases.Conexion;
import Clases.Empresa;
import Clases.Trabajador;
import Interfaces.DAOEmpresa;
import java.sql.*;
import java.util.*;

public class DAOEmpresaImpl implements DAOEmpresa {

    private Conexion conexion = new Conexion();
    private Connection getCon = conexion.getConexion();

    public DAOEmpresaImpl() throws SQLException {}

    @Override
    public void insertar(Empresa empresa) throws Exception {
        PreparedStatement ps = getCon.prepareStatement("INSERT INTO Empresa(Telefono, Nombre, Direccion, Estado) VALUES(?,?,?,?)");
        ps.setInt(1,empresa.getTelefono());
        ps.setString(2,empresa.getNombre());
        ps.setString(3,empresa.getDireccion());
        ps.setString(4,empresa.getEstado());
    }

    @Override
    public List<Empresa> listarEmpresas() throws Exception {
        List<Empresa> list = new ArrayList<>();
        PreparedStatement ps = getCon.prepareStatement("SELECT * FROM Empresa");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Empresa empresa = new Empresa(rs.getInt(1), rs.getInt(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6));
            list.add(empresa);
        }
        return list;
    }

    public Empresa getEmpresa(Trabajador seleccionTrabajador) throws SQLException {
        PreparedStatement ps = getCon.prepareStatement("SELECT * FROM Empresa WHERE CIF=?");
        System.out.println(seleccionTrabajador);
        ps.setInt(1, seleccionTrabajador.getEmpresa().getCIF());
        ResultSet rs = ps.executeQuery();
        Empresa empresa = new Empresa();
        while (rs.next()){
            empresa.setCIF(rs.getInt(1));
            empresa.setTelefono(rs.getInt(2));
            empresa.setNombre(rs.getString(3));
            empresa.setDireccion(rs.getString(4));
            empresa.setEstado(rs.getString(5));
            empresa.setCampoObservaciones(rs.getString(6));
        }
        return empresa;
    }
}