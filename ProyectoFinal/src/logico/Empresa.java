package logico;

import java.util.ArrayList;

public class Empresa {
	
	private ArrayList<Cliente>misClientes;
	private ArrayList<Contrato>misContratos;
	private ArrayList<Trabajador>misTrabajadores;
	private ArrayList<Proyecto>misProyectos;
	private static Empresa empresa = null;
	public static int idContrato;
	public static int idProyecto;
	public static int idCliente;
	public static int idTrabajador;
	public int cantContrato;
	public int cantProyecto;
	public int cantCliente;
	public int cantTrabajador;
	
	
	public Empresa() {
		super();
		misClientes = new ArrayList<>();
		misContratos = new ArrayList<>();
		misTrabajadores = new ArrayList<>();
		misProyectos = new ArrayList<>();
		idContrato = 1;
		idProyecto = 1;
		idCliente = 1;
	}
	
	public static Empresa getInstance() {
		if(empresa == null) {
			empresa = new Empresa();
		}
		return empresa;
	}

	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(ArrayList<Cliente> misClientes) {
		this.misClientes = misClientes;
	}

	public ArrayList<Contrato> getMisContratos() {
		return misContratos;
	}

	public void setMisContratos(ArrayList<Contrato> misContratos) {
		this.misContratos = misContratos;
	}

	public ArrayList<Trabajador> getMisTrabajadores() {
		return misTrabajadores;
	}

	public void setMisTrabajadores(ArrayList<Trabajador> misTrabajadores) {
		this.misTrabajadores = misTrabajadores;
	}

	public ArrayList<Proyecto> getMisProyectos() {
		return misProyectos;
	}

	public void setMisProyectos(ArrayList<Proyecto> misProyectos) {
		this.misProyectos = misProyectos;
	}

	public static Empresa getEmpresa() {
		return empresa;
	}

	public static void setEmpresa(Empresa empresa) {
		Empresa.empresa = empresa;
	}

	public static int getIdContrato() {
		return idContrato;
	}

	public static void setIdContrato(int idContrato) {
		Empresa.idContrato = idContrato;
	}

	public static int getIdProyecto() {
		return idProyecto;
	}

	public static void setIdProyecto(int idProyecto) {
		Empresa.idProyecto = idProyecto;
	}
	
	public void ingresarCliente(Cliente cliente){
		misClientes.add(cliente);
		idCliente++;
		cantCliente++;
	}
	public void ingresarContrato(Contrato contrato){
		misContratos.add(contrato);
		idContrato++;
		cantContrato++;
	}
	public void ingresarProyecto(Proyecto proyecto){
		misProyectos.add(proyecto);
		idProyecto++;
		cantProyecto++;
	}
	public void ingresarTrabajador(Trabajador trabajador){
		misTrabajadores.add(trabajador);
		idTrabajador++;
		cantTrabajador++;
	}
	

}
