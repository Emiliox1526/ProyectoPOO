package logico;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Programador extends Trabajador implements Serializable{
	private static final long serialVersionUID = 1L;
	
	ArrayList<String> lenguajeDeProgramacion = new ArrayList<>();

	public Programador(String cedula, String nombre, String apellidos, String direccionParticular, String sexo,
			Date date, String evaluacionAnual, int cantProyectosFallidos, int cantProyectos,
			ArrayList<String> lenguajeDeProgramacion) {
		super(cedula, nombre, apellidos, direccionParticular, sexo, date, evaluacionAnual, cantProyectosFallidos,
				cantProyectos);
		this.lenguajeDeProgramacion = lenguajeDeProgramacion;
	}

	public ArrayList<String> getLenguajeDeProgramacion() {
		return lenguajeDeProgramacion;
	}

	public void setLenguajeDeProgramacion(ArrayList<String> lenguajeDeProgramacion) {
		this.lenguajeDeProgramacion = lenguajeDeProgramacion;
	}
}
