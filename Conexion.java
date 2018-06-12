package Clases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;



public class Conexion {
    private Connection conexion;

    private String DB_URL = "jdbc:mysql://172.16.10.36/PracticaFinal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String USER = "test";
    private String PASS = new String(Base64.getDecoder().decode("dGVzdA=="));

    public Connection getConexion() throws SQLException {
        return conexion = DriverManager.getConnection(DB_URL,USER,PASS);
    }
}
