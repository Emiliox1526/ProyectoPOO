package logico;

import java.sql.Date;

public class Jefe extends Trabajador {
	private int cantTrabajadores;

	public Jefe(String cedula, String nombre, String apellidos, String direccionParticular, String sexo,
			java.util.Date date, String evaluacionAnual, int cantTrabajadores) {
		super(cedula, nombre, apellidos, direccionParticular, sexo, date, evaluacionAnual);
		this.cantTrabajadores = cantTrabajadores;
	}

	public int getCantTrabajadores() {
		return cantTrabajadores;
	}

	public void setCantTrabajadores(int cantTrabajadores) {
		this.cantTrabajadores = cantTrabajadores;
	}
	

}
