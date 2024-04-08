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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;

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
			setBounds(100, 100, 479, 456);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBackground(new Color(230, 230, 250));
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			setLocationRelativeTo(null);
			
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
			panel.setBounds(20, 195, 419, 155);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, BorderLayout.CENTER);
			table = new JTable();
			table.setBackground(SystemColor.textHighlightText);
			table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			table.setColumnSelectionAllowed(true);
			table.setEnabled(false);
			table.setModel(model);
			scrollPane.setViewportView(table);
			
			String[] header = {"ID", "Nombre", "Apellido", "Evaluacion"};
			
			model = new DefaultTableModel();
			model.setColumnIdentifiers(header);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(SystemColor.menu);
			panel_1.setBorder(new LineBorder(new Color(160, 82, 45), 2, true));
			panel_1.setBounds(10, 35, 442, 333);
			contentPanel.add(panel_1);
			panel_1.setLayout(null);
			{
				JLabel lblNewLabel_1 = new JLabel("Buscar por:");
				lblNewLabel_1.setBounds(10, 11, 82, 14);
				panel_1.add(lblNewLabel_1);
				lblNewLabel_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
			}
			{
				JLabel lblNewLabel_2 = new JLabel("ID:");
				lblNewLabel_2.setBounds(10, 36, 30, 14);
				panel_1.add(lblNewLabel_2);
				lblNewLabel_2.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Nombre(s):");
				lblNewLabel_3.setBounds(10, 61, 96, 14);
				panel_1.add(lblNewLabel_3);
				lblNewLabel_3.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
			}
			{
				JLabel lblApellidos = new JLabel("Apellido(s):");
				lblApellidos.setBounds(10, 89, 96, 14);
				panel_1.add(lblApellidos);
				lblApellidos.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Evaluacion anual:");
				lblNewLabel_4.setBounds(10, 114, 119, 14);
				panel_1.add(lblNewLabel_4);
				lblNewLabel_4.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
			}
			
			Id = new JTextField();
			Id.setBounds(135, 34, 114, 20);
			panel_1.add(Id);
			Id.setColumns(10);
			
			nombre = new JTextField();
			nombre.setBounds(135, 59, 114, 20);
			panel_1.add(nombre);
			nombre.setColumns(10);
			
			Apellido = new JTextField();
			Apellido.setBounds(135, 87, 114, 20);
			panel_1.add(Apellido);
			Apellido.setColumns(10);
			
			JComboBox EvalAnual = new JComboBox();
			EvalAnual.setBounds(135, 112, 114, 20);
			panel_1.add(EvalAnual);
			EvalAnual.setBackground(Color.WHITE);
			
			JButton btnNuevo = new JButton("Nuevo");
			btnNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RegistroTrabajador t = new RegistroTrabajador();
					t.setModal(true);
					t.setVisible(true);
				}
			});
			btnNuevo.setBounds(285, 48, 89, 42);
			panel_1.add(btnNuevo);
			btnNuevo.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
			btnNuevo.setBackground(new Color(0, 255, 255));
			
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(285, 105, 89, 23);
			panel_1.add(btnBuscar);
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

						loadTrabajadoresbyId(Id.getText());
						loadTrabajadoresbyNombre(Id.getText());
						loadTrabajadoresbyApellido(Id.getText());
				}

			});
			btnBuscar.setBackground(new Color(51, 204, 153));
			btnBuscar.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
			{
				JLabel lblNewLabel = new JLabel("Trabajadores\r\n");
				lblNewLabel.setBounds(10, 11, 91, 25);
				contentPanel.add(lblNewLabel);
				lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
			}
			
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				buttonPane.setBackground(new Color(135, 206, 250));
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
		
		private void loadTrabajadoresbyApellido(String text) {
			// TODO Auto-generated method stub
			model.setRowCount(0);
			row = new Object[model.getColumnCount()];
			Trabajador t = Empresa.getInstance().BuscarTrabajadorById(text);
			for (Trabajador trabajador : Empresa.getInstance().getMisTrabajadores()) {
				if(t.getApellidos().equalsIgnoreCase(trabajador.getApellidos())) {
					row[0] = trabajador.getCedula();
					row[1] = trabajador.getNombre();
					row[2] = trabajador.getApellidos();
					row[3] = trabajador.getEvaluacionAnual();
				}
			}
			table.setModel(model);
		}

		private void loadTrabajadoresbyNombre(String text) {
			// TODO Auto-generated method stub
			model.setRowCount(0);
			row = new Object[model.getColumnCount()];
			Trabajador t = Empresa.getInstance().BuscarTrabajadorById(text);
			for (Trabajador trabajador : Empresa.getInstance().getMisTrabajadores()) {
				if(t.getNombre().equalsIgnoreCase(trabajador.getNombre())) {
					row[0] = trabajador.getCedula();
					row[1] = trabajador.getNombre();
					row[2] = trabajador.getApellidos();
					row[3] = trabajador.getEvaluacionAnual();
				}
			}
			table.setModel(model);
		}
		
		private void loadTrabajadoresbyId(String cedula) {
			model.setRowCount(0);
			row = new Object[model.getColumnCount()];
			Trabajador t = Empresa.getInstance().BuscarTrabajadorById(cedula);
			for (Trabajador trabajador : Empresa.getInstance().getMisTrabajadores()) {
				if(t.getCedula().equalsIgnoreCase(trabajador.getCedula())) {
					row[0] = trabajador.getCedula();
					row[1] = trabajador.getNombre();
					row[2] = trabajador.getApellidos();
					row[3] = trabajador.getEvaluacionAnual();
				}
			}
			table.setModel(model);
		}
			
		private void loadTrabajadores() {
				model.setRowCount(0);
				row = new Object[model.getColumnCount()];
				ArrayList<Trabajador> trabajadores  = Empresa.getInstance().getMisTrabajadores();
				for (Trabajador trabajador : trabajadores) {
						row[0] = trabajador.getCedula();
						row[1] = trabajador.getNombre();
						row[2] = trabajador.getApellidos();
						row[3] = trabajador.getEvaluacionAnual();
				} 
				table.setModel(model);
		}
	}


