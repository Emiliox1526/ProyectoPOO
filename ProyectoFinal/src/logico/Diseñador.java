package logico;

import java.sql.Date;

public class Dise�ador extends Trabajador {

	private int cantAgnoExp;

	
	public Dise�ador(String cedula, String nombre, String apellidos, String direccionParticular, String sexo,
			java.util.Date fecha, String evaluacionAnual, int cantAgnoExp) {
		super(cedula, nombre, apellidos, direccionParticular, sexo, fecha, evaluacionAnual);
		this.cantAgnoExp = cantAgnoExp;
	}

	public int getCantAgnoExp() {
		return cantAgnoExp;
	}

	public void setCantAgnoExp(int cantAgnoExp) {
		this.cantAgnoExp = cantAgnoExp;
	}

}
