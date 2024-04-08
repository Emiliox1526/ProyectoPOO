package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import logico.Cliente;
import logico.Empresa;

public class RegistroCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		setBounds(100, 100, 290, 306);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		
		JLabel lblNewLabel = new JLabel("Registro cliente");
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 21, 102, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(20, 116, 64, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cedula");
		lblNewLabel_2.setBounds(20, 68, 46, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Direccion:");
		lblNewLabel_3.setBounds(20, 162, 64, 14);
		contentPanel.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(94, 65, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(94, 116, 123, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(94, 159, 139, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 36, 254, 168);
		contentPanel.add(panel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setBackground(new Color(176, 196, 222));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar\r\n");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Cliente cliente = new Cliente(textField.getText(), textField_1.getText(), textField_2.getText() ,0 );
						JOptionPane.showMessageDialog(null,"Registro Satisfactorio" ,"Informaci n", JOptionPane.INFORMATION_MESSAGE);
						loadCliente(cliente);
						clean();
					}
				});
				okButton.setBackground(new Color(0, 255, 0));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
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
		}
	}
	
	void loadCliente(Cliente cliente){
		
		Empresa.getInstance().ingresarCliente(cliente);
		
		
	}
	
	void clean() {
		textField.setText(" ");
		textField_1.setText(" ");
		textField_2.setText(" ");
	}
}
