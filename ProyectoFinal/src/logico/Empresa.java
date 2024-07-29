package logico;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Cliente> misClientes;
    private ArrayList<Contrato> misContratos;
    private ArrayList<Trabajador> misTrabajadores;
    private ArrayList<Proyecto> misProyectos;
    private static Empresa empresa = null;
    public static int idContrato;
    public static int idProyecto;
    public static int idCliente;
    public static int idTrabajador;
    public int cantContrato;
    public int cantProyecto;
    public int cantCliente;
    public int cantTrabajador;

    public Empresa() {
        super();
        misClientes = new ArrayList<>();
        misContratos = new ArrayList<>();
        misTrabajadores = new ArrayList<>();
        misProyectos = new ArrayList<>();
        idContrato = 1;
        idProyecto = 1;
        idCliente = 1;
        idTrabajador = 1; // Inicializa el idTrabajador
    }

    public static Empresa getInstance() {
        if (empresa == null) {
            empresa = new Empresa();
            empresa.loadData(); 
        }
        return empresa;
    }

    public ArrayList<Cliente> getMisClientes() {
        return misClientes;
    }

    public void setMisClientes(ArrayList<Cliente> misClientes) {
        this.misClientes = misClientes;
    }

    public ArrayList<Contrato> getMisContratos() {
        return misContratos;
    }

    public void setMisContratos(ArrayList<Contrato> misContratos) {
        this.misContratos = misContratos;
    }

    public ArrayList<Trabajador> getMisTrabajadores() {
        return misTrabajadores;
    }

    public void setMisTrabajadores(ArrayList<Trabajador> misTrabajadores) {
        this.misTrabajadores = misTrabajadores;
    }

    public ArrayList<Proyecto> getMisProyectos() {
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

    public static int getIdContrato() {
        return idContrato;
    }

    public static void setIdContrato(int idContrato) {
        Empresa.idContrato = idContrato;
    }

    public static int getIdProyecto() {
        return idProyecto;
    }

    public static void setIdProyecto(int idProyecto) {
        Empresa.idProyecto = idProyecto;
    }

    public static int getIdCliente() {
        return idCliente;
    }

    public static void setIdCliente(int idCliente) {
        Empresa.idCliente = idCliente;
    }

    public static int getIdTrabajador() {
        return idTrabajador;
    }

    public static void setIdTrabajador(int idTrabajador) {
        Empresa.idTrabajador = idTrabajador;
    }

    public void ingresarCliente(Cliente cliente) {
        misClientes.add(cliente);
        saveCliente(cliente); 
        idCliente++;
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
        idContrato++;
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
        idProyecto++;
        cantProyecto++;
    }

    public void eliminarProyecto(String idProyecto) {
        for (int i = misProyectos.size() - 1; i >= 0; i--) {
            Proyecto proyecto = misProyectos.get(i);
            if (proyecto.getId() == idProyecto) {
                misProyectos.remove(i);
                deleteProyecto(idProyecto); 
                cantProyecto--;
            }
        }
    }

    public void ingresarTrabajador(Trabajador trabajador) {
        misTrabajadores.add(trabajador);
        saveTrabajador(trabajador); 
        idTrabajador++;
        cantTrabajador++;
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

    private void loadClientes() {
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM Cliente");
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

    private void loadContratos() {
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM Contrato");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id");
                String idCliente = rs.getString("id_cliente");
                String nombre = rs.getString("nombre");
                Date fechaEntrega = rs.getDate("fechaEntrega");
                Date fechaInicio = rs.getDate("fechaInicio");
                int id_proyecto = rs.getInt("id_proyecto");
                Contrato contrato = new Contrato(id, idCliente, nombre, fechaEntrega, fechaInicio, id_proyecto);
                misContratos.add(contrato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadProyectos() {
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM Proyecto");
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
                
                Proyecto proyecto = new Proyecto(idProyecto, cliente, participantes, contrato, 
                fechaInicio, fechaInicio, fechaFin, fechaProrroga, isPenalizado);
                misProyectos.add(proyecto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Trabajador> loadTrabajadoresByProyectoId(int idProyecto) {
        ArrayList<Trabajador> trabajadores = new ArrayList<>();
        String sql = "SELECT t.* FROM Trabajador t "
                   + "JOIN Proyecto_Trabajador pt ON t.cedula = pt.cedula "
                   + "WHERE pt.id_proyecto = ?";
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
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

    private void loadTrabajadores() {
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM Trabajador");
             ResultSet rs = stmt.executeQuery()) {

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
    }

    private Cliente loadClienteById(int idCliente) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente WHERE id_cliente = ?";
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
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
        Contrato contrato = null;
        String sql = "SELECT * FROM Contrato WHERE id_proyecto = ?";
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idProyecto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("id");
                    String idCliente = rs.getString("id_cliente");
                    String nombre = rs.getString("nombre");
                    Date fechaEntrega = rs.getDate("fechaEntrega");
                    Date fechaInicio = rs.getDate("fechaInicio");
                    int id_proyecto = rs.getInt("id_proyecto");
                    contrato = new Contrato(id, idCliente, nombre, fechaEntrega, fechaInicio, id_proyecto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contrato;
    }

    private void saveCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (id_cliente, nombre, apellido, direccion, username) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getId());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellido());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void deleteCliente(String idCliente) {
        String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, idCliente);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveContrato(Contrato contrato) {
        String sql = "INSERT INTO Contrato (id, fechaIni, fechaFin, id_proyecto) VALUES (?, ?, ?, ?)";
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, contrato.getId());
            stmt.setDate(2, new java.sql.Date(contrato.getFechaInicio().getTime()));
            stmt.setDate(3, new java.sql.Date(contrato.getFechaEntrega().getTime()));
            stmt.setInt(4, contrato.getId_proyecto()); 
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void deleteContrato(String idContrato) {
        String sql = "DELETE FROM Contrato WHERE id = ?";
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, idContrato);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void saveProyecto(Proyecto proyecto) {
        String sql = "INSERT INTO Proyecto (id_proyecto, fechaInicio, fechaFin, fechaProrroga, isPenalizado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, proyecto.getId());
            stmt.setDate(2, new java.sql.Date(proyecto.getFechaInicio().getTime()));
            stmt.setDate(3, new java.sql.Date(proyecto.getFechaEntregaFinal().getTime()));
            stmt.setDate(4, proyecto.getFechaProrroga() != null ? new java.sql.Date(proyecto.getFechaProrroga().getTime()) : null);
            stmt.setString(5, proyecto.isPenalizado() ? "s" : "n");
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void deleteProyecto(String idProyecto) {
        String sql = "DELETE FROM Proyecto WHERE id_proyecto = ?";
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, idProyecto);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void saveTrabajador(Trabajador trabajador) {
        String sql = "INSERT INTO Trabajador (cedula, nombre, fechaNacimiento, sexo, direccion, username) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, trabajador.getCedula());
            stmt.setString(2, trabajador.getNombre());
            stmt.setDate(3, new java.sql.Date(trabajador.getFechaDeNacimiento().getTime()));
            stmt.setString(4, trabajador.getSexo());
            stmt.setString(5, trabajador.getDireccionParticular());
            stmt.setString(6, trabajador.getCedula());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void deleteTrabajador(String cedulaTrabajador) {
        String sql = "DELETE FROM Trabajador WHERE cedula = ?";
        try (Connection con = Conect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, cedulaTrabajador);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
