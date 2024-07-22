package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conect {
	
	Connection con;
	public Conect() {
		try {
			// Cargar el controlador correcto para SQL Server
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://192.168.100.118:1433;"
					+ "database=gestion_paquetes;"
					+ "user=;" // TU USER
					+ "password=;" // TU CLAVE
					+ "encrypt=true;"
					+ "trustServerCertificate=true;"
					+ "loginTimeout=30;");


			Statement statement = con.createStatement();
			String selectSql = "SELECT * FROM lugar";
			ResultSet resultSet = statement.executeQuery(selectSql);


			while (resultSet.next()) {
				int id = resultSet.getInt("id_lugar");
				String nombre = resultSet.getString("nombre_lugar");
				System.out.println("ID: " + id + ", Nombre: " + nombre);
			}
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
	}

	public static void main(String[] args) {
		Conect cn = new Conect();
	}
}

