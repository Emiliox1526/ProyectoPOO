package logico;

import java.io.Serializable;
import java.util.Date;

public class Contrato implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private int idCliente;
	private String nombre;
	private Date fechaEntrega;
	private Date fechaInicio;
	private int id_proyecto;
	


	public Contrato (int idCliente, String nombre, Date fechaEntrega, Date fechaInicio, int id_proyecto) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.fechaEntrega = fechaEntrega;
		this.fechaInicio = fechaInicio;
		this.id_proyecto = id_proyecto;
	}


	public int getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Date getFechaEntrega() {
		return fechaEntrega;
	}


	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public int getId_proyecto() {
		return id_proyecto;
	}


	public void setId_proyecto(int id_proyecto) {
		this.id_proyecto = id_proyecto;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public float calcularPenalizacion() {
		return 0;
	}
	

}
