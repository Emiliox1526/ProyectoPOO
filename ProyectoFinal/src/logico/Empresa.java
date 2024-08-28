package logico;

import java.awt.List;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;

public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Cliente> misClientes;
    private ArrayList<Contrato> misContratos;
    private ArrayList<Trabajador> misTrabajadores;
    private ArrayList<Proyecto> misProyectos;
    private ArrayList<User> misUsuarios;
    private ArrayList<Log> misLogs;
    private static Empresa empresa = null;
    public int cantContrato;
    public int cantProyecto;
    public int cantCliente;
    public int cantTrabajador;
    public int contratoid;
    public int proyectoid;

    private static Connection con;

    public Empresa() {
        super();
        misClientes = new ArrayList<>();
        misContratos = new ArrayList<>();
        misTrabajadores = new ArrayList<>();
        misProyectos = new ArrayList<>();
        misUsuarios = new ArrayList<>();
        misLogs = new ArrayList<>();
    }

    public static Empresa getInstance() { 
        if (empresa == null) {
            empresa = new Empresa();
            con = Conect.getConnection();
        }
        return empresa;
    }
    
    public ArrayList<Log> getMisLogs(){
    	empresa.loadLog();
		return misLogs;
    }
   

	public ArrayList<User> getMisUsuarios(){
    	empresa.loadUser();
		return misUsuarios;
    }

    public ArrayList<Cliente> getMisClientes() {
    	empresa.loadClientes();
        return misClientes;
    }

    public void setMisClientes(ArrayList<Cliente> misClientes) {
        this.misClientes = misClientes;
    }

    public ArrayList<Contrato> getMisContratos() {
    	empresa.loadContratos();;
        return misContratos;
    }

    public void setMisContratos(ArrayList<Contrato> misContratos) {
        this.misContratos = misContratos;
    }

    public ArrayList<Trabajador> getMisTrabajadores() {
        empresa.loadTrabajadores();
        for (Trabajador trabajador : misTrabajadores) {
            System.out.println("Trabajador: " + trabajador.getNombre() + " " + trabajador.getApellidos() +
                               ", Cédula: " + trabajador.getCedula() +
                               ", Dirección: " + trabajador.getDireccionParticular() +
                               ", Sexo: " + trabajador.getSexo() +
                               ", Fecha de Nacimiento: " + trabajador.getFechaDeNacimiento());
        }
        return misTrabajadores;
    }


    public void setMisTrabajadores(ArrayList<Trabajador> misTrabajadores) {
        this.misTrabajadores = misTrabajadores;
    }

    public ArrayList<Proyecto> getMisProyectos() {
    	empresa.loadProyectos();
        return misProyectos;
    }

    public void setMisProyectos(ArrayList<Proyecto> misProyectos) {
        this.misProyectos = misProyectos;
    }

    public static Empresa getEmpresa() {
        return empresa;
    }

    public static void setEmpresa(Empresa empresa) {
        Empresa.empresa = empresa;
    }


    public void ingresarCliente(Cliente cliente) {

        saveCliente(cliente); 
        cantCliente++;
    }

    public void eliminarCliente(String idCliente) {
        for (int i = misClientes.size() - 1; i >= 0; i--) {
            Cliente cliente = misClientes.get(i);
            if (cliente.getId().equalsIgnoreCase(idCliente)) {
                misClientes.remove(i);
                deleteCliente(idCliente); 
                cantCliente--;
            }
        }
    }

    public void ingresarContrato(Contrato contrato) {
        misContratos.add(contrato);
        saveContrato(contrato); 
        cantContrato++;
    }

    public void eliminarContrato(String idContrato) {
        for (int i = misContratos.size() - 1; i >= 0; i--) {
            Contrato contrato = misContratos.get(i);
            if (contrato.getId().equalsIgnoreCase(idContrato)) {
                misContratos.remove(i);
                deleteContrato(idContrato); 
                cantContrato--;
            }
        }
    }

    public void ingresarProyecto(Proyecto proyecto) {
        misProyectos.add(proyecto);
        saveProyecto(proyecto); 
        cantProyecto++;
    }

    public void eliminarProyecto(int idProyecto) {
        for (int i = misProyectos.size() - 1; i >= 0; i--) {
            Proyecto proyecto = misProyectos.get(i);
            if (proyecto.getId() == idProyecto) {
                deleteProyecto(idProyecto); 
            }
        }
    }

    public void ingresarTrabajador(Trabajador trabajador) {
        saveTrabajador(trabajador); 
    }

    public void eliminarTrabajador(String cedulaTrabajador) {
        for (int i = misTrabajadores.size() - 1; i >= 0; i--) {
            Trabajador trabajador = misTrabajadores.get(i);
            if (trabajador.getCedula().equalsIgnoreCase(cedulaTrabajador)) {
                misTrabajadores.remove(i);
                deleteTrabajador(cedulaTrabajador); 
                cantTrabajador--;
            }
        }
    }

    public void loadData() {
        loadClientes();
        loadContratos();
        loadProyectos();
        loadTrabajadores();
    }

    private void loadLog() {
        con = Conect.getConnection();
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Control_usuario ORDER BY fecha_hora DESC");
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String username = rs.getString("username");
                String id = rs.getString("id");
                Date timestamp = rs.getDate("fecha_hora"); 
                Date fyh = new Date(timestamp.getTime());
                
                Log l = new Log(username, id, fyh);
                misLogs.add(l);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void loadClientes() {
    	con = Conect.getConnection();
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Cliente");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                Cliente cliente = new Cliente(id, nombre, apellido, direccion);
                misClientes.add(cliente);
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadContratos() {
    	con = Conect.getConnection();
        try (PreparedStatement stmt = con.prepareStatement("SELECT p.id,p.fechaIni,p.fechaFin,c.id_cliente,c.nombre,Proyecto.id_proyecto FROM Proyecto p"
        		+ "JOIN Cliente c on p.id_cliente = c.id_cliente"
        		+ "JOIN Contratoc on p.id_proyecto = c.id contrato");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Date fechaIni = rs.getDate("fechaIni");
                Date fechaFinC = rs.getDate("fechaFin");
                String nombre = rs.getString("nombre");
                int id_cliente = rs.getInt("id_cliente");
                int id_proyecto = rs.getInt("id_proyecto");
                Contrato contrato = new Contrato(id_cliente, nombre, fechaIni, fechaFinC, id_proyecto);
                misContratos.add(contrato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadProyectos() {
    	con = Conect.getConnection();
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Proyecto");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idProyecto = rs.getInt("id_proyecto");
                Date fechaInicio = rs.getDate("fechaInicio");
                Date fechaFin = rs.getDate("fechaFin");
                Date fechaProrroga = rs.getDate("fechaProrroga");
                boolean isPenalizado = rs.getString("isPenalizado").equalsIgnoreCase("s");
                
                Cliente cliente = loadClienteById(idProyecto);

                Contrato contrato = loadContratoByProyectoId(idProyecto);
                
                ArrayList<Trabajador> participantes = loadTrabajadoresByProyectoId(idProyecto);
                
                Proyecto proyecto = new Proyecto(cliente, participantes, contrato, 
                fechaInicio, fechaInicio, fechaFin, fechaProrroga, isPenalizado);
                misProyectos.add(proyecto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void loadUser() {
    	con = Conect.getConnection();
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Usuario");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String Username = rs.getString("username");
                String pass = rs.getString("pass");
                String tipo = rs.getString("tipo");
                User usuario = new User(Username, pass, tipo);
                misUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Trabajador> loadTrabajadoresByProyectoId(int idProyecto) {
    	con = Conect.getConnection();
        ArrayList<Trabajador> trabajadores = new ArrayList<>();
        String sql = "SELECT t.* FROM Trabajador t "
                   + "JOIN Proyecto_Trabajador pt ON t.cedula = pt.cedula "
                   + "WHERE pt.id_proyecto = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idProyecto);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                	String cedula = rs.getString("cedula");
                    String nombre = rs.getString("nombre");
                    String apellidos = rs.getString("apellido");
                    Date fechaNacimiento = rs.getDate("fechaNacimiento");
                    String sexo = rs.getString("sexo");
                    String direccion = rs.getString("direccion");
                    Trabajador trabajador = new Trabajador(cedula, nombre,apellidos ,direccion, sexo, fechaNacimiento," ", 0, 0);
                    trabajadores.add(trabajador);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trabajadores;
    }

    public void loadTrabajadores() {
        con = Conect.getConnection();
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Trabajador");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellido");
                Date fechaNacimiento = rs.getDate("fechaNacimiento");
                String sexo = rs.getString("sexo");
                String direccion = rs.getString("direccion");

                String tipo = getTipoTrabajador(cedula);
                Trabajador trabajador = null;

                if(tipo == "Programador") {
                        ArrayList<String> lenguajes = new ArrayList<>();
                        try (PreparedStatement stmt1 = con.prepareStatement(
                            "SELECT lp.nombre FROM Programador_Lenguaje pl " +
                            "JOIN LenguajeProgramacion lp ON pl.id_lenguaje = lp.id " +
                            "WHERE pl.cedula = ?")) {
                            stmt1.setString(1, cedula);
                            try (ResultSet rs1 = stmt1.executeQuery()) {
                                while (rs1.next()) {
                                    String lenguaje = rs1.getString("nombre");
                                    lenguajes.add(lenguaje);
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        trabajador = new Programador(cedula, nombre, apellidos, direccion, sexo, fechaNacimiento, " ", 0, 0, lenguajes);
                }if(tipo == "Jefe") {
                        int cantTrab = 0;
                        try (PreparedStatement stmt1 = con.prepareStatement("SELECT * FROM Jefe WHERE cedula = ?")) {
                            stmt1.setString(1, cedula);
                            try (ResultSet rs1 = stmt1.executeQuery()) {
                                if (rs1.next()) {
                                    cantTrab = rs1.getInt("cantidad_trabajadores");
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        trabajador = new Jefe(cedula, nombre, apellidos, direccion, sexo, fechaNacimiento, " ", 0, 0, cantTrab);
                }if(tipo == "Planificador") {
                        int frecuenciaPlanificacion = 0;
                        try (PreparedStatement stmt1 = con.prepareStatement("SELECT * FROM Planificador WHERE cedula = ?")) {
                            stmt1.setString(1, cedula);
                            try (ResultSet rs1 = stmt1.executeQuery()) {
                                if (rs1.next()) {
                                    frecuenciaPlanificacion = rs1.getInt("frecuencia_planificacion");
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        trabajador = new Planificador(cedula, nombre, apellidos, direccion, sexo, fechaNacimiento, " ", 0, 0, frecuenciaPlanificacion);
                }if(tipo == "Diseniador") {
                        int anosExperiencia = 0;
                        try (PreparedStatement stmt1 = con.prepareStatement("SELECT * FROM Disenador WHERE cedula = ?")) {
                            stmt1.setString(1, cedula);
                            try (ResultSet rs1 = stmt1.executeQuery()) {
                                if (rs1.next()) {
                                    anosExperiencia = rs1.getInt("anos_experiencia");
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        trabajador = new Diseñador(cedula, nombre, apellidos, direccion, sexo, fechaNacimiento, " ", 0, 0, anosExperiencia);
                }

                if (trabajador != null) {
                    misTrabajadores.add(trabajador);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getTipoTrabajador(String cedula) throws SQLException {
        String tipo = null;
        
        String[] tipos = {"Programador", "Jefe", "Planificador", "Diseniador"};
        for (String t : tipos) {
            try (PreparedStatement stmt = con.prepareStatement("SELECT 1 FROM " + t + " WHERE cedula = ?")) {
                stmt.setString(1, cedula);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        tipo = t;
                        break;
                    }
                }
            }
        }
        
        return tipo;
    }


    public Cliente loadClienteById(int idProyecto) {
    	con = Conect.getConnection();
        Cliente cliente = null;
        String sql = "SELECT c.* FROM Cliente c "
                   + "JOIN Proyecto_Cliente pc ON c.id_cliente = pc.id_cliente "
                   + "WHERE pc.id_proyecto = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idProyecto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                	String id = rs.getString("id_cliente");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String direccion = rs.getString("direccion");
                    cliente = new Cliente(id, nombre, apellido, direccion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    private Contrato loadContratoByProyectoId(int idProyecto) {
    	con = Conect.getConnection();
        Contrato contrato = null;
        String sql = "SELECT p.id,p.fechaIni,p.fechaFin,c.id_cliente,c.nombre,Proyecto.id_proyecto FROM Proyecto p"
        		+ "JOIN Cliente c on p.id_cliente = c.id_cliente"
        		+ "JOIN Contratoc on p.id_proyecto = c.id contrato";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idProyecto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Date fechaIni = rs.getDate("fechaIni");
                    Date fechaFinC = rs.getDate("fechaFin");
                    String nombre = rs.getString("nombre");
                    int id_cliente = rs.getInt("id_cliente");
                    int id_proyecto = rs.getInt("id_proyecto");
                    contrato = new Contrato(id_cliente, nombre, fechaIni, fechaFinC, id_proyecto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contrato;
    }

    private void saveCliente(Cliente cliente) {
    	con = Conect.getConnection();
        String sql = "INSERT INTO Cliente (id_cliente, nombre, apellido, direccion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cliente.getId());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellido());
            stmt.setString(4, cliente.getDireccion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Conect.closeConnection();
        }
    }

    private void saveContrato(Contrato contrato) {
    	con = Conect.getConnection();
        String sql = "INSERT INTO Contrato (id, id_cliente, nombre, fechaEntrega, fechaInicio, id_proyecto) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(2, contrato.getIdCliente());
            stmt.setString(3, contrato.getNombre());
            stmt.setDate(4, (Date) contrato.getFechaEntrega());
            stmt.setDate(5, (Date) contrato.getFechaInicio());
            stmt.setInt(6, contrato.getId_proyecto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveProyecto(Proyecto proyecto) {
    	con = Conect.getConnection();
        String sql = "INSERT INTO Proyecto (id_proyecto, fechaInicio, fechaFin, fechaProrroga, isPenalizado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(2, proyecto.getFechaInicio());
            stmt.setDate(3, proyecto.getFechaInicio());
            stmt.setDate(4, proyecto.getFechaProrroga());
            stmt.setString(5, proyecto.isPenalizado() ? "s" : "n");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveTrabajador(Trabajador trabajador) {
        String default_password = "12345";
        String tipo_usuario = "Usuario";

        String sqlUsuario = "INSERT INTO Usuario (username, pass, tipo) VALUES (?, ?, ?)";
        String sqlTrabajador = "INSERT INTO Trabajador (cedula, nombre, apellido, fechaNacimiento, sexo, direccion, username) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conect.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement pstUsuario = con.prepareStatement(sqlUsuario);
                 PreparedStatement pstTrabajador = con.prepareStatement(sqlTrabajador)) {

                pstUsuario.setString(1, trabajador.getCedula());
                pstUsuario.setString(2, default_password);
                pstUsuario.setString(3, tipo_usuario);
                pstUsuario.executeUpdate();

                pstTrabajador.setString(1, trabajador.getCedula());
                pstTrabajador.setString(2, trabajador.getNombre());
                pstTrabajador.setString(3, trabajador.getApellidos());
                pstTrabajador.setDate(4, trabajador.FechaDeNacimiento);
                pstTrabajador.setString(5, String.valueOf(trabajador.getSexo()));
                pstTrabajador.setString(6, trabajador.getDireccionParticular());
                pstTrabajador.setString(7, trabajador.getCedula());
                pstTrabajador.executeUpdate();

                con.commit(); 

            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void deleteCliente(String idCliente) {
    	con = Conect.getConnection();
        String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, idCliente);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteContrato(String idContrato) {
    	con = Conect.getConnection();
        String sql = "DELETE FROM Contrato WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, idContrato);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteProyecto(int idProyecto) {
    	con = Conect.getConnection();
        String sql = "DELETE FROM Proyecto WHERE id_proyecto = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idProyecto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteTrabajador(String cedulaTrabajador) {
    	con = Conect.getConnection();
        String sql = "DELETE FROM Trabajador WHERE cedula = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cedulaTrabajador);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProyecto(Proyecto proyecto, int id) {
        Connection con = Conect.getConnection();
        try {
            String sql = "UPDATE Proyecto SET fechaProrroga = ? WHERE id_proyecto = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDate(1, proyecto.getFechaProrroga());
            pstmt.setInt(2, id); 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }


	public Proyecto BuscarProyectoById(String idProyecto) {
	    con = Conect.getConnection();
	    Proyecto proyecto = null;
	    try {
	        String sql = "SELECT Proyecto.*, Contrato.*, Cliente.* FROM Proyecto "
	                   + "JOIN Contrato ON Proyecto.id_proyecto = Contrato.id_proyecto "
	                   + "JOIN Proyecto_cliente ON Proyecto.id_proyecto = Proyecto_cliente.id_proyecto "
	                   + "JOIN Cliente ON Proyecto_cliente.id_cliente = Cliente.id_cliente "
	                   + "WHERE Proyecto.id_proyecto = ?";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, idProyecto);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            int id_proyecto = rs.getInt("id_proyecto");
	            Date fechaIni = rs.getDate("fechaInicio");
	            Date fechaFin = rs.getDate("fechaFin");
	            Date fechaProrroga = rs.getDate("fechaProrroga");
	            boolean isPenalizado = rs.getString("isPenalizado").equalsIgnoreCase("s");

                Cliente cliente = buscarClientePorId(rs.getString("id_cliente"));

	            Contrato contrato = buscarContratoPorId(rs.getInt("id"));

	            ArrayList<Trabajador> trabajadores = buscarTrabajadoresPorProyecto(id_proyecto);

	            proyecto = new Proyecto(cliente, trabajadores, contrato, fechaIni, fechaFin, fechaFin, fechaProrroga, isPenalizado);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return proyecto;
	}

	public Contrato buscarContratoPorId(int idContrato) {
	    con = Conect.getConnection();
	    Contrato contrato = null;
	    try {
	        String sql = "SELECT Contrato.id, Contrato.fechaIni, Contrato.fechaFin, Proyecto.id_proyecto, Cliente.id_cliente, Cliente.nombre "
	                   + "FROM Contrato "
	                   + "JOIN Proyecto ON Proyecto.id_proyecto = Contrato.id_proyecto "
	                   + "JOIN Proyecto_cliente ON Proyecto_cliente.id_proyecto = Proyecto.id_proyecto "
	                   + "JOIN Cliente ON Cliente.id_cliente = Proyecto_cliente.id_cliente "
	                   + "WHERE Contrato.id = ?";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, idContrato);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            String nombreCliente = rs.getString("nombre");
	            Date fechaIni = rs.getDate("fechaIni");
	            Date fechaFin = rs.getDate("fechaFin");
	            int id_proyecto = rs.getInt("id_proyecto");
	            int id_cliente = rs.getInt("id_cliente");

	            contrato = new Contrato(id_cliente, nombreCliente,fechaIni, fechaFin, id_proyecto);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return contrato;
	}

	
	public ArrayList<Trabajador> buscarTrabajadoresPorProyecto(int i) {
		con = Conect.getConnection();
        ArrayList<Trabajador> trabajadores = new ArrayList<>();
        try {
            String sql = "SELECT t.* FROM Trabajador t INNER JOIN Proyecto_Trabajador pt ON t.cedula = pt.cedula WHERE pt.id_proyecto = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, i);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
            	String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellido");
                Date fechaNacimiento = rs.getDate("fechaNacimiento");
                String sexo = rs.getString("sexo");
                String direccion = rs.getString("direccion");
                Trabajador trabajador = new Trabajador(cedula, nombre,apellidos ,direccion, sexo, fechaNacimiento," ", 0, 0);
                misTrabajadores.add(trabajador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trabajadores;
    }
	
	

	public Cliente buscarClientePorId(String string) {
		con = Conect.getConnection();
        Cliente cliente = null;
        try {
            String sql = "SELECT * FROM Cliente WHERE id_cliente = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, string);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                    rs.getString("id_cliente"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("direccion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
	}

}
