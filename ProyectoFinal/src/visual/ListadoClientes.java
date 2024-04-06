package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class ListadoClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellido;
	private DefaultTableModel model;
	private Object[] row;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoClientes dialog = new ListadoClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoClientes() {
		setResizable(false);
		setBounds(100, 100, 479, 456);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 195, 419, 155);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		String[] header = {"ID", "Nombre", "Apellido", "Evaluacion"};
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		table = new JTable();
		table.setBackground(SystemColor.textHighlightText);
		table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setColumnSelectionAllowed(true);
		table.setEnabled(false);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBorder(new LineBorder(new Color(160, 82, 45), 2, true));
		panel_1.setBounds(10, 11, 442, 357);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Trabajadores\r\n");
			lblNewLabel.setBounds(10, 0, 91, 25);
			panel_1.add(lblNewLabel);
			lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		}
		{
			JSeparator separator = new JSeparator();
			separator.setForeground(new Color(0, 0, 0));
			separator.setBounds(10, 23, 429, 2);
			panel_1.add(separator);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Buscar por:");
			lblNewLabel_1.setBounds(10, 36, 82, 14);
			panel_1.add(lblNewLabel_1);
			lblNewLabel_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		}
		{
			JLabel lblNewLabel_2 = new JLabel("ID:");
			lblNewLabel_2.setBounds(10, 61, 30, 14);
			panel_1.add(lblNewLabel_2);
			lblNewLabel_2.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Nombre(s):");
			lblNewLabel_3.setBounds(10, 86, 96, 14);
			panel_1.add(lblNewLabel_3);
			lblNewLabel_3.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		}
		{
			JLabel lblApellidos = new JLabel("Apellido(s):");
			lblApellidos.setBounds(10, 114, 96, 14);
			panel_1.add(lblApellidos);
			lblApellidos.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Evaluacion anual:");
			lblNewLabel_4.setBounds(10, 139, 119, 14);
			panel_1.add(lblNewLabel_4);
			lblNewLabel_4.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		}
		
		Id = new JTextField();
		Id.setBounds(135, 59, 114, 20);
		panel_1.add(Id);
		Id.setColumns(10);
		
		nombre = new JTextField();
		nombre.setBounds(135, 84, 114, 20);
		panel_1.add(nombre);
		nombre.setColumns(10);
		
		Apellido = new JTextField();
		Apellido.setBounds(135, 112, 114, 20);
		panel_1.add(Apellido);
		Apellido.setColumns(10);
		
		JComboBox EvalAnual = new JComboBox();
		EvalAnual.setBounds(135, 137, 114, 20);
		panel_1.add(EvalAnual);
		EvalAnual.setBackground(Color.WHITE);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(285, 73, 89, 42);
		panel_1.add(btnNuevo);
		btnNuevo.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
		btnNuevo.setBackground(new Color(0, 255, 255));
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(285, 130, 89, 23);
		panel_1.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBuscar.setBackground(new Color(51, 204, 153));
		btnBuscar.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
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
		//loadClientes();
	}
}
