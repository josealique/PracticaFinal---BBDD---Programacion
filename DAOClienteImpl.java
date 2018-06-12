package DAO;

import Clases.Cliente;
import Clases.Conexion;
import Interfaces.DAOCliente;
import java.sql.*;
import java.util.*;

public class DAOClienteImpl implements DAOCliente {
    private Conexion conexion = new Conexion();
    private Connection getCon = conexion.getConexion();

    public DAOClienteImpl() throws SQLException {}

    @Override
    public void insertar(Cliente cliente) throws Exception {
        PreparedStatement prs = getCon.prepareStatement("INSERT INTO Cliente(DNI,Nombre,Apellidos,Email,Telefono) VALUES(?,?,?,?,?)");
        prs.setString(1, cliente.getDNI());
        prs.setString(2, cliente.getNombre());
        prs.setString(3, cliente.getApellidos());
        prs.setString(4, cliente.getEmail());
        prs.setInt(5, cliente.getTelefono());
        prs.execute();
    }

    @Override
    public List<Cliente> listarClientes() throws Exception {
        List<Cliente> list = new ArrayList<>();
        PreparedStatement ps = getCon.prepareStatement("SELECT * FROM Cliente");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Cliente cliente = new Cliente(rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getInt(5), rs.getString(4));
            list.add(cliente);
        }
        return list;
    }
}
