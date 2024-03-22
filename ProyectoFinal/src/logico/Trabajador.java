package logico;

public class Trabajador {
	
	protected String cedula;
	protected String nombre;
	protected String apellidos;
	protected String direccionParticular;
	protected String sexo;
	protected int edad;
	protected String evaluacionAnual;
	public Trabajador(String cedula, String nombre, String apellidos, String direccionParticular, String sexo, int edad,
			String evaluacionAnual) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccionParticular = direccionParticular;
		this.sexo = sexo;
		this.edad = edad;
		this.evaluacionAnual = evaluacionAnual;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccionParticular() {
		return direccionParticular;
	}
	public void setDireccionParticular(String direccionParticular) {
		this.direccionParticular = direccionParticular;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getEvaluacionAnual() {
		return evaluacionAnual;
	}
	public void setEvaluacionAnual(String evaluacionAnual) {
		this.evaluacionAnual = evaluacionAnual;
	}

}
