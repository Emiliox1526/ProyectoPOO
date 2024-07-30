package logico;

import java.sql.Date;

public class Planificador extends Trabajador {
	private int frecuenciaDePlanificacion;


	public Planificador(String cedula, String nombre, String apellidos, String direccionParticular, String sexo,
			Date date, String evaluacionAnual, int cantProyectosFallidos, int cantProyectos,
			int frecuenciaDePlanificacion) {
		super(cedula, nombre, apellidos, direccionParticular, sexo, date, evaluacionAnual, cantProyectosFallidos,
				cantProyectos);
		this.frecuenciaDePlanificacion = frecuenciaDePlanificacion;
	}

	public int getFrecuenciaDePlanificacion() {
		return frecuenciaDePlanificacion;
	}

	public void setFrecuenciaDePlanificacion(int frecuenciaDePlanificacion) {
		this.frecuenciaDePlanificacion = frecuenciaDePlanificacion;
	}
}
