package logico;

public class Planificadores extends Trabajador {
	private int frecuenciaDePlanificacion;

	public Planificadores(String cedula, String nombre, String apellidos, String direccionParticular, String sexo,
			int edad, String evaluacionAnual, int frecuenciaDePlanificacion) {
		super(cedula, nombre, apellidos, direccionParticular, sexo, edad, evaluacionAnual);
		this.frecuenciaDePlanificacion = frecuenciaDePlanificacion;
	}

	public int getFrecuenciaDePlanificacion() {
		return frecuenciaDePlanificacion;
	}

	public void setFrecuenciaDePlanificacion(int frecuenciaDePlanificacion) {
		this.frecuenciaDePlanificacion = frecuenciaDePlanificacion;
	}
}
