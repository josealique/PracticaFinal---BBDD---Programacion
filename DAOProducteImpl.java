package DAO;

import Clases.Conexion;
import Clases.Empresa;
import Clases.Producto;
import Interfaces.DAOProducte;
import java.sql.*;
import java.util.*;

public class DAOProducteImpl implements DAOProducte {

    private Conexion conexion = new Conexion();
    private Connection getCon = conexion.getConexion();

    public DAOProducteImpl() throws SQLException {}

    @Override
    public void insertar(Producto prod) throws Exception {
        try {
            // Preparamos las querys
            PreparedStatement ps = getCon.prepareStatement("INSERT INTO Producto(Nombre,Stock) VALUES(?,?)");
            // Cogemos los valores correspondientes de la tabla
            ps.setString(1, prod.getNombre());
            ps.setInt(2, prod.getStock());
            ps.execute();
            // Cogeremos la ultima ID insertada en la base de datos
            PreparedStatement getID = getCon.prepareStatement("SELECT LAST_INSERT_ID()");
            // Ejecutamos un ResultSet para poder recorrer el PreparedStatement y coger cada valor
            ResultSet rs = getID.executeQuery();
            // Cogemos la siguiente ID
            rs.next();
            // Insertamos en la tabla Pro_Empresa sus valores correspondientes, ya que esta tabla es la que contiene el precio del producto
            // según la empresa
            PreparedStatement ps1 = getCon.prepareStatement("INSERT INTO Pro_Empresa(CIF_Empresa,Id_Producto,Precio) VALUES(?,?,?)");
            ps1.setInt(1,prod.getEmpresa().getCIF());
            ps1.setInt(2, rs.getInt(1));
            ps1.setDouble(3, prod.getPrecio());
            ps1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Producto prod) throws Exception {
        try {
            // Preparamos las Querys
            PreparedStatement ps1 = getCon.prepareStatement("DELETE FROM Pro_Empresa WHERE Id_Producto = ?");
            PreparedStatement ps = getCon.prepareStatement("DELETE FROM Producto WHERE Id = ?");
            // Escogemos los campos de la BBDD
            ps1.setInt(1, 1);
            ps.setInt(1, prod.getId());
            // Ejecutamos las Querys
            ps1.execute();
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Producto producto) throws Exception {
        try {
            // Preparamos la query que actualizará el producto
            PreparedStatement ps = getCon.prepareStatement("UPDATE Producto SET Nombre = ?," +
                    " Stock = ?" + " WHERE Id = ? ");
            ps.setString(1,producto.getNombre());
            ps.setInt(2,producto.getStock());
            ps.setInt(3,producto.getId());
            ps.execute();

            PreparedStatement ps1 = getCon.prepareStatement("UPDATE Pro_Empresa SET CIF_Empresa = ?," +
                    "Precio = ? WHERE Id_Producto = ?");
            ps1.setInt(1,producto.getEmpresa().getCIF());
            ps1.setDouble(2,producto.getPrecio());
            ps1.setInt(3,producto.getId());
            ps1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> listarProductos() throws Exception {
        // Creamos una lista de Objetos de tipo Empresa
        List<Producto> list = new ArrayList<>();
        // Hacemos un INNER JOIN entre Producto, Empresa y entre el Producto_Empresa
        PreparedStatement ps = getCon.prepareStatement("SELECT emp.*, prod.Id, prod.Nombre, prod.Stock, prodEmp.Precio FROM Producto AS prod" +
                " INNER JOIN Pro_Empresa prodEmp ON prodEmp.Id_Producto = prod.Id" +
                " INNER JOIN Empresa emp on emp.CIF = prodEmp.CIF_Empresa");
        ResultSet resultSet = ps.executeQuery();
        // Recorremos la query para poder coger cada valor
        while (resultSet.next()) {
            // Creamos un objeto de tipo Empresa
            Empresa empresa = new Empresa();
            // Cogemos los valores correspondientes de cada fila de la tabla Empresa
            empresa.setCIF(resultSet.getInt(1));
            empresa.setTelefono(resultSet.getInt(2));
            empresa.setNombre(resultSet.getString(3));
            empresa.setDireccion(resultSet.getString(4));
            empresa.setEstado(resultSet.getString(5));
            empresa.setCampoObservaciones(resultSet.getString(6));
            // Creamos un objeto de tipo Producto
            Producto prod = new Producto();
            // Cogemos los valores correspondientes de cada fila de la tabla Producto
            prod.setId(resultSet.getInt(7));
            prod.setNombre(resultSet.getString(8));
            prod.setStock(resultSet.getInt(9));
            prod.setPrecio(resultSet.getDouble(10));
            // Por ultimo le añadimos la empresa correspondiente a ese producto
            prod.setEmpresa(empresa);
            // Añadimos a la lista el producto creado anteriormente
            list.add(prod);
        }
        return list;
    }
}