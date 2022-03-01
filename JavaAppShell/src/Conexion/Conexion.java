package Conexion;

import java.sql.*;

/**
 *
 * @author Java-Shell
 */
public class Conexion {

    Connection con;

    public Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql:// Server: sql3.freemysqlhosting.net:3306/ sql3475611", "sql3475611", "Ld1FUlXC1e");
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public Connection getConnection() {
        return con;
    }

}
