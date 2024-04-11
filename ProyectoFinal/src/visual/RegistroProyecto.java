package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import logico.Cliente;
import logico.Contrato;
import logico.Empresa;
import logico.Trabajador;

public class RegistroProyecto extends JDialog {
	private JTextField txtidProyecto;
	private JTextField txtidContrato;
	private JTable tableTrabajadores;
	private JTable tableTrabajadoresAgregados;
	ArrayList<Trabajador> listaTrabajadores = new ArrayList<>();
	private static DefaultTableModel model;
	private static DefaultTableModel modelAgregado;
	private JTextField txtIdCliente;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private Cliente cliente = null;
	private int indexSeleccionadoTrabajadores = -1;
	private int indexSeleccionadoAgregados = -1;
	ArrayList<Trabajador> listaAgregados = new ArrayList<>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroProyecto dialog = new RegistroProyecto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroProyecto() {
		for(Trabajador trabajador: Empresa.getInstance().getMisTrabajadores()) {
			listaTrabajadores.add(trabajador);
		}
		setBounds(100, 100, 659, 563);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.setBackground(new Color(255, 99, 71));
				cancelButton.setForeground(new Color(255, 255, 255));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panelprincipal = new JPanel();
			getContentPane().add(panelprincipal, BorderLayout.CENTER);
			panelprincipal.setLayout(null);
			panelprincipal.setBackground(new Color(230,230,250));
			
			JPanel panelDatos = new JPanel();
			panelDatos.setBounds(12, 44, 618, 437);
			panelprincipal.add(panelDatos);
			panelDatos.setLayout(null);
			
			JLabel lblidProyecto = new JLabel("Proyecto:");
			lblidProyecto.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
			lblidProyecto.setBounds(12, 13, 75, 14);
			panelDatos.add(lblidProyecto);
			
			txtidProyecto = new JTextField();
			txtidProyecto.setEditable(false);
			txtidProyecto.setBounds(150, 10, 150, 20);
			txtidProyecto.setText("P-"+Empresa.getInstance().idProyecto);
			panelDatos.add(txtidProyecto);
			txtidProyecto.setColumns(10);
			
			JLabel lblcliente = new JLabel("Cedula Cliente:");
			lblcliente.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
			lblcliente.setBounds(12, 45, 120, 14);
			panelDatos.add(lblcliente);
			
			JLabel lblcontrato = new JLabel("Contrato:");
			lblcontrato.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
			lblcontrato.setBounds(12, 111, 75, 14);
			panelDatos.add(lblcontrato);
			
			txtidContrato = new JTextField();
			txtidContrato.setEditable(false);
			txtidContrato.setColumns(10);
			txtidContrato.setBounds(150, 108, 150, 20);
			txtidContrato.setText("C-"+Empresa.getInstance().idContrato);
			panelDatos.add(txtidContrato);
			
			JLabel lblfechaInicio = new JLabel("Fecha de inicio:");
			lblfechaInicio.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
			lblfechaInicio.setBounds(12, 144, 110, 14);
			panelDatos.add(lblfechaInicio);
			
			JLabel lblfechaEntrega = new JLabel("Fecha de entrega:");
			lblfechaEntrega.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
			lblfechaEntrega.setBounds(12, 177, 120, 14);
			panelDatos.add(lblfechaEntrega);
			
			JDateChooser dChooserInicio = new JDateChooser();
			dChooserInicio.setBounds(150, 141, 150, 20);
			panelDatos.add(dChooserInicio);
			
			JDateChooser dChooserFinal = new JDateChooser();
			dChooserFinal.setBounds(150, 174, 150, 20);
			panelDatos.add(dChooserFinal);
			
			JPanel panelTrabajadores = new JPanel();
			panelTrabajadores.setBackground(new Color(175, 238, 238));
			panelTrabajadores.setBounds(12, 222, 594, 202);
			panelDatos.add(panelTrabajadores);
			panelTrabajadores.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 13, 270, 140);
			panelTrabajadores.add(scrollPane, BorderLayout.CENTER);
			
			tableTrabajadores = new JTable();
			tableTrabajadores.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					indexSeleccionadoTrabajadores = tableTrabajadores.getSelectedRow();
				}
			});
			tableTrabajadores.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
			scrollPane.setColumnHeaderView(tableTrabajadores);
			String [] headers = {"Cedula", "Nombre", "Apellido", "Rol","Dato del Rol"};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(headers);
			String [] headersAgregados = {"Cedula", "Nombre", "Apellido", "Rol","Dato del Rol"};
			modelAgregado = new DefaultTableModel();
			modelAgregado.setColumnIdentifiers(headersAgregados);
					
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(312, 13, 270, 140);
			panelTrabajadores.add(scrollPane_1);
			
			tableTrabajadoresAgregados = new JTable();
			tableTrabajadoresAgregados.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					indexSeleccionadoAgregados = tableTrabajadoresAgregados.getSelectedRow();
				}
			});
			tableTrabajadoresAgregados.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
			scrollPane_1.setColumnHeaderView(tableTrabajadoresAgregados);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(135, 206, 250));
			panel.setBounds(0, 166, 594, 36);
			panelTrabajadores.add(panel);
			
			JButton btnAgregar = new JButton("Agregar");
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Trabajador TrabajadorAgregado = listaTrabajadores.get(indexSeleccionadoTrabajadores);
		            if (listaAgregados.size() < 5) {
		                listaAgregados.add(TrabajadorAgregado);
		                listaTrabajadores.remove(indexSeleccionadoTrabajadores);
		                loadTrabajadores();
		            } else {
		                JOptionPane.showMessageDialog(null, "No se permiten mas de 3 lenguajes", "Error", JOptionPane.ERROR_MESSAGE);
		            }
				}
			});
			btnAgregar.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
			panel.add(btnAgregar);
			
			JButton btnQuitar = new JButton("Quitar");
			btnQuitar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Trabajador trabajadorEliminado = listaAgregados.get(indexSeleccionadoAgregados);
					listaTrabajadores.add(trabajadorEliminado);
		            listaAgregados.remove(indexSeleccionadoAgregados);
		            loadTrabajadores();
				}
			});
			btnQuitar.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
			panel.add(btnQuitar);
			
			JLabel lblTrabajadores = new JLabel("Trabajadores");
			lblTrabajadores.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
			lblTrabajadores.setBounds(243, 202, 120, 14);
			panelDatos.add(lblTrabajadores);
			
			txtIdCliente = new JTextField();
			txtIdCliente.setColumns(10);
			txtIdCliente.setBounds(150, 43, 150, 20);
			panelDatos.add(txtIdCliente);
			
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				String id = txtIdCliente.getText();
				if (buscarCliente (id) != null) {
					cliente = buscarCliente(id);
					txtNombre.setText(cliente.getNombre());
					txtApellido.setText(cliente.getApellido());
				} else {
					JOptionPane.showMessageDialog(null,"Cliente no encontrado para la cedula: " + id+ ". Puede crear el cliente en el botón de agregar nuevo" ,"Error", JOptionPane.ERROR_MESSAGE);
				}
				}

			});
			btnBuscar.setBounds(312, 40, 120, 25);
			panelDatos.add(btnBuscar);
			
			JButton btnNewButton_1 = new JButton("Agregar Nuevo");
			btnNewButton_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					RegistroCliente c = new RegistroCliente();
					c.setModal(true);
					c.setVisible(true);
				}
			});
			btnNewButton_1.setBounds(440, 40, 120, 25);
			panelDatos.add(btnNewButton_1);
			
			JLabel lblNombre = new JLabel("Nombre(s):");
			lblNombre.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
			lblNombre.setBounds(12, 78, 75, 14);
			panelDatos.add(lblNombre);
			
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setColumns(10);
			txtNombre.setBounds(150, 75, 150, 20);
			panelDatos.add(txtNombre);
			
			txtApellido = new JTextField();
			txtApellido.setEditable(false);
			txtApellido.setColumns(10);
			txtApellido.setBounds(410, 75, 150, 20);
			panelDatos.add(txtApellido);
			
			JLabel lblApellido = new JLabel("Apellido(s):");
			lblApellido.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
			lblApellido.setBounds(312, 79, 75, 14);
			panelDatos.add(lblApellido);
			
			JLabel lblNombreVentana = new JLabel("Nuevo proyecto");
			lblNombreVentana.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
			lblNombreVentana.setBounds(12, 13, 126, 25);
			panelprincipal.add(lblNombreVentana);
		}
		loadTrabajadores();
	}
	
	private void loadTrabajadores() {

	    model.setRowCount(0);
	    for (Trabajador trabajador : listaTrabajadores) {
	        model.addRow(new Object[]{trabajador});
	    }
	    tableTrabajadores.setModel(model);


	    modelAgregado.setRowCount(0);
	    for (Trabajador Tagregado : listaAgregados) {
	        modelAgregado.addRow(new Object[]{Tagregado});
	    }
	    tableTrabajadoresAgregados.setModel(modelAgregado);
	}
	
	private Cliente buscarCliente(String id) {
		Cliente aux = null;
		for (Cliente c : Empresa.getInstance().getMisClientes()) {
			if (aux.getId().equalsIgnoreCase(id)) {
				aux = c;
			}		
		}
		return aux;
	}
}
