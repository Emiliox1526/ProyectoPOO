package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoTrabajador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private DefaultTableModel model;
	private Object[] row;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoTrabajador dialog = new ListadoTrabajador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	
		public ListadoTrabajador() {
		setResizable(false);
			setBounds(100, 100, 435, 423);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			{
				JSeparator separator = new JSeparator();
				separator.setBounds(10, 36, 374, 2);
				contentPanel.add(separator);
			}
			{
				JLabel lblNewLabel = new JLabel("Trabajadores\r\n");
				lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 15));
				lblNewLabel.setBounds(34, 11, 105, 14);
				contentPanel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Buscar por:");
				lblNewLabel_1.setFont(new Font("Segoe Print", Font.PLAIN, 14));
				lblNewLabel_1.setBounds(20, 49, 82, 14);
				contentPanel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("ID");
				lblNewLabel_2.setFont(new Font("Segoe Print", Font.PLAIN, 14));
				lblNewLabel_2.setBounds(20, 91, 30, 14);
				contentPanel.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Nombre (s)");
				lblNewLabel_3.setFont(new Font("Segoe Print", Font.PLAIN, 14));
				lblNewLabel_3.setBounds(20, 116, 96, 14);
				contentPanel.add(lblNewLabel_3);
			}
			{
				JLabel lblApellidos = new JLabel("Apellido (s)");
				lblApellidos.setFont(new Font("Segoe Print", Font.PLAIN, 14));
				lblApellidos.setBounds(20, 141, 96, 14);
				contentPanel.add(lblApellidos);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Evaluacion anual");
				lblNewLabel_4.setFont(new Font("Segoe Print", Font.PLAIN, 14));
				lblNewLabel_4.setBounds(20, 166, 119, 14);
				contentPanel.add(lblNewLabel_4);
			}
			
			textField = new JTextField();
			textField.setBounds(172, 92, 86, 14);
			contentPanel.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(172, 117, 86, 14);
			contentPanel.add(textField_1);
			
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(172, 142, 86, 14);
			contentPanel.add(textField_2);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setBackground(Color.WHITE);
			comboBox.setBounds(172, 164, 86, 20);
			contentPanel.add(comboBox);
			
			JButton btnNewButton = new JButton("Nuevo");
			btnNewButton.setFont(new Font("Segoe Print", Font.PLAIN, 11));
			btnNewButton.setBackground(new Color(0, 255, 255));
			btnNewButton.setBounds(307, 113, 89, 42);
			contentPanel.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Buscar");
			btnNewButton_1.setBackground(Color.WHITE);
			btnNewButton_1.setFont(new Font("Segoe Print", Font.PLAIN, 11));
			btnNewButton_1.setBounds(172, 195, 89, 23);
			contentPanel.add(btnNewButton_1);
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(34, 243, 350, 97);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			
			
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, BorderLayout.CENTER);
			
			table = new JTable();
			table.setBackground(Color.WHITE);
			scrollPane.setViewportView(table);
			{
				
				
				JPanel buttonPane = new JPanel();
				buttonPane.setBackground(Color.WHITE);
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					
					JButton cancelButton = new JButton("Cerrar\r\n");
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
	}


