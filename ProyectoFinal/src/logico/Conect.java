package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conect {

    private static Connection con = null;

    public static Connection getConnection() {
        if (con == null || isClosed()) {
            try {
                // Cargar el controlador correcto para SQL Server
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection("jdbc:sqlserver://192.168.100.118:1433;"
                    + "database=Empresa_Software;"
                    + "user=d.morfe;" // TU USER
                    + "password=Win221983;" // TU CLAVE
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;");
            } catch (Exception e) {
                System.err.println("Error: " + e);
            }
        }
        return con;
    }

    private static boolean isClosed() {
        try {
            return con == null || con.isClosed();
        } catch (SQLException e) {
            return true;
        }
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
