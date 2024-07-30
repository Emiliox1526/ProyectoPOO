package logico;

import java.io.Serializable;
import java.sql.Date;

public class Jefe extends Trabajador implements Serializable{
	private static final long serialVersionUID = 1L;
	private int cantTrabajadores;


	public Jefe(String cedula, String nombre, String apellidos, String direccionParticular, String sexo, Date date,
			String evaluacionAnual, int cantProyectosFallidos, int cantProyectos, int cantTrabajadores) {
		super(cedula, nombre, apellidos, direccionParticular, sexo, date, evaluacionAnual, cantProyectosFallidos,
				cantProyectos);
		this.cantTrabajadores = cantTrabajadores;
	}

	public int getCantTrabajadores() {
		return cantTrabajadores;
	}

	public void setCantTrabajadores(int cantTrabajadores) {
		this.cantTrabajadores = cantTrabajadores;
	}
	

}
