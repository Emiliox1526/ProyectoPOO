package logico;

import java.io.Serializable;
import java.sql.Date;

public class Diseñador extends Trabajador implements Serializable{
	private static final long serialVersionUID = 1L;
	private int cantAgnoExp;

	public Diseñador(String cedula, String nombre, String apellidos, String direccionParticular, String sexo, Date date,
			String evaluacionAnual, int cantProyectosFallidos, int cantProyectos, int cantAgnoExp) {
		super(cedula, nombre, apellidos, direccionParticular, sexo, date, evaluacionAnual, cantProyectosFallidos,
				cantProyectos);
		this.cantAgnoExp = cantAgnoExp;
	}

	public int getCantAgnoExp() {
		return cantAgnoExp;
	}

	public void setCantAgnoExp(int cantAgnoExp) {
		this.cantAgnoExp = cantAgnoExp;
	}

}
