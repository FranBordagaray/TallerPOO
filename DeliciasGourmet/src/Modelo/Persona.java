package Modelo;

public class Persona {
	// Variables de clase
	private String nombre;
	private String apellido;
	private String domicilio;
	private String telefono;
	private String email;
	private String usuario;
	private String contrasenia;

	// Constructor de clase
	public Persona() {
	}

	// Getter de nombre
	public String getNombre() {
		return nombre;
	}

	// Setter de nombre
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Getter de apellido
	public String getApellido() {
		return apellido;
	}

	// Setter de apellido
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	// Getter de domicilio
	public String getDomicilio() {
		return domicilio;
	}

	// Setter de domicilio
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	// Getter de telefono
	public String getTelefono() {
		return telefono;
	}

	// Setter de telefono
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	// Getter de email
	public String getEmail() {
		return email;
	}

	// Setter de email
	public void setEmail(String email) {
		this.email = email;
	}

	// Getter de usuario
	public String getUsuario() {
		return usuario;
	}

	// Setter de usuario
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	// Getter de contrasenia
	public String getContrasenia() {
		return contrasenia;
	}

	// Setter de contrasenia
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

}