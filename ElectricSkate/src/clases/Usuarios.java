package clases;

public abstract class Usuarios {
	
	//Atributos
	
	private String DNI;
	private String Nombre;
	private String Apellido;
	private int Edad;
	private String Email;
	
	//Constructor
	
	public Usuarios(String dNI, String nombre, String apellido, int edad, String email) {
		super();
		DNI = dNI;
		Nombre = nombre;
		Apellido = apellido;
		Edad = edad;
		Email = email;
	}

	//Getters & Setters
	
	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public int getEdad() {
		return Edad;
	}

	public void setEdad(int edad) {
		Edad = edad;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

}
