package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Empresa;
import logico.Trabajador;

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
	private JTextField Id;
	private JTextField nombre;
	private JTextField Apellido;
	private DefaultTableModel model;
	private Object[] row;
	private JTable table;

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
				lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
				lblNewLabel.setBounds(34, 11, 105, 14);
				contentPanel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Buscar por:");
				lblNewLabel_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
				lblNewLabel_1.setBounds(20, 49, 82, 14);
				contentPanel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("ID");
				lblNewLabel_2.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
				lblNewLabel_2.setBounds(20, 91, 30, 14);
				contentPanel.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Nombre (s)");
				lblNewLabel_3.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
				lblNewLabel_3.setBounds(20, 116, 96, 14);
				contentPanel.add(lblNewLabel_3);
			}
			{
				JLabel lblApellidos = new JLabel("Apellido (s)");
				lblApellidos.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
				lblApellidos.setBounds(20, 141, 96, 14);
				contentPanel.add(lblApellidos);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Evaluacion anual");
				lblNewLabel_4.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
				lblNewLabel_4.setBounds(20, 166, 119, 14);
				contentPanel.add(lblNewLabel_4);
			}
			
			Id = new JTextField();
			Id.setBounds(172, 92, 86, 14);
			contentPanel.add(Id);
			Id.setColumns(10);
			
			nombre = new JTextField();
			nombre.setColumns(10);
			nombre.setBounds(172, 117, 86, 14);
			contentPanel.add(nombre);
			
			Apellido = new JTextField();
			Apellido.setColumns(10);
			Apellido.setBounds(172, 142, 86, 14);
			contentPanel.add(Apellido);
			
			JComboBox EvalAnual = new JComboBox();
			EvalAnual.setBackground(Color.WHITE);
			EvalAnual.setBounds(172, 164, 86, 20);
			contentPanel.add(EvalAnual);
			
			JButton btnNuevo = new JButton("Nuevo");
			btnNuevo.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
			btnNuevo.setBackground(new Color(0, 255, 255));
			btnNuevo.setBounds(307, 113, 89, 42);
			contentPanel.add(btnNuevo);
			
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnBuscar.setBackground(Color.WHITE);
			btnBuscar.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
			btnBuscar.setBounds(172, 195, 89, 23);
			contentPanel.add(btnBuscar);
			
			JPanel panel = new JPanel();
			panel.setBounds(20, 260, 374, 90);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, BorderLayout.CENTER);
			
			String[] header = {"ID", "Nombre", "Apellido", "Evaluacion"};
			
			model = new DefaultTableModel();
			model.setColumnIdentifiers(header);
			table = new JTable();
			table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			table.setColumnSelectionAllowed(true);
			table.setEnabled(false);
			table.setModel(model);
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
			loadTrabajadores();
		}
		
		private void loadTrabajadores() {
			model.setRowCount(0);
			row = new Object[model.getColumnCount()];
			for (Trabajador trabajador : Empresa.getInstance().getMisTrabajadores()) {
				row[0] = trabajador.getCedula();
				row[1] = trabajador.getNombre();
				row[2] = trabajador.getApellidos();
				row[3] = trabajador.getEvaluacionAnual();
			} 
			
		}
	}


