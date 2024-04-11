package logico;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Trabajador  implements Serializable {
	private static final long serialVersionUID = 1L;
	static LocalDate fechaActual = LocalDate.now();
	protected String cedula;
	protected String nombre;
	protected String apellidos;
	protected String direccionParticular;
	protected String sexo;
	protected java.util.Date FechaDeNacimiento;
	protected String evaluacionAnual;
	protected int cantProyectosFallidos;
	protected int cantProyectosTotal;
	public Trabajador(String cedula, String nombre, String apellidos, String direccionParticular, String sexo,
			java.util.Date date, String evaluacionAnual,int cantProyectosFallidos, int cantProyectos) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccionParticular = direccionParticular;
		this.sexo = sexo;
		FechaDeNacimiento = date;
		this.evaluacionAnual = evaluacionAnual;
		this.cantProyectosFallidos = cantProyectosFallidos;
		this.cantProyectosTotal = cantProyectos;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccionParticular() {
		return direccionParticular;
	}
	public void setDireccionParticular(String direccionParticular) {
		this.direccionParticular = direccionParticular;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getFechaDeNacimiento() {
		return (Date) FechaDeNacimiento;
	}
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		FechaDeNacimiento = fechaDeNacimiento;
	}
	public String getEvaluacionAnual() {
		return evaluacionAnual;
	}
	public void setEvaluacionAnual(String evaluacionAnual) {
		this.evaluacionAnual = evaluacionAnual;
	}
	
	
	int calcularEdad(){
		int edadanios = 0;
		int edadDias = 0;
		int edad = 0;
		
		edadanios = fechaActual.getYear() - FechaDeNacimiento.getYear();
		edadDias = fechaActual.getDayOfYear() - FechaDeNacimiento.getDay();
		
		if (edadDias < 0) {
			edad = edadanios + 1;
		}else {
			edad = edadanios;
		}
		
		return edad;
		
	}
	public int getCantProyectosFallidos() {
		return cantProyectosFallidos;
	}
	public void setCantProyectosFallidos(int cantProyectosFallidos) {
		this.cantProyectosFallidos = cantProyectosFallidos;
	}
	public int getCantProyectosTotal() {
		return cantProyectosTotal;
	}
	public void setCantProyectosTotal(int cantProyectosTotal) {
		this.cantProyectosTotal = cantProyectosTotal;
	}
	String calcularEvaluacionAnual(){
		float porcentaje = ((getCantProyectosFallidos()/getCantProyectosTotal())*100);
		String evaluacion = null;
		if(porcentaje >= 0 && porcentaje <= 30) {//Destacado//
			evaluacion = "Destacado";
		}
		if(porcentaje >= 31 && porcentaje <= 60) {//Cumplido//
			evaluacion = "Cumplidor";
		}
		if(porcentaje >= 61 && porcentaje <= 100) {//Incumplidor//
			evaluacion = "Incumplidor";
		}
		return evaluacion;
	}
}
