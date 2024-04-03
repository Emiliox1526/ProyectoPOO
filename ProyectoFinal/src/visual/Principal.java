package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 439);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setIcon(new ImageIcon(Principal.class.getResource("/images/Clientes_resized (7).jpg")));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(29, 143, 120, 122);
		contentPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(10, 32, 474, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel = new JLabel("Pagina Principal");
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 11));
		lblNewLabel.setBounds(29, 11, 128, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CLIENTES");
		lblNewLabel_1.setFont(new Font("Segoe Print", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(62, 118, 57, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton button = new JButton("");
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setForeground(Color.WHITE);
		button.setBackground(Color.WHITE);
		button.setBounds(191, 143, 120, 122);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(348, 143, 120, 122);
		contentPane.add(button_1);
		
		JLabel lblTrabajadores = new JLabel("TRABAJADORES");
		lblTrabajadores.setFont(new Font("Segoe Print", Font.PLAIN, 11));
		lblTrabajadores.setBounds(202, 118, 98, 14);
		contentPane.add(lblTrabajadores);
		
		JLabel lblProyectos = new JLabel("PROYECTOS");
		lblProyectos.setFont(new Font("Segoe Print", Font.PLAIN, 11));
		lblProyectos.setBounds(373, 118, 88, 14);
		contentPane.add(lblProyectos);
	}
}
