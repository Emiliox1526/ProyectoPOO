package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;
import logico.Empresa;
import logico.Proyecto;
import logico.Cliente;

public class VentanaProrroga extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtIdProyecto;
    private JTextField txtIdCliente;
    private JTextField txtNombreCliente;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFinal;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private Empresa empresa = Empresa.getInstance();
    
    public static void main(String[] args) {
        try {
            VentanaProrroga dialog = new VentanaProrroga();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public VentanaProrroga() {
        setResizable(false);
        setBounds(100, 100, 450, 320);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(230, 230, 250));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(160, 82, 45), 2, true));
        panel.setBounds(12, 33, 408, 192);
        contentPanel.add(panel);
        panel.setLayout(null);

        JSpinner spnDias = new JSpinner();
        spnDias.setModel(new SpinnerNumberModel(1, 1, null, 1));
        spnDias.setBounds(186, 145, 97, 22);
        panel.add(spnDias);

        JLabel lblProyecto = new JLabel("Id Proyecto:");
        lblProyecto.setBounds(12, 13, 75, 16);
        panel.add(lblProyecto);

        txtIdProyecto = new JTextField();
        txtIdProyecto.setBounds(99, 10, 75, 22);
        panel.add(txtIdProyecto);
        txtIdProyecto.setColumns(10);

        JLabel lblIdCliente = new JLabel("Id Cliente:");
        lblIdCliente.setBounds(12, 45, 75, 16);
        panel.add(lblIdCliente);

        txtIdCliente = new JTextField();
        txtIdCliente.setEditable(false);
        txtIdCliente.setColumns(10);
        txtIdCliente.setBounds(99, 42, 75, 22);
        panel.add(txtIdCliente);

        JLabel lblNombreCliente = new JLabel("Nombre Cliente:");
        lblNombreCliente.setBounds(186, 45, 97, 16);
        panel.add(lblNombreCliente);

        txtNombreCliente = new JTextField();
        txtNombreCliente.setEditable(false);
        txtNombreCliente.setColumns(10);
        txtNombreCliente.setBounds(295, 42, 88, 22);
        panel.add(txtNombreCliente);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idProyecto = txtIdProyecto.getText();
                try {
                    Proyecto proyecto = buscarProyectoPorId(idProyecto);
                    if (proyecto != null) {
                        Cliente cliente = proyecto.getCliente();
                        if (cliente != null) {
                            txtIdCliente.setText(cliente.getId());
                            txtNombreCliente.setText(cliente.getNombre());
                            txtFechaInicio.setText(proyecto.getFechaInicio().toString());
                            txtFechaFinal.setText(proyecto.getFechaEntregaFinal().toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "El proyecto no tiene un cliente asociado.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Proyecto no encontrado para el ID: " + idProyecto, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al buscar el proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnBuscar.setBounds(186, 9, 97, 25);
        panel.add(btnBuscar);

        JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
        lblFechaInicio.setBounds(12, 77, 75, 16);
        panel.add(lblFechaInicio);

        txtFechaInicio = new JTextField();
        txtFechaInicio.setEditable(false);
        txtFechaInicio.setColumns(10);
        txtFechaInicio.setBounds(99, 74, 75, 22);
        panel.add(txtFechaInicio);

        JLabel lblFechaFinal = new JLabel("Fecha Final:");
        lblFechaFinal.setBounds(186, 77, 97, 16);
        panel.add(lblFechaFinal);

        txtFechaFinal = new JTextField();
        txtFechaFinal.setEditable(false);
        txtFechaFinal.setColumns(10);
        txtFechaFinal.setBounds(295, 74, 88, 22);
        panel.add(txtFechaFinal);

        JLabel lblProrrogarPor = new JLabel("Prorrogar por:");
        lblProrrogarPor.setBounds(12, 106, 90, 16);
        panel.add(lblProrrogarPor);

        JRadioButton rdbtnDia = new JRadioButton("Cantidad de d�as");
        rdbtnDia.setSelected(true);
        buttonGroup.add(rdbtnDia);
        rdbtnDia.setBounds(12, 131, 127, 25);
        panel.add(rdbtnDia);

        JRadioButton rdbtnFecha = new JRadioButton("Fecha");
        buttonGroup.add(rdbtnFecha);
        rdbtnFecha.setBounds(12, 161, 127, 25);
        panel.add(rdbtnFecha);

        JDateChooser dateFecha = new JDateChooser();
        dateFecha.setBounds(186, 145, 97, 22);
        panel.add(dateFecha);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPane.setBackground(new Color(135, 206, 250));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnProrrogar = new JButton("Prorrogar");


btnProrrogar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String idProyecto = txtIdProyecto.getText();
        System.out.println("ID Proyecto: " + idProyecto);
        System.out.println("Empresa instancia: " + empresa);
        try {
            Proyecto proyecto = buscarProyectoPorId(idProyecto);
            System.out.println("Proyecto: " + proyecto);
                    if (proyecto != null) {
                        Date fechaActual = new Date(System.currentTimeMillis());
                        if (!proyecto.isPenalizado() && proyecto.getFechaProrroga() == null) {
                            if (proyecto.getFechaInicio().after(fechaActual)) {
                                if (rdbtnDia.isSelected()) {
                                    int diasProrroga = (int) spnDias.getValue();
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(proyecto.getFechaInicio());
                                    calendar.add(Calendar.DAY_OF_MONTH, diasProrroga);
                                    Date nuevaFechaFin = new Date(calendar.getTimeInMillis());
                                    proyecto.setFechaProrroga(nuevaFechaFin);
                                    actualizarProyecto(proyecto, Integer.parseInt(idProyecto));
                                    JOptionPane.showMessageDialog(null, "Pr�rroga realizada correctamente.", "�xito", JOptionPane.INFORMATION_MESSAGE);
                                } else if (rdbtnFecha.isSelected()) {
                                    Date nuevaFechaFin = new Date(dateFecha.getDate().getTime());
                                    proyecto.setFechaProrroga(nuevaFechaFin);
                                    actualizarProyecto(proyecto, Integer.parseInt(idProyecto));
                                    JOptionPane.showMessageDialog(null, "Pr�rroga realizada correctamente.", "�xito", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una opci�n de pr�rroga.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "No se puede realizar la pr�rroga porque la fecha de entrega ya ha pasado.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No se puede realizar la pr�rroga porque el proyecto ya est� penalizado o prorrogado.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Proyecto no encontrado para el ID: " + idProyecto, "Error", JOptionPane.ERROR_MESSAGE);
                    }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al buscar el proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});

        buttonPane.add(btnProrrogar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(btnCancelar);
    }

    private Proyecto buscarProyectoPorId(String idProyecto) throws SQLException {
        return Empresa.getInstance().BuscarProyectoById(idProyecto);
    }

    private void actualizarProyecto(Proyecto proyecto, int i) throws SQLException {
        Empresa.getInstance().actualizarProyecto(proyecto, i);
    }
}
