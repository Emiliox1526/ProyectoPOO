package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion_Trabajadores {
    
    Connection con;
    
    public Conexion_Trabajadores() {
        try {
            // Cargar el controlador correcto para SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://192.168.100.118:1433;"
                    + "database=Final_Proyect;"
                    + "user=d.morfe;" // TU USER
                    + "password=Win221983;" // TU CLAVE
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
    
    // Método para insertar un nuevo trabajador
    public void insertarTrabajador(String cedula, String nombre, java.sql.Date fechaNacimiento, String sexo, String direccion, String username) {
        String sql = "INSERT INTO Trabajador (cedula, nombre, fechaNacimiento, sexo, direccion, username) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, cedula);
            pstmt.setString(2, nombre);
            pstmt.setDate(3, fechaNacimiento);
            pstmt.setString(4, sexo);
            pstmt.setString(5, direccion);
            pstmt.setString(6, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
    }
    
    // Método para actualizar los datos de un trabajador
    public void actualizarTrabajador(String cedula, String nombre, java.sql.Date fechaNacimiento, String sexo, String direccion, String username) {
        String sql = "UPDATE Trabajador SET nombre = ?, fechaNacimiento = ?, sexo = ?, direccion = ?, username = ? WHERE cedula = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setDate(2, fechaNacimiento);
            pstmt.setString(3, sexo);
            pstmt.setString(4, direccion);
            pstmt.setString(5, username);
            pstmt.setString(6, cedula);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
    }
    
    // Método para eliminar un trabajador
    public void eliminarTrabajador(String cedula) {
        String sql = "DELETE FROM Trabajador WHERE cedula = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, cedula);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
    }
    
    // Método para cargar y mostrar todos los trabajadores
    public void cargarTrabajadores() {
        String sql = "SELECT * FROM Trabajador";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                java.sql.Date fechaNacimiento = rs.getDate("fechaNacimiento");
                String sexo = rs.getString("sexo");
                String direccion = rs.getString("direccion");
                String username = rs.getString("username");
                System.out.println("Cedula: " + cedula + ", Nombre: " + nombre + ", Fecha de Nacimiento: " + fechaNacimiento + ", Sexo: " + sexo + ", Direccion: " + direccion + ", Username: " + username);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
    }

}
