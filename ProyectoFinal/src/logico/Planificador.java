package logico;

import java.sql.Date;

public class Planificador extends Trabajador {
	private int frecuenciaDePlanificacion;

	public Planificador(String cedula, String nombre, String apellidos, String direccionParticular, String sexo,
			Date fechaDeNacimiento, String evaluacionAnual, int frecuenciaDePlanificacion) {
		super(cedula, nombre, apellidos, direccionParticular, sexo, fechaDeNacimiento, evaluacionAnual);
		this.frecuenciaDePlanificacion = frecuenciaDePlanificacion;
	}

	public int getFrecuenciaDePlanificacion() {
		return frecuenciaDePlanificacion;
	}

	public void setFrecuenciaDePlanificacion(int frecuenciaDePlanificacion) {
		this.frecuenciaDePlanificacion = frecuenciaDePlanificacion;
	}
}
