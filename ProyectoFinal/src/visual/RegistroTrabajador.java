package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class RegistroTrabajador extends JDialog {
	private JTextField Cedula;
	private JTextField Nombre;
	private JTextField textField;
	private JTextField Direccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroTrabajador dialog = new RegistroTrabajador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroTrabajador() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 360, 534);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Nuevo trabajador\r\n");
			lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 12));
			lblNewLabel.setBounds(20, 11, 97, 25);
			panel.add(lblNewLabel);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(10, 33, 312, 2);
			panel.add(separator);
			
			JLabel lblNewLabel_1 = new JLabel("Cedula:\r\n");
			lblNewLabel_1.setBounds(10, 62, 88, 14);
			panel.add(lblNewLabel_1);
			
			JLabel lblApellidos = new JLabel("Apellido (s):");
			lblApellidos.setBounds(10, 134, 88, 14);
			panel.add(lblApellidos);
			
			JLabel label_1 = new JLabel("Nombre (s):");
			label_1.setBounds(10, 99, 88, 14);
			panel.add(label_1);
			
			JLabel lblSexo = new JLabel("Sexo:");
			lblSexo.setBounds(10, 168, 59, 14);
			panel.add(lblSexo);
			
			JLabel lblEdad = new JLabel("Edad:");
			lblEdad.setBounds(10, 201, 88, 14);
			panel.add(lblEdad);
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(10, 235, 88, 14);
			panel.add(lblDireccion);
			
			JLabel lblNewLabel_2 = new JLabel("Rol:");
			lblNewLabel_2.setBounds(10, 272, 46, 14);
			panel.add(lblNewLabel_2);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.setBounds(42, 272, 97, 102);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			Cedula = new JTextField();
			Cedula.setBounds(94, 59, 86, 20);
			panel.add(Cedula);
			Cedula.setColumns(10);
			
			Nombre = new JTextField();
			Nombre.setColumns(10);
			Nombre.setBounds(94, 96, 86, 20);
			panel.add(Nombre);
			
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(94, 131, 86, 20);
			panel.add(textField);
			
			JSpinner spinner = new JSpinner();
			spinner.setBounds(94, 198, 46, 20);
			panel.add(spinner);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(94, 165, 59, 20);
			panel.add(comboBox);
			
			Direccion = new JTextField();
			Direccion.setColumns(10);
			Direccion.setBounds(94, 232, 118, 20);
			panel.add(Direccion);
		}
	}
}
