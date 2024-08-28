package logico;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L; 
    private Cliente cliente;
    private ArrayList<Trabajador> participantes;
    private Contrato contrato;
    private Date fechaInicio;
    private Date fechaEntregaInicial;
    private Date fechaEntregaFinal;
    private Date fechaProrroga;
    private boolean isPenalizado;

    public Proyecto(Cliente cliente, ArrayList<Trabajador> participantes, Contrato contrato,
                    java.util.Date fechaI, java.util.Date fechaF, java.util.Date fechaF2, Date fechaProrroga,
                    boolean isPenalizado) {
        this.cliente = cliente;
        this.participantes = participantes;
        this.contrato = contrato;
        this.fechaInicio = (Date) fechaI;
        this.fechaEntregaInicial = (Date) fechaF;
        this.fechaEntregaFinal = (Date) fechaF2;
        this.fechaProrroga = fechaProrroga;
        this.isPenalizado = isPenalizado;
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
    
    public Contrato getContrato(){ 
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
    
    public Date getFechaEntregaInicial() { 
    	return fechaEntregaInicial; 
    }
    
    public void setFechaEntregaInicial(Date fechaEntregaInicial) { 
    	this.fechaEntregaInicial = fechaEntregaInicial; 
    }
    
    public Date getFechaEntregaFinal() { 
    	return fechaEntregaFinal; 
    }
    
    public void setFechaEntregaFinal(Date fechaEntregaFinal) { 
    	this.fechaEntregaFinal = fechaEntregaFinal; 
    }
    
    public Date getFechaProrroga() { 
    	return fechaProrroga; 
    }
    
    public void setFechaProrroga(Date fechaProrroga) { 
    	this.fechaProrroga = fechaProrroga; 
    }
    
    public boolean isPenalizado() { 
    	return isPenalizado; 
    }
    
    public void setPenalizado(boolean penalizado) { 
    	isPenalizado = penalizado; 
    }
    
    
}
