package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logico.Control;
import logico.User;

public class VentanaLogin extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtUsuario;
    private JPasswordField txtContrase�a;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Control control = Control.getInstance(); // Aseg�rate de inicializar el control
                try {
                    VentanaLogin frame = new VentanaLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaLogin() {
        setResizable(false);
        setBounds(100, 100, 535, 327);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(112, 128, 144));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 245, 238));
        panel.setBorder(new LineBorder(new Color(205, 133, 63), 2, true));
        panel.setBounds(10, 46, 499, 217);
        contentPanel.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 250, 240));
        panel_1.setBorder(new LineBorder(new Color(205, 133, 63), 2, true));
        panel_1.setBounds(0, 174, 499, 42);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JButton btnNewButton = new JButton("Login");
        btnNewButton.setActionCommand("OK");
        getRootPane().setDefaultButton(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Control.getInstance().confirmLogin(txtUsuario.getText(), txtContrase�a.getText())) {
                    Principal frame = new Principal(Control.getInstance());
                    dispose();
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(VentanaLogin.this, "No se encontr� el usuario " + txtUsuario.getText() + " en el sistema", "Error de inicio de sesi�n", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnNewButton.setBounds(320, 11, 67, 23);
        panel_1.add(btnNewButton);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                regUser frame = new regUser();
                dispose();
                frame.setVisible(true);
            }
        });
        btnRegistrar.setBounds(397, 11, 92, 23);
        panel_1.add(btnRegistrar);

        JLabel lblNewLabel_1 = new JLabel("Usuario:");
        lblNewLabel_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(22, 53, 89, 14);
        panel.add(lblNewLabel_1);

        JLabel lblContrase�a = new JLabel("Contrase�a:");
        lblContrase�a.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
        lblContrase�a.setBounds(22, 100, 99, 14);
        panel.add(lblContrase�a);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(97, 51, 380, 20);
        panel.add(txtUsuario);
        txtUsuario.setColumns(10);

        txtContrase�a = new JPasswordField();
        txtContrase�a.setColumns(10);
        txtContrase�a.setBounds(112, 98, 365, 20);
        panel.add(txtContrase�a);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(10, 11, 46, 14);
        contentPanel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));

        JSeparator separator = new JSeparator();
        separator.setBounds(-20, 31, 567, 12);
        contentPanel.add(separator);
        separator.setForeground(new Color(255, 250, 240));
    }
}
