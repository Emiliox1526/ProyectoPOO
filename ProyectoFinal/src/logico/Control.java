package logico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Control {
    private static Control instance;
    private List<User> users;
    private static User loginUser;
    static Connection con = Conect.getConnection();

    private Control() {
        users = new ArrayList<>();
        initializeDatabase();
    }

    public static Control getInstance() {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }
    
	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		Control.loginUser = loginUser;
	}

    private void initializeDatabase() {
        try {
            Statement statement = con.createStatement();
            String selectSql = "SELECT * FROM Usuario";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("pass");
                String tipo = resultSet.getString("tipo");
                User user = new User(tipo, username, password);
                users.add(user);
            }
            System.out.println("Usuarios cargados: ");
            for (User user : users) {
                System.out.println("Username: " + user.getUserName() + ", Tipo: " + user.getTipo());
            }
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e);
        }
    }

    public List<User> getMisUsers() {
        return users;
    }



    public boolean confirmLogin(String username, String password) {
        System.out.println("Intento de login con usuario: " + username);
        String sqlInsertControl = "INSERT INTO Control_usuario (username, fecha_hora) VALUES (?, getdate())";
        String sqlInsertContro2 = "INSERT INTO Control_Acceso (tipo) VALUES (?)";

        
        for (User user : users) {
            System.out.println("Usuario en la base de datos: " + user.getUserName());
            if (user.getUserName().equals(username)) {
                System.out.println("Usuario encontrado");
                if (user.getPass().equals(password)) {
                    System.out.println("Contraseña correcta");
                    loginUser = user;
                    try (PreparedStatement pstmt = con.prepareStatement(sqlInsertControl)) {
                        pstmt.setString(1, username);
                        pstmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                    try (PreparedStatement pstmt = con.prepareStatement(sqlInsertContro2)) {
                        pstmt.setString(1, "E");
                        pstmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                    return true;
                } else {
                    System.out.println("Contraseña incorrecta");
                }
            }
        }
        System.out.println("Usuario no encontrado");
        return false;
    }
}