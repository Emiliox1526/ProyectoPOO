package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import logico.Conect;

public class ListadoProyecto extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtId;
    private JTextField txtNombreCliente;
    private JTextField txtApellidoCliente;
    private DefaultTableModel model;
    private Object[] row;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ListadoProyecto dialog = new ListadoProyecto();
            dialog.loadProyectos();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ListadoProyecto() {
        setResizable(false);
        setBounds(100, 100, 675, 466);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(230, 230, 250));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);
        String[] header = {"ID Proyecto", "Cliente", "Fecha de inicio", "Fecha de entrega", "Prorrogado", "Penalizado"};

        model = new DefaultTableModel();
        model.setColumnIdentifiers(header);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.menu);
        panel_1.setBorder(new LineBorder(new Color(160, 82, 45), 2, true));
        panel_1.setBounds(10, 35, 636, 344);
        contentPanel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblBuscar = new JLabel("Buscar por:");
        lblBuscar.setBounds(10, 11, 82, 14);
        panel_1.add(lblBuscar);
        lblBuscar.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        
        JLabel lblId = new JLabel("ID Proyecto:");
        lblId.setBounds(10, 36, 96, 14);
        panel_1.add(lblId);
        lblId.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        
        JLabel lblNombreCliente = new JLabel("Nombre del cliente:");
        lblNombreCliente.setBounds(10, 61, 137, 14);
        panel_1.add(lblNombreCliente);
        lblNombreCliente.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        
        JLabel lblApellidoCliente = new JLabel("Apellido del cliente:");
        lblApellidoCliente.setBounds(10, 89, 137, 14);
        panel_1.add(lblApellidoCliente);
        lblApellidoCliente.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        
        txtId = new JTextField();
        txtId.setBounds(155, 34, 269, 20);
        panel_1.add(txtId);
        txtId.setColumns(10);

        txtNombreCliente = new JTextField();
        txtNombreCliente.setBounds(155, 59, 269, 20);
        panel_1.add(txtNombreCliente);
        txtNombreCliente.setColumns(10);

        txtApellidoCliente = new JTextField();
        txtApellidoCliente.setBounds(155, 87, 269, 20);
        panel_1.add(txtApellidoCliente);
        txtApellidoCliente.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(434, 33, 89, 42);
        panel_1.add(btnBuscar);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                model.setRowCount(0);

                String id = txtId.getText();
                String nombreCliente = txtNombreCliente.getText();
                String apellidoCliente = txtApellidoCliente.getText();

                try {
                    Connection con = Conect.getConnection();
                    String sql = "SELECT p.id_proyecto, c.nombre, c.apellido, p.fechaInicio, p.fechaFin, p.fechaProrroga, p.isPenalizado " +
                            "FROM Proyecto p " +
                            "INNER JOIN Proyecto_Cliente pc ON p.id_proyecto = pc.id_proyecto " +
                            "INNER JOIN Cliente c ON pc.id_cliente = c.id_cliente " +
                            "WHERE (p.id_proyecto = ? OR ? = '') " +
                            "AND (c.nombre = ? OR ? = '') " +
                            "AND (c.apellido = ? OR ? = '')";

                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, id);
                    pst.setString(3, nombreCliente);
                    pst.setString(4, nombreCliente);
                    pst.setString(5, apellidoCliente);
                    pst.setString(6, apellidoCliente);

                    ResultSet rs = pst.executeQuery();

                    while (rs.next()) {
                        row = new Object[model.getColumnCount()];
                        row[0] = rs.getString("id_proyecto");
                        row[1] = rs.getString("nombre") + " " + rs.getString("apellido");
                        row[2] = rs.getDate("fechaInicio").toString();
                        row[3] = rs.getDate("fechaFin").toString();
                        row[4] = rs.getDate("fechaProrroga") == null ? "No" : rs.getDate("fechaProrroga").toString();
                        row[5] = rs.getBoolean("isPenalizado") ? "Si" : "No";
                        model.addRow(row);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        btnBuscar.setBackground(new Color(51, 204, 153));
        btnBuscar.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));

        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                txtNombreCliente.setText("");
                txtApellidoCliente.setText("");
                loadProyectos();
            }
        });
        btnReiniciar.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
        btnReiniciar.setBackground(new Color(240, 128, 128));
        btnReiniciar.setBounds(434, 84, 89, 27);
        panel_1.add(btnReiniciar);

        JPanel panel = new JPanel();
        panel.setBounds(10, 145, 616, 188);
        panel_1.add(panel);
        panel.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
        panel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);
        table = new JTable();
        table.setBackground(SystemColor.textHighlightText);
        table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        table.setColumnSelectionAllowed(true);
        table.setEnabled(false);
        scrollPane.setViewportView(table);
        table.setModel(model);

        JButton btnProrroga = new JButton("Prorrogar");
        btnProrroga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaProrroga p = new VentanaProrroga();
                dispose();
                p.setModal(true);
                p.setVisible(true);
            }
        });
        btnProrroga.setBackground(new Color(50, 205, 50));
        btnProrroga.setBounds(533, 36, 93, 75);
        panel_1.add(btnProrroga);

        JLabel lblNewLabel = new JLabel("Proyectos");
        lblNewLabel.setBounds(10, 11, 91, 25);
        contentPanel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        buttonPane.setBackground(new Color(135, 206, 250));
        buttonPane.setLayout(new BorderLayout(0, 0));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton cancelButton = new JButton("Cerrar");
        cancelButton.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
        cancelButton.setBackground(new Color(255, 99, 71));
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(cancelButton, BorderLayout.EAST);
    }

    public void loadProyectos() {
        model.setRowCount(0);
        try {
            Connection con = Conect.getConnection();
            String sql = "SELECT p.id_proyecto, c.nombre, c.apellido, p.fechaInicio, p.fechaFin, p.fechaProrroga, p.isPenalizado " +
                         "FROM Proyecto p " +
                         "INNER JOIN Proyecto_Cliente pc ON p.id_proyecto = pc.id_proyecto " +
                         "INNER JOIN Cliente c ON pc.id_cliente = c.id_cliente";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                row = new Object[model.getColumnCount()];
                row[0] = rs.getString("id_proyecto");
                row[1] = rs.getString("nombre") + " " + rs.getString("apellido");
                row[2] = rs.getDate("fechaInicio").toString();
                row[3] = rs.getDate("fechaFin").toString();
                row[4] = rs.getDate("fechaProrroga") == null ? "No" : rs.getDate("fechaProrroga").toString();
                row[5] = rs.getBoolean("isPenalizado") ? "Si" : "No";
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
