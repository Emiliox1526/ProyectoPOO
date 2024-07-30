package visual;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import logico.Conect;

public class ListadoTrabajador extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private DefaultTableModel model;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ListadoTrabajador dialog = new ListadoTrabajador();
            dialog.loadTrabajadores();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ListadoTrabajador() {
        setResizable(false);
        setBounds(100, 100, 675, 466);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(230, 230, 250));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);

        String[] header = {"Cédula", "Nombre", "Fecha de Nacimiento", "Sexo", "Dirección"};
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
        lblBuscar.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        panel_1.add(lblBuscar);

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(10, 36, 96, 14);
        lblCedula.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        panel_1.add(lblCedula);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 61, 137, 14);
        lblNombre.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        panel_1.add(lblNombre);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(10, 89, 137, 14);
        lblDireccion.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        panel_1.add(lblDireccion);

        txtCedula = new JTextField();
        txtCedula.setBounds(155, 34, 269, 20);
        panel_1.add(txtCedula);

        txtNombre = new JTextField();
        txtNombre.setBounds(155, 59, 269, 20);
        panel_1.add(txtNombre);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(155, 87, 269, 20);
        panel_1.add(txtDireccion);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(434, 33, 89, 42);
        btnBuscar.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
        btnBuscar.setBackground(new Color(51, 204, 153));
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                searchTrabajadores();
            }
        });
        panel_1.add(btnBuscar);

        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setBounds(434, 84, 89, 27);
        btnReiniciar.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
        btnReiniciar.setBackground(new Color(240, 128, 128));
        btnReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtCedula.setText("");
                txtNombre.setText("");
                txtDireccion.setText("");
                loadTrabajadores();
            }
        });
        panel_1.add(btnReiniciar);

        JPanel panel = new JPanel();
        panel.setBounds(10, 145, 616, 188);
        panel.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
        panel.setLayout(new BorderLayout(0, 0));
        panel_1.add(panel);

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);
        table = new JTable(model);
        table.setBackground(SystemColor.textHighlightText);
        table.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        table.setEnabled(false);
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("Trabajadores");
        lblNewLabel.setBounds(10, 11, 91, 25);
        lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
        contentPanel.add(lblNewLabel);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
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

    private void searchTrabajadores() {
        model.setRowCount(0);
        String cedula = txtCedula.getText();
        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();

        String sql = "SELECT t.cedula, t.nombre, t.fechaNacimiento, t.sexo, t.direccion " +
                     "FROM Trabajador t " +
                     "WHERE (t.cedula = ? OR ? = '') " +
                     "AND (t.nombre LIKE ? OR ? = '') " +
                     "AND (t.direccion LIKE ? OR ? = '')";

        try (Connection con = Conect.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, cedula);
            pst.setString(2, cedula);
            pst.setString(3, "%" + nombre + "%");
            pst.setString(4, nombre);
            pst.setString(5, "%" + direccion + "%");
            pst.setString(6, direccion);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[model.getColumnCount()];
                    row[0] = rs.getString("cedula");
                    row[1] = rs.getString("nombre");
                    row[2] = rs.getDate("fechaNacimiento").toString();
                    row[3] = rs.getString("sexo");
                    row[4] = rs.getString("direccion");
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadTrabajadores() {
        model.setRowCount(0);
        String sql = "SELECT t.cedula, t.nombre, t.fechaNacimiento, t.sexo, t.direccion FROM Trabajador t";

        try (Connection con = Conect.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Object[] row = new Object[model.getColumnCount()];
                row[0] = rs.getString("cedula");
                row[1] = rs.getString("nombre");
                row[2] = rs.getDate("fechaNacimiento").toString();
                row[3] = rs.getString("sexo");
                row[4] = rs.getString("direccion");
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
