package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import logico.Cliente;
import logico.Empresa;

public class ListadoClientes extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtId;
    private JTextField nombre;
    private JTextField apellido;
    private DefaultTableModel model;
    private JTable table;
    private JSpinner spnCant;
    private Empresa empresa;

    public static void main(String[] args) {
        try {
            ListadoClientes dialog = new ListadoClientes();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListadoClientes() {
        setResizable(false);
        setBounds(100, 100, 642, 439);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(230, 230, 250));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);

        String[] header = {"ID", "Nombre", "Apellido"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(header);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.menu);
        panel_1.setBorder(new LineBorder(new Color(160, 82, 45), 2, true));
        panel_1.setBounds(20, 28, 592, 322);
        contentPanel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Buscar por:");
        lblNewLabel_1.setBounds(10, 11, 82, 14);
        panel_1.add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));

        JLabel lblNewLabel_2 = new JLabel("ID:");
        lblNewLabel_2.setBounds(10, 49, 30, 14);
        panel_1.add(lblNewLabel_2);
        lblNewLabel_2.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));

        JLabel lblNombre = new JLabel("Nombre(s):");
        lblNombre.setBounds(10, 74, 96, 14);
        panel_1.add(lblNombre);
        lblNombre.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));

        spnCant = new JSpinner();
        spnCant.setBounds(186, 122, 68, 20);
        panel_1.add(spnCant);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(436, 49, 89, 45);
        panel_1.add(btnBuscar);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String id = txtId.getText();
                String nombreCliente = nombre.getText();
                String apellidoCliente = apellido.getText();

                model.setRowCount(0);

                ArrayList<Cliente> clientes = Empresa.getInstance().getMisClientes();
                if (clientes != null && !clientes.isEmpty()) {
                    for (Cliente cliente : clientes) {
                        if ((id.isEmpty() || cliente.getId().equalsIgnoreCase(id))
                            && (nombreCliente.isEmpty() || cliente.getNombre().equalsIgnoreCase(nombreCliente))
                            && (apellidoCliente.isEmpty() || cliente.getApellido().equalsIgnoreCase(apellidoCliente))) {
                            Object[] row = new Object[3];
                            row[0] = cliente.getId();
                            row[1] = cliente.getNombre();
                            row[2] = cliente.getApellido();
                            model.addRow(row);
                        }
                    }
                }
            }
        });
        btnBuscar.setBackground(new Color(51, 204, 153));
        btnBuscar.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
        panel.setBounds(10, 167, 572, 144);
        panel_1.add(panel);
        panel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane);
        table = new JTable();
        table.setBackground(SystemColor.textHighlightText);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setColumnSelectionAllowed(true);
        table.setEnabled(false);
        table.setModel(model);
        scrollPane.setViewportView(table);

        txtId = new JTextField();
        txtId.setBounds(91, 47, 299, 20);
        panel_1.add(txtId);
        txtId.setColumns(10);

        nombre = new JTextField();
        nombre.setColumns(10);
        nombre.setBounds(91, 72, 299, 20);
        panel_1.add(nombre);

        JLabel label = new JLabel("Apellido(s):");
        label.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        label.setBounds(10, 99, 96, 14);
        panel_1.add(label);

        apellido = new JTextField();
        apellido.setColumns(10);
        apellido.setBounds(91, 97, 299, 20);
        panel_1.add(apellido);

        JLabel lblCants = new JLabel("Cantidad de proyectos:");
        lblCants.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblCants.setBounds(10, 124, 176, 14);
        panel_1.add(lblCants);

        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                nombre.setText("");
                apellido.setText("");
                spnCant.setValue(0);
                loadClientes();
            }
        });
        btnReiniciar.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
        btnReiniciar.setBackground(new Color(205, 92, 92));
        btnReiniciar.setBounds(436, 105, 89, 27);
        panel_1.add(btnReiniciar);

        JPanel buttonPane = new JPanel();
        buttonPane.setBounds(0, 361, 626, 38);
        contentPanel.add(buttonPane);
        buttonPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        buttonPane.setBackground(new Color(135, 206, 250));
        buttonPane.setLayout(null);

        JButton cancelButton = new JButton("Cerrar");
        cancelButton.setBounds(517, 11, 87, 23);
        cancelButton.setForeground(new Color(0, 0, 0));
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        cancelButton.setBackground(new Color(205, 92, 92));
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);

        JLabel lblNewLabel = new JLabel("Clientes");
        lblNewLabel.setBounds(20, 11, 91, 18);
        contentPanel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));

        loadClientes();
    }

    private void loadClientes() {
        model.setRowCount(0);
        if (Empresa.getInstance() != null) {
            ArrayList<Cliente> clientes = Empresa.getInstance().getMisClientes();
            if (clientes != null && !clientes.isEmpty()) {
                for (Cliente cliente : clientes) {
                    Object[] row = new Object[3];
                    row[0] = cliente.getId();
                    row[1] = cliente.getNombre();
                    row[2] = cliente.getApellido();
                    model.addRow(row);
                }
                table.setModel(model);
            }
        }
    }
}
