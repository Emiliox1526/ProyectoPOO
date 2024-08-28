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
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import logico.Empresa;
import logico.Log;
import javax.swing.JTextField;

public class LogDialog extends JDialog { 

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            LogDialog dialog = new LogDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public LogDialog() {
        setResizable(false);
        setBounds(100, 100, 642, 439);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(230, 230, 250));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);

        String[] header = {"Nombre de Usuario", "Fecha"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(header);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.menu);
        panel_1.setBorder(new LineBorder(new Color(160, 82, 45), 2, true));
        panel_1.setBounds(20, 113, 592, 237);
        contentPanel.add(panel_1);
        panel_1.setLayout(new BorderLayout());

        table = new JTable();
        table.setBackground(SystemColor.textHighlightText);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setEnabled(false); 
        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        panel_1.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPane = new JPanel();
        buttonPane.setBounds(0, 361, 626, 38);
        contentPanel.add(buttonPane);
        buttonPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        buttonPane.setBackground(new Color(135, 206, 250));
        buttonPane.setLayout(null);

        JButton cancelButton = new JButton("Cerrar");
        cancelButton.setBounds(511, 11, 87, 23);
        cancelButton.setForeground(new Color(0, 0, 0));
        cancelButton.setBackground(new Color(205, 92, 92));
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        buttonPane.add(cancelButton);

        JLabel lblNewLabel = new JLabel("Log");
        lblNewLabel.setBounds(20, 11, 91, 18);
        contentPanel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));

        JLabel lblNewLabel_1 = new JLabel("Username:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(20, 64, 76, 14);
        contentPanel.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(106, 63, 165, 20);
        contentPanel.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Buscar");
        btnNewButton.setBackground(new Color(144, 238, 144));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                String username = textField.getText(); 

                if (Empresa.getInstance() != null) {
                    ArrayList<Log> logs = Empresa.getInstance().getMisLogs();
                    if (logs != null && !logs.isEmpty()) {
                        model.setRowCount(0); 
                        for (Log log : logs) {
                            if (username.isEmpty() || log.getUsername().equalsIgnoreCase(username)) {
                                Object[] row = new Object[2];
                                row[0] = log.getUsername();
                                row[1] = log.getFyH();
                                model.addRow(row);
                            }
                        }
                    }
                }
            }
        });
        btnNewButton.setBounds(410, 40, 89, 45);
        contentPanel.add(btnNewButton);
        
        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		loadLog();
        	}
        });
        btnReiniciar.setBackground(new Color(255, 255, 255));
        btnReiniciar.setBounds(523, 38, 89, 45);
        contentPanel.add(btnReiniciar);

        loadLog();
    }

    private void loadLog() {
        model.setRowCount(0);
        if (Empresa.getInstance() != null) {
            ArrayList<Log> logs = Empresa.getInstance().getMisLogs();
            if (logs != null && !logs.isEmpty()) {
                for (Log l : logs) {
                    Object[] row = new Object[2];
                    row[0] = l.getUsername();
                    row[1] = l.getFyH();
                    model.addRow(row);
                }
            }
        }
    }
    
    
}
