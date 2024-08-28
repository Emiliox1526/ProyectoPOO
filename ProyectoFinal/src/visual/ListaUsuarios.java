package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import logico.User;



public class ListaUsuarios extends JDialog {
	
    private DefaultTableModel model;

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaUsuarios dialog = new ListaUsuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaUsuarios() {
        setResizable(false);
        setBounds(100, 100, 642, 439);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(230, 230, 250));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);

        String[] header = {"Nombre de Usuario", "Tipo"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(header);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.menu);
        panel_1.setBorder(new LineBorder(new Color(160, 82, 45), 2, true));
        panel_1.setBounds(20, 28, 592, 322);
        contentPanel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Lista usuarios");
        lblNewLabel_1.setBounds(10, 11, 109, 14);
        panel_1.add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));

        table = new JTable();
        table.setBackground(SystemColor.textHighlightText);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setColumnSelectionAllowed(true);
        table.setEnabled(false);
        table.setModel(model);
                        
                                JPanel panel = new JPanel();
                                panel.setBounds(10, 36, 566, 275);
                                panel_1.add(panel);
                                panel.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
                                panel.setLayout(new BorderLayout(0, 0));
                                
                                        JScrollPane scrollPane = new JScrollPane();
                                        panel.add(scrollPane, BorderLayout.CENTER);
                                        scrollPane.setViewportView(table);
                                        
                                        table = new JTable();
                                        scrollPane.setViewportView(table);

        JPanel buttonPane = new JPanel();
        buttonPane.setBounds(0, 361, 626, 38);
        contentPanel.add(buttonPane);
        buttonPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        buttonPane.setBackground(new Color(135, 206, 250));
        buttonPane.setLayout(null);

        JButton cancelButton = new JButton("Cerrar");
        cancelButton.setBounds(511, 11, 87, 23);
        cancelButton.setForeground(new Color(0, 0, 0));
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        cancelButton.setBackground(new Color(205, 92, 92));
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);

        JLabel lblNewLabel = new JLabel("Usuarios");
        lblNewLabel.setBounds(20, 11, 91, 18);
        contentPanel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));

        loadClientes();
    }

    private void loadClientes() {
        model.setRowCount(0);
        if (Empresa.getInstance() != null) {
            ArrayList<User> user = Empresa.getInstance().getMisUsuarios();
            if (user != null && !user.isEmpty()) {
                for (User u : user) {
                    Object[] row = new Object[3];
                    row[0] = u.getTipo();
                    row[1] = u.getPass();
                    model.addRow(row);
                }
                table.setModel(model);
            }
        }
    }
}
