package logico;

import java.sql.Date;
import java.time.LocalDate;

public class Trabajador {
	
	static LocalDate fechaActual = LocalDate.now();
	protected String cedula;
	protected String nombre;
	protected String apellidos;
	protected String direccionParticular;
	protected String sexo;
	protected java.util.Date FechaDeNacimiento;
	protected String evaluacionAnual;
	public Trabajador(String cedula, String nombre, String apellidos, String direccionParticular, String sexo,
			java.util.Date fecha, String evaluacionAnual) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccionParticular = direccionParticular;
		this.sexo = sexo;
		FechaDeNacimiento = fecha;
		this.evaluacionAnual = evaluacionAnual;
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
		return FechaDeNacimiento;
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
	
}
