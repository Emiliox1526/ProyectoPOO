package logico;

public class Diseñador extends Trabajador {

	private int cantAgnoExp;

	public Diseñador(String cedula, String nombre, String apellidos, String direccionParticular, String sexo, int edad,
			String evaluacionAnual, int cantAgnoExp) {
		super(cedula, nombre, apellidos, direccionParticular, sexo, edad, evaluacionAnual);
		this.cantAgnoExp = cantAgnoExp;
	}

	public int getCantAgnoExp() {
		return cantAgnoExp;
	}

	public void setCantAgnoExp(int cantAgnoExp) {
		this.cantAgnoExp = cantAgnoExp;
	}

}
