package logico;

public class Programador extends Trabajador {
	private String[] lenguajeDeProgramacion = new String[3];

	public Programador(String cedula, String nombre, String apellidos, String direccionParticular, String sexo,
			int edad, String evaluacionAnual, String[] lenguajeDeProgramacion) {
		super(cedula, nombre, apellidos, direccionParticular, sexo, edad, evaluacionAnual);
		this.lenguajeDeProgramacion = lenguajeDeProgramacion;
	}

	public String[] getLenguajeDeProgramacion() {
		return lenguajeDeProgramacion;
	}

	public void setLenguajeDeProgramacion(String[] lenguajeDeProgramacion) {
		this.lenguajeDeProgramacion = lenguajeDeProgramacion;
	}
}
