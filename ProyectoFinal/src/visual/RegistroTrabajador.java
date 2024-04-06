package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
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
import javax.swing.border.TitledBorder;

import logico.Jefe;
import logico.Trabajador;

import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class RegistroTrabajador extends JDialog {
	private JTextField Cedula;
	private JTextField Nombre;
	private JTextField textField;
	private JTextField Direccion;
	private ButtonGroup botones = new ButtonGroup();
	private JTextField JefeText;
	private JTextField textField_1;

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
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 418, 563);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			setLocationRelativeTo(null);
			JLabel lblNewLabel = new JLabel("Nuevo trabajador\r\n");
			lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 12));
			lblNewLabel.setBounds(20, 11, 97, 25);
			panel.add(lblNewLabel);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(20, 34, 350, 2);
			panel.add(separator);
			
			JLabel lblNewLabel_1 = new JLabel("Cedula:\r\n");
			lblNewLabel_1.setBounds(29, 62, 88, 14);
			panel.add(lblNewLabel_1);
			
			JLabel lblApellidos = new JLabel("Apellido (s):");
			lblApellidos.setBounds(29, 108, 88, 14);
			panel.add(lblApellidos);
			
			JLabel label_1 = new JLabel("Nombre (s):");
			label_1.setBounds(201, 62, 88, 14);
			panel.add(label_1);
			
			JLabel lblSexo = new JLabel("Sexo:");
			lblSexo.setBounds(201, 108, 59, 14);
			panel.add(lblSexo);
			
			JLabel lblEdad = new JLabel("Fecha:");
			lblEdad.setBounds(20, 156, 48, 14);
			panel.add(lblEdad);
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(201, 156, 88, 14);
			panel.add(lblDireccion);
			
			JLabel lblNewLabel_2 = new JLabel("Rol:");
			lblNewLabel_2.setBounds(20, 274, 46, 14);
			panel.add(lblNewLabel_2);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.setBackground(new Color(240, 255, 240));
			panel_1.setBounds(53, 274, 112, 165);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			

			Cedula = new JTextField();
			Cedula.setBounds(105, 59, 86, 20);
			panel.add(Cedula);
			Cedula.setColumns(10);
			
			Nombre = new JTextField();
			Nombre.setColumns(10);
			Nombre.setBounds(274, 59, 86, 20);
			panel.add(Nombre);
			
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(105, 105, 86, 20);
			panel.add(textField);
			
			JSpinner spinner = new JSpinner();
			spinner.setBounds(108, 206, 46, 20);
			panel.add(spinner);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(274, 105, 59, 20);
			panel.add(comboBox);
			
			comboBox.addItem("         ");
			comboBox.addItem("M");
			comboBox.addItem("F");
			
			Direccion = new JTextField();
			Direccion.setColumns(10);
			Direccion.setBounds(276, 153, 97, 20);
			panel.add(Direccion);
			
			JLabel JefeLabel = new JLabel("Cantidad de trabajadores:");
			JefeLabel.setBounds(175, 293, 127, 14);
			panel.add(JefeLabel);
			JefeLabel.setVisible(false);
			
			JLabel DiseniadorLabel = new JLabel("A\u00F1os de eperiencia:");
			DiseniadorLabel.setBounds(175, 331, 97, 14);
			panel.add(DiseniadorLabel);
			DiseniadorLabel.setVisible(false);
			
			JLabel ProgramadorLabel = new JLabel("Programador:");
			ProgramadorLabel.setBounds(175, 373, 71, 14);
			panel.add(ProgramadorLabel);
			ProgramadorLabel.setVisible(false);
			
			JefeText = new JTextField();
			JefeText.setColumns(10);
			JefeText.setBounds(287, 373, 86, 37);
			panel.add(JefeText);
			JefeText.setVisible(false);
			
			JLabel PlanificadorLabel = new JLabel("Frecuencia de planificacion:");
			PlanificadorLabel.setBounds(175, 422, 144, 14);
			panel.add(PlanificadorLabel);
			PlanificadorLabel.setVisible(false);
			
			JSpinner jefeSpinner = new JSpinner();
			jefeSpinner.setBounds(326, 290, 44, 20);
			panel.add(jefeSpinner);
			jefeSpinner.setVisible(false);
			
			JSpinner DiseniadorSpinner = new JSpinner();
			DiseniadorSpinner.setBounds(326, 328, 44, 20);
			panel.add(DiseniadorSpinner);
			DiseniadorSpinner.setVisible(false);
			
			JSpinner PlanificadorSpinner = new JSpinner();
			PlanificadorSpinner.setBounds(326, 419, 44, 20);
			PlanificadorSpinner.setVisible(false);
			panel.add(PlanificadorSpinner);
			
			JRadioButton rdbtnNewRadioButton = new JRadioButton("Jefe");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JefeLabel.setVisible(true);
					DiseniadorLabel.setVisible(false);
					ProgramadorLabel.setVisible(false);
					JefeText.setVisible(false);
					PlanificadorLabel.setVisible(false);
					jefeSpinner.setVisible(true);
					DiseniadorSpinner.setVisible(false);
					PlanificadorSpinner.setVisible(false);
					
				}
			});
			rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnNewRadioButton.setBackground(new Color(240, 255, 240));
			rdbtnNewRadioButton.setBounds(6, 7, 100, 23);
			panel_1.add(rdbtnNewRadioButton);
			
			JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Dise\u00F1ador");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JefeLabel.setVisible(false);
					DiseniadorLabel.setVisible(true);
					ProgramadorLabel.setVisible(false);
					JefeText.setVisible(false);
					PlanificadorLabel.setVisible(false);
					jefeSpinner.setVisible(false);
					DiseniadorSpinner.setVisible(true);
					PlanificadorSpinner.setVisible(false);
				}
			});
			rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnNewRadioButton_1.setBackground(new Color(240, 255, 240));
			rdbtnNewRadioButton_1.setBounds(6, 49, 100, 23);
			panel_1.add(rdbtnNewRadioButton_1);
			
			JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Programador");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JefeLabel.setVisible(false);
					DiseniadorLabel.setVisible(false);
					ProgramadorLabel.setVisible(true);
					JefeText.setVisible(true);
					PlanificadorLabel.setVisible(false);
					jefeSpinner.setVisible(false);
					DiseniadorSpinner.setVisible(false);
					PlanificadorSpinner.setVisible(false);
				}
			});
			rdbtnNewRadioButton_2.setBackground(new Color(240, 255, 240));
			rdbtnNewRadioButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnNewRadioButton_2.setBounds(6, 92, 100, 23);
			panel_1.add(rdbtnNewRadioButton_2);
			
			JRadioButton rdbtnPlanificador = new JRadioButton("Planificador");
			rdbtnPlanificador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				JefeLabel.setVisible(false);
				DiseniadorLabel.setVisible(false);
				ProgramadorLabel.setVisible(false);
				JefeText.setVisible(false);
				PlanificadorLabel.setVisible(true);
				jefeSpinner.setVisible(false);
				DiseniadorSpinner.setVisible(false);
				PlanificadorSpinner.setVisible(true);
				}
			});
			rdbtnPlanificador.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnPlanificador.setBackground(new Color(240, 255, 240));
			rdbtnPlanificador.setBounds(6, 133, 100, 23);
			panel_1.add(rdbtnPlanificador);
			
			botones.add(rdbtnNewRadioButton);
			botones.add(rdbtnNewRadioButton_1);
			botones.add(rdbtnNewRadioButton_2);
			botones.add(rdbtnPlanificador);
			
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(0, 478, 404, 46);
			panel.add(panel_2);
			panel_2.setLayout(null);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Trabajador t = null;
						String id = Cedula.getText();
						String nombre = Nombre.getName();
						String Apellido = textField.getText(); // apellido
						String direccion = Direccion.getText();
						String Sexo = comboBox.getSelectedItem().toString();
						if(rdbtnNewRadioButton.isSelected()){
							t.setNombre(nombre);
							t.setApellidos(Apellido);
							t.setCedula(id);
							t.setDireccionParticular(direccion);
							t.setSexo(Sexo);
							
							t = new Jefe(id, nombre, Apellido, direccion, Sexo, null, jefeSpinner.getValue());
						}
						if(rbtnQCilindro.isSelected()){
							
						}
						if(rbtnQCHueco.isSelected()){
							
						}
						
					}
				});
				okButton.setBounds(223, 11, 89, 23);
				panel_2.add(okButton);
				okButton.setBackground(Color.GREEN);
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.setBounds(322, 11, 72, 23);
				panel_2.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setBackground(new Color(255, 99, 71));
				cancelButton.setActionCommand("Cancel");
			}
			
			JLabel lblNewLabel_3 = new JLabel("Fecha de nacimiento:");
			lblNewLabel_3.setBounds(201, 209, 118, 14);
			panel.add(lblNewLabel_3);
			
			textField_1 = new JTextField();
			textField_1.setBounds(306, 206, 59, 20);
			panel.add(textField_1);
			textField_1.setColumns(10);
			
			JDateChooser dateChooser = new JDateChooser();
			dateChooser.setBounds(89, 156, 100, 22);
			panel.add(dateChooser);
		}
	}
	
	void botones() {
		
	}
}
