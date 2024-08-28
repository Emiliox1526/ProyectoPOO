package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logico.Conect;
import logico.Cliente;

public class RegistroCliente extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtDir;
    private JTextField txtApellido;
    private static String default_password = "12345";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            RegistroCliente dialog = new RegistroCliente();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public RegistroCliente() {
        setResizable(false);
        setBounds(100, 100, 605, 314);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(230, 230, 250));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblNewLabel = new JLabel("Registro cliente");
        lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 21, 137, 14);
        contentPanel.add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(205, 133, 63), 2, true));
        panel.setBounds(10, 35, 564, 182);
        contentPanel.add(panel);
        panel.setLayout(null);

        JLabel lblApellido = new JLabel("Apellido(s):");
        lblApellido.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblApellido.setBounds(270, 80, 84, 14);
        panel.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setFont(new Font("Dialog", Font.PLAIN, 10));
        txtApellido.setColumns(10);
        txtApellido.setBounds(349, 80, 176, 20);
        panel.add(txtApellido);

        txtDir = new JTextField();
        txtDir.setBounds(86, 123, 439, 20);
        panel.add(txtDir);
        txtDir.setFont(new Font("Dialog", Font.PLAIN, 10));
        txtDir.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Direccion:");
        lblNewLabel_3.setBounds(10, 125, 139, 14);
        panel.add(lblNewLabel_3);
        lblNewLabel_3.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));

        JLabel lblNewLabel_1 = new JLabel("Nombre(s):");
        lblNewLabel_1.setBounds(10, 81, 84, 14);
        panel.add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));

        txtNombre = new JTextField();
        txtNombre.setBounds(84, 79, 166, 20);
        panel.add(txtNombre);
        txtNombre.setFont(new Font("Dialog", Font.PLAIN, 10));
        txtNombre.setColumns(10);

        txtCedula = new JTextField();
        txtCedula.setBounds(84, 29, 166, 20);
        panel.add(txtCedula);
        txtCedula.setFont(new Font("Dialog", Font.PLAIN, 10));
        txtCedula.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Cedula");
        lblNewLabel_2.setBounds(10, 31, 46, 14);
        panel.add(lblNewLabel_2);
        lblNewLabel_2.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new LineBorder(new Color(184, 134, 11), 2, true));
        buttonPane.setBackground(new Color(100, 149, 237));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Registrar\r\n");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!txtCedula.getText().isEmpty() && !txtNombre.getText().isEmpty() && !txtDir.getText().isEmpty()) {
                    Cliente cliente = new Cliente(txtCedula.getText(), txtNombre.getText(), txtApellido.getText(), txtDir.getText());
                    try {
                        registrarClienteEnBaseDeDatos(cliente);
                        JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                        clean();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al registrar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        okButton.setBackground(new Color(0, 255, 0));
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cerrar");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        cancelButton.setBackground(new Color(255, 99, 71));
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }

    void registrarClienteEnBaseDeDatos(Cliente cliente) throws SQLException {

        String sqlCliente = "INSERT INTO Cliente (nombre, apellido, direccion, username) VALUES (?, ?, ?, ?)";

        try (Connection con = Conect.getConnection()) {
            con.setAutoCommit(false); 

            try (PreparedStatement pstCliente = con.prepareStatement(sqlCliente)) {
                
                pstCliente.setString(1, cliente.getNombre());
                pstCliente.setString(2, cliente.getApellido());
                pstCliente.setString(3, cliente.getDireccion());
                pstCliente.setString(4, cliente.getId());
                pstCliente.executeUpdate();

                con.commit(); 

            } catch (SQLException e) {
                con.rollback(); 
                throw e;
            }
        }
    }


    void clean() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtDir.setText("");
    }
}
