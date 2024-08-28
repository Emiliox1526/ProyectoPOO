package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import logico.Cliente;
import logico.Conect;
import logico.Contrato;
import logico.Diseñador;
import logico.Empresa;
import logico.Jefe;
import logico.Planificador;
import logico.Programador;
import logico.Proyecto;
import logico.Trabajador;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class RegistroProyecto extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtidProyecto;
    private JTextField txtidContrato;
    private JTable tableTrabajadores;
    private JTable tableTrabajadoresAgregados;
    private Empresa empresa = Empresa.getInstance();
    private JDateChooser dChooserInicio;
    private JDateChooser dChooserFinal;
    private static DefaultTableModel model;
    private static DefaultTableModel modelAgregado;
    private JTextField txtIdCliente;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private Cliente cliente = null;
    private int indexSeleccionadoTrabajadores = -1;
    private int indexSeleccionadoAgregados = -1;
    private Object[] rowTrabajadores;
    private Object[] rowAgregado;
    private ArrayList<Trabajador> listaTrabajadores = Empresa.getInstance().getMisTrabajadores();
    private ArrayList<Trabajador> listaAgregados = new ArrayList<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            RegistroProyecto dialog = new RegistroProyecto();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public RegistroProyecto() {
        setResizable(false);
        setBounds(100, 100, 754, 563);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(230, 230, 250));


        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnReg = new JButton("Registrar");
        btnReg.setBackground(Color.GREEN);
        btnReg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarProyecto();
            }
        });
        buttonPane.add(btnReg);

        JButton cancelButton = new JButton("Cerrar");
        cancelButton.setBackground(new Color(255, 99, 71));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);

        JPanel panelDatos = new JPanel();
        panelDatos.setBounds(12, 44, 702, 437);
        contentPanel.add(panelDatos);
        panelDatos.setLayout(null);

        JLabel lblidProyecto = new JLabel("Proyecto:");
        lblidProyecto.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblidProyecto.setBounds(12, 13, 75, 14);
        panelDatos.add(lblidProyecto);

        txtidProyecto = new JTextField();
        txtidProyecto.setEditable(false);
        txtidProyecto.setBounds(150, 10, 205, 20);
        panelDatos.add(txtidProyecto);
        txtidProyecto.setColumns(10);

        JLabel lblcliente = new JLabel("Cedula Cliente:");
        lblcliente.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblcliente.setBounds(12, 45, 120, 14);
        panelDatos.add(lblcliente);

        txtIdCliente = new JTextField();
        txtIdCliente.setBounds(150, 42, 205, 20);
        panelDatos.add(txtIdCliente);
        txtIdCliente.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblNombre.setBounds(389, 45, 120, 14);
        panelDatos.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setEditable(false);
        txtNombre.setBounds(501, 42, 189, 20);
        panelDatos.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblApellido.setBounds(389, 78, 120, 14);
        panelDatos.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setEditable(false);
        txtApellido.setBounds(501, 75, 189, 20);
        panelDatos.add(txtApellido);
        txtApellido.setColumns(10);

        JLabel lblcontrato = new JLabel("Contrato:");
        lblcontrato.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblcontrato.setBounds(12, 111, 75, 14);
        panelDatos.add(lblcontrato);

        txtidContrato = new JTextField();
        txtidContrato.setEditable(false);
        txtidContrato.setColumns(10);
        txtidContrato.setBounds(150, 108, 205, 20);
        panelDatos.add(txtidContrato);

        JLabel lblfechaInicio = new JLabel("Fecha de inicio:");
        lblfechaInicio.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblfechaInicio.setBounds(12, 144, 110, 14);
        panelDatos.add(lblfechaInicio);

        dChooserInicio = new JDateChooser();
        dChooserInicio.setBounds(150, 141, 205, 20);
        panelDatos.add(dChooserInicio);

        JLabel lblfechaEntrega = new JLabel("Fecha de entrega:");
        lblfechaEntrega.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblfechaEntrega.setBounds(12, 177, 120, 14);
        panelDatos.add(lblfechaEntrega);

        dChooserFinal = new JDateChooser();
        dChooserFinal.setBounds(150, 174, 205, 20);
        panelDatos.add(dChooserFinal);


        JPanel panelTrabajadores = new JPanel();
        panelTrabajadores.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
        panelTrabajadores.setBackground(new Color(175, 238, 238));
        panelTrabajadores.setBounds(12, 224, 680, 202);
        panelDatos.add(panelTrabajadores);
        panelTrabajadores.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 277, 181);
        panelTrabajadores.add(scrollPane);

        tableTrabajadores = new JTable();
        tableTrabajadores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                indexSeleccionadoTrabajadores = tableTrabajadores.getSelectedRow();
            }
        });
        String[] headers = {"Cedula", "Nombre", "Apellido", "Ocupacion"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(headers);
        tableTrabajadores.setModel(model);
        scrollPane.setViewportView(tableTrabajadores);

        JScrollPane scrollPaneAgregado = new JScrollPane();
        scrollPaneAgregado.setBounds(393, 10, 277, 181);
        panelTrabajadores.add(scrollPaneAgregado);

        tableTrabajadoresAgregados = new JTable();
        tableTrabajadoresAgregados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                indexSeleccionadoAgregados = tableTrabajadoresAgregados.getSelectedRow();
            }
        });
        modelAgregado = new DefaultTableModel();
        modelAgregado.setColumnIdentifiers(headers);
        tableTrabajadoresAgregados.setModel(modelAgregado);
        scrollPaneAgregado.setViewportView(tableTrabajadoresAgregados);

        JButton btnPasar = new JButton("Agregar");
        btnPasar.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
        btnPasar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	moverTrabajadorEntreListas(true);
            }
        });
        btnPasar.setBounds(297, 69, 89, 23);
        panelTrabajadores.add(btnPasar);

        JButton btnRemover = new JButton("Remover");
        btnRemover.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	moverTrabajadorEntreListas(false);
            }
        });
        btnRemover.setBounds(297, 115, 89, 23);
        panelTrabajadores.add(btnRemover);
        
        JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtIdCliente.getText();
				cliente = empresa.buscarClientePorId(id);
				if (cliente != null) {
				    txtNombre.setText(cliente.getNombre());
				    txtApellido.setText(cliente.getApellido());
				} else {
				    JOptionPane.showMessageDialog(null,"Cliente no encontrado para la cedula: " + id+ ". Puede crear el cliente en el botón de agregar nuevo" ,"Error", JOptionPane.ERROR_MESSAGE);
				}

			}

		});
		btnBuscar.setBounds(235, 73, 120, 25);
		panelDatos.add(btnBuscar);
        actualizarTablas();
    }
    
    private void moverTrabajadorEntreListas(boolean agregar) {
        if (agregar) {
            if (indexSeleccionadoTrabajadores != -1) {
                Trabajador trabajadorSeleccionado = listaTrabajadores.get(indexSeleccionadoTrabajadores);
                listaAgregados.add(trabajadorSeleccionado);
                listaTrabajadores.remove(indexSeleccionadoTrabajadores);
            }
        } else {
            if (indexSeleccionadoAgregados != -1) {
                Trabajador trabajadorSeleccionado = listaAgregados.get(indexSeleccionadoAgregados);
                listaTrabajadores.add(trabajadorSeleccionado);
                listaAgregados.remove(indexSeleccionadoAgregados); 
            }
        }
        actualizarTablas();
    }

    private void actualizarTablas() {
        model.setRowCount(0);
        modelAgregado.setRowCount(0); 

        ArrayList<Trabajador> listaTrabajadoresCopia = new ArrayList<>(listaTrabajadores);
        ArrayList<Trabajador> listaAgregadosCopia = new ArrayList<>(listaAgregados);
        
        
        rowTrabajadores = new Object[4];
        for (Trabajador trabajador : listaTrabajadoresCopia) {
            rowTrabajadores[0] = trabajador.getCedula();
            rowTrabajadores[1] = trabajador.getNombre();
            rowTrabajadores[2] = trabajador.getApellidos();
            rowTrabajadores[3] = tipoTrabajador(trabajador.getCedula());
            model.addRow(rowTrabajadores.clone());
        }

        rowAgregado = new Object[4];
        for (Trabajador trabajador : listaAgregadosCopia) {
            rowAgregado[0] = trabajador.getCedula();
            rowAgregado[1] = trabajador.getNombre();
            rowAgregado[2] = trabajador.getApellidos();
            rowAgregado[3] = tipoTrabajador(trabajador.getCedula());
            modelAgregado.addRow(rowAgregado.clone());
        }
       
    }

    private String tipoTrabajador(String id) {
    	String tipo = null;
    	ArrayList<Trabajador> trab = listaTrabajadores;
    	Trabajador t = null;
    	for (Trabajador trabajador : trab) {
			if(trabajador.getCedula().equalsIgnoreCase(id)) {
				t = trabajador;
			}
		}
    	
    	if(t instanceof Jefe) {
    		tipo = "JEFE";
    	}if(t instanceof Planificador) {
    		tipo = "PLANIFICADOR";
    	}if(t instanceof Programador){
    		tipo = "PROGRAMADOR";
    	}if(t instanceof Diseñador) {
    		tipo = "DISEÑADOR";
    	}
    	
		return tipo;
    	
    }

    private void registrarProyecto() {
        try (Connection con = Conect.getConnection()) {
            con.setAutoCommit(false);

            
            String insertProyectoSQL = "INSERT INTO Proyecto (fechaInicio, fechaFin, isPenalizado, id_cliente) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmtProyecto = con.prepareStatement(insertProyectoSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmtProyecto.setDate(1, new java.sql.Date(dChooserInicio.getDate().getTime()));
                stmtProyecto.setDate(2, new java.sql.Date(dChooserFinal.getDate().getTime()));
                stmtProyecto.setString(3, "N");
                stmtProyecto.setString(4, txtIdCliente.getText());

                int affectedRows = stmtProyecto.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Error al crear el proyecto, no se ha insertado ninguna fila.");
                }

                try (ResultSet generatedKeys = stmtProyecto.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idProyecto = generatedKeys.getInt(1);
                        txtidProyecto.setText("P"+String.valueOf(idProyecto));
                        txtidContrato.setText("C"+String.valueOf(idProyecto));

                       
                        String insertProyectoClienteSQL = "INSERT INTO Proyecto_cliente (id_proyecto, id_cliente) VALUES (?, ?)";
                        try (PreparedStatement stmtProyectoCliente = con.prepareStatement(insertProyectoClienteSQL)) {
                            String dniCliente = txtIdCliente.getText(); 
                            stmtProyectoCliente.setInt(1, idProyecto);
                            stmtProyectoCliente.setString(2, dniCliente);
                            stmtProyectoCliente.executeUpdate();
                        }

                        
                        String insertContratoSQL = "INSERT INTO Contrato (fechaIni, fechaFin, id_proyecto) VALUES (?, ?, ?)";
                        try (PreparedStatement stmtContrato = con.prepareStatement(insertContratoSQL)) {
                            stmtContrato.setDate(1, new java.sql.Date(dChooserInicio.getDate().getTime()));
                            stmtContrato.setDate(2, new java.sql.Date(dChooserFinal.getDate().getTime()));
                            stmtContrato.setInt(3, idProyecto);
                            stmtContrato.executeUpdate();
                        }

                        
                        String insertProyectoTrabajadorSQL = "INSERT INTO Proyecto_Trabajador (id_proyecto, cedula) VALUES (?, ?)";
                        try (PreparedStatement stmtProyectoTrabajador = con.prepareStatement(insertProyectoTrabajadorSQL)) {
                            for (Trabajador trabajador : listaAgregados) {
                                stmtProyectoTrabajador.setInt(1, idProyecto);
                                stmtProyectoTrabajador.setString(2, trabajador.getCedula());
                                stmtProyectoTrabajador.addBatch();
                            }
                            stmtProyectoTrabajador.executeBatch();  
                        }

                        con.commit();
                        JOptionPane.showMessageDialog(this, "Proyecto y contrato registrados exitosamente.");
                    } else {
                        throw new SQLException("No se ha generado el ID del proyecto.");
                    }
                }
            } catch (SQLException ex) {
                con.rollback();
                JOptionPane.showMessageDialog(this, "Error al registrar el proyecto y contrato: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error en la conexión a la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }


}