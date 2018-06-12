package DAO;

import Clases.*;
import Interfaces.DAOPresupuesto;

import java.sql.*;
import java.util.*;

public class DAOPresupuestoImpl implements DAOPresupuesto {

    // Creamos la variable de conexion
    private Conexion conexion = new Conexion();
    private Connection getCon = conexion.getConexion();

    public DAOPresupuestoImpl() throws SQLException {
    }

    @Override
    public void insertar(Presupuesto presup) throws Exception {
        // Preparamos la query que insertará en la tabla de Presupuesto (de la Base de Datos) los valores que nosotros queramos
        PreparedStatement ps = getCon.prepareStatement("INSERT INTO Presupuesto (Fecha,DNI_Cliente, DNI_Trabajador, CIF_Empresa)" +
                " VALUES(now(),?,?,?)");
        // Cogemos los valores uno por uno
        ps.setString(1, presup.getCliente().getDNI());
        ps.setString(2, presup.getTrabajador().getDNI());
        ps.setInt(3, presup.getEmpresa().getCIF());
        System.out.println(presup);

        ps.execute();
        // Cogeremos la ultima ID insertada en la base de datos
        PreparedStatement getID = getCon.prepareStatement("SELECT LAST_INSERT_ID()");
        // Ejecutamos un ResultSet para poder recorrer el PreparedStatement y coger cada valor
        ResultSet rs = getID.executeQuery();
        // Cogemos la siguiente ID
        rs.next();
        // Preparamos la query que insertara los datos en la tabla que contiene la Interrelación entre Producto y Presupuesto
        PreparedStatement ps1 = getCon.prepareStatement("INSERT INTO Producto_Presup(Id_Producto, Id_Presupuesto, Cantidad) VALUES(?,?,?)");
        for (Producto prod : presup.getProducto()) {
            ps1.setInt(1, prod.getId());
            ps1.setInt(2, rs.getInt(1));
            ps1.setInt(3, prod.getCantidad());
            ps1.execute();
            System.out.println(prod);
        }
    }

    @Override
    public void eliminar(Presupuesto presup) throws Exception {
        // Realizamos las querys que eliminaran los productos y los presupuestos asociados a una ID
        PreparedStatement ps = getCon.prepareStatement(" DELETE FROM Producto_Presup WHERE Id_Producto = ?");
        ps.setInt(1, 1);
        PreparedStatement ps1 = getCon.prepareStatement(" DELETE FROM Presupuesto WHERE Id = ?");
        ps1.setInt(1, presup.getId());
        // Ejecutamos las Querys
        ps.execute();
        ps1.execute();
    }

    @Override
    public void actualizar(Presupuesto presup) throws Exception {
        try {
            // Preparamos la query que actualizará el producto
            PreparedStatement ps = getCon.prepareStatement("UPDATE Producto SET Cantidad = " + presup.getCantidad() + " " +
                    " WHERE Id = " + presup.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Presupuesto> listarPresupuestos() throws Exception {
        // Creamos una lista de Presupuestos
        List<Presupuesto> list = new ArrayList<>();
        // Preparamos la query donde tendremos que hacer una serie de INNER JOIN para poder recabar la información necesaria
        // sobre la Empresa, el Cliente, el Trabajador y el Presupuesto
        PreparedStatement ps = getCon.prepareStatement(" SELECT emp.*, cli.*, trab.*, presup.* FROM Presupuesto AS presup " +
                " INNER JOIN Producto_Presup AS prodPre ON prodPre.Id_Presupuesto=presup.Id " +
                " INNER JOIN Empresa AS emp ON emp.CIF=presup.CIF_Empresa " +
                " INNER JOIN Cliente AS cli ON cli.DNI=presup.DNI_Cliente " +
                " INNER JOIN Trabajador AS trab ON trab.DNI = presup.DNI_Trabajador ");
        // Ejecutamos un ResultSet que recorrerá la query y recogerá todos los campos que necesitamos
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            /*
            Antes de todo hay que ejecutar la query (en la base de datos) que hemos mencionado anteriormente para que
            así podamos coger los campos a nuestro gusto
            Creamos un objeto de tipo Empresa y cogeremos toda su información por orden
            */
            Empresa empresa = new Empresa();
            empresa.setCIF(rs.getInt(1));
            empresa.setTelefono(rs.getInt(2));
            empresa.setNombre(rs.getString(3));
            empresa.setDireccion(rs.getString(4));
            empresa.setEstado(rs.getString(5));
            empresa.setCampoObservaciones(rs.getString(6));
            // Creamos un objeto de tipo Cliente y cogeremos toda su información por orden
            Cliente cliente = new Cliente();
            cliente.setDNI(rs.getString(7));
            cliente.setNombre(rs.getString(8));
            cliente.setApellidos(rs.getString(9));
            cliente.setEmail(rs.getString(10));
            cliente.setTelefono(rs.getInt(11));
            // Creamos un objeto de tipo Trabajador y cogeremos toda su información por orden
            Trabajador trabajador = new Trabajador();
            trabajador.setDNI(rs.getString(12));
            trabajador.setNombre(rs.getString(13));
            trabajador.setApellidos(rs.getString(14));
            trabajador.setTelefono(rs.getInt(15));
            // Creamos un objeto de tipo Presupuesto y en este caso cuando no tengamos que almacenar mas informácion
            // propia del presupuesto, tendremos que hacer un setter del cliente, del trabajador y de la empresa, en dichos
            // setter tendremos que poner las variables que antes hemos creado de sus respectivas Clases
            Presupuesto presupuesto = new Presupuesto();
            presupuesto.setId(rs.getInt(16));
            presupuesto.setFecha(rs.getString(17));
            presupuesto.setCantidad(rs.getInt(18));
            presupuesto.setCliente(cliente);
            presupuesto.setTrabajador(trabajador);
            presupuesto.setEmpresa(empresa);
            presupuesto.setProducto(getProductos(presupuesto));
            list.add(presupuesto);
        }
        return list;
    }

    private List<Producto> getProductos(Presupuesto presupuesto) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        PreparedStatement ps = getCon.prepareStatement("SELECT prod.Id, prod.Nombre,prod.Stock, prodEmp.Precio, pp.Cantidad FROM Producto AS prod " +
                " INNER JOIN Producto_Presup AS pp ON pp.Id_Producto=prod.Id" +
                " INNER JOIN Presupuesto AS pre ON pre.Id=pp.Id_Presupuesto" +
                " INNER JOIN Pro_Empresa prodEmp ON prodEmp.Id_Producto=prod.Id" +
                " WHERE pre.Id = ?");
        ps.setInt(1,presupuesto.getId());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Producto p = new Producto();
            p.setId(rs.getInt(1));
            p.setNombre(rs.getString(2));
            p.setStock(rs.getInt(3));
            p.setPrecio(rs.getDouble(4));
            p.setCantidad(rs.getInt(5));
            productos.add(p);
        }
        return productos;
    }
}