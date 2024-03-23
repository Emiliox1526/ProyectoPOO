package logico;

import java.util.ArrayList;
import java.util.Date;

public class Proyecto {
	
	private String id;
	private Cliente cliente;
	private ArrayList<Trabajador>participantes;
	private Contrato contrato;
	private Date fechaInicio;
	private Date fechaEntrega;
	
	public Proyecto(String id, Cliente cliente, ArrayList<Trabajador> participantes, Contrato contrato,
			Date fechaInicio, Date fechaEntrega) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.participantes = participantes;
		this.contrato = contrato;
		this.fechaInicio = fechaInicio;
		this.fechaEntrega = fechaEntrega;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Trabajador> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(ArrayList<Trabajador> participantes) {
		this.participantes = participantes;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	

}
