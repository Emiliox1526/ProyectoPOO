package logico;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conect {
	
	private static Connection connection;
	private static final String URL = "jdbc:sqlserver://192.168.100.118:1433;"
			+ "database=Empresa_Software;"
			+ "user=d.morfe;" 
			+ "password=Win221983;" 
			+ "encrypt=true;"
			+ "trustServerCertificate=true;"
			+ "loginTimeout=30;";
	private static final String USER = "d.morfe";
	private static final String PASSWORD = "Win221983";
	
	public Conect() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(URL);
			System.out.println("Conexión exitosa a la base de datos");
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
	}
	
    public static Connection getConnection() {
        if (connection == null) {
            initialize();
        }
        return connection;
    }
    
    public static void initialize() {
        if (connection == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(URL);
                System.out.println("Conexión exitosa a la base de datos");
            } catch (Exception e) {
                System.err.println("Error: " + e);
            }
        }
    }

	public static void main(String[] args) {
		Conect cn = new Conect();
	}
}
