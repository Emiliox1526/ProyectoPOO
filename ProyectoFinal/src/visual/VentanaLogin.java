package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

public class VentanaLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JTextField txtContraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaLogin dialog = new VentanaLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaLogin() {
		setBounds(100, 100, 535, 327);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(288, 10, 0, 0);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
		}
		
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
		btnNewButton.setBounds(320, 11, 67, 23);
		panel_1.add(btnNewButton);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(397, 11, 92, 23);
		panel_1.add(btnRegistrar);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(22, 53, 89, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		lblContrasea.setBounds(22, 100, 99, 14);
		panel.add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(112, 51, 365, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContraseña = new JTextField();
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(112, 98, 365, 20);
		panel.add(txtContraseña);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		{
			JSeparator separator = new JSeparator();
			separator.setBounds(-20, 31, 567, 12);
			contentPanel.add(separator);
			separator.setForeground(new Color(255, 255, 255));
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(105, 105, 105));
		panel_2.setBounds(0, 0, 519, 33);
		contentPanel.add(panel_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
