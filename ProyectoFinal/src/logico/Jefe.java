package logico;

public class Jefe extends Trabajador {
	private int cantTrabajadores;
	

	public Jefe(String cedula, String nombre, String apellidos, String direccionParticular, String sexo, int edad,
			String evaluacionAnual, int cantTrabajadores) {
		super(cedula, nombre, apellidos, direccionParticular, sexo, edad, evaluacionAnual);
		this.cantTrabajadores = cantTrabajadores;
	}

	public int getCantTrabajadores() {
		return cantTrabajadores;
	}

	public void setCantTrabajadores(int cantTrabajadores) {
		this.cantTrabajadores = cantTrabajadores;
	}
	

}
