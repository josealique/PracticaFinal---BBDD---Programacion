package database;

import java.sql.*;
import java.util.Base64;

public class Database {
    private Connection conn;

    public Database() throws SQLException {
        String DB_URL = "jdbc:mysql://172.16.8.13/PracticaFinal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String USER = "test";
        String PASS = new String(Base64.getDecoder().decode("dGVzdA=="));
        conn = DriverManager.getConnection(
                DB_URL,
                USER,
                PASS
        );
    }

    public Connection getConn(){
        return conn;
    }


//    public boolean comprobarCredenciales(String user, String password, String field) throws SQLException {
//        String sql = "SELECT * FROM " + field + " WHERE Pass='" + password + "' AND User='"+user+"'";
//        ps = conn.prepareStatement(sql);
//        return ps.executeQuery().isBeforeFirst();
//    }
//
//    public String getDepartment(String user, String pass) throws SQLException {
//        ps = conn.prepareStatement("SELECT dpt.Nombre FROM Departamento as dpt " +
//                "inner join Trabajador as trab on trab.Id_Departamento=dpt.Id " +
//                "WHERE trab.Pass='" + pass + "' AND trab.User='" + user +"'");
//        String dept = "";
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()){
//            dept = rs.getString(1);
//        }
//        return dept;
//    }
}
