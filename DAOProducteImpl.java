package DAO;
import Clases.Conexion;
import Clases.Producte;
import Interfaces.DAOProducte;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOProducteImpl implements DAOProducte {

    private Conexion conexion = new Conexion();
    private Connection getCon = conexion.getConexion();

    public DAOProducteImpl() throws SQLException {
    }

    @Override
    public void insertar(Producte prod) throws Exception {
        try {
            PreparedStatement ps = getCon.prepareStatement("INSERT INTO Producto(Nombre,Precio_Base,Stock) VALUES(?,?,?)");
            ps.setString(1,prod.getNombre());
            ps.setInt(2,prod.getPrecio_Base());
            ps.setInt(3,prod.getStock());
            ps.execute();

            PreparedStatement getID = getCon.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet rs = getID.executeQuery();
            // Cogemos la siguiente ID
            rs.next();
            PreparedStatement ps1 = getCon.prepareStatement("INSERT INTO Pro_Empresa(Id_Empresa,Id_Producto,Precio) VALUES(18783,?,?)");
            ps1.setInt(1,rs.getInt(1));
            ps1.setInt(2,rs.getInt(1));
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public void eliminar(Producte prod) throws Exception {
        try {
            // Preparamos las Querys
            PreparedStatement ps1 = getCon.prepareStatement("DELETE FROM Producto_Presup WHERE Id_Producto = ?");
            PreparedStatement ps = getCon.prepareStatement("DELETE FROM Producto WHERE Id = ?");
            // Escogemos los campos de la BBDD
            ps1.setInt(1,1);
            ps.setInt(1,prod.getId());
            // Ejecutamos las Querys
            ps1.execute();
            ps.execute();
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public void actualizar(Producte prod) throws Exception {
        try {
            PreparedStatement ps = getCon.prepareStatement("UPDATE Producto SET Id = '"+prod.getId()+"', " +
                    " Nombre = '"+prod.getNombre()+"', " +
                    " Precio_Base = '"+prod.getPrecio_Base()+"', Stock = '"+prod.getStock()+"' WHERE Id = ?");
            ps.setInt(1,prod.getId());
            ps.setString(1,prod.getNombre());
            ps.setInt(1,prod.getPrecio_Base());
            ps.setInt(1,prod.getStock());
            ps.execute();

            PreparedStatement getID = getCon.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet rs = getID.executeQuery();
            // Cogemos la siguiente ID
            rs.next();

            PreparedStatement ps1 = getCon.prepareStatement("UPDATE Producto_Presup SET Id_Producto = '"+rs.getInt(1)+"'");
            ps1.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Producte> listarProductos() throws Exception {
        List<Producte> list = new ArrayList<>();
        try {
            PreparedStatement ps = getCon.prepareStatement("SELECT prod.Id, prod.Nombre, prod.Precio_Base, prod.Stock FROM " +
                    "Producto prod INNER JOIN Empresa emp ON emp.Id = prod.Id");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Producte prod = new Producte();
                resultSet.getInt(1);
                resultSet.getString(1);
                resultSet.getInt(1);
                resultSet.getInt(1);
                list.add(prod);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
