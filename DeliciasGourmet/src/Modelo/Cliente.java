package Modelo;

public class Cliente extends Persona {
	// Variables de clase
	private int idCliente;
	private Tarjeta tarjeta;

	// Constructor de clase
	public Cliente() {
	}

	// Getter de idCliente
	public int getIdCliente() {
		return idCliente;
	}

	// Setter de idCliente
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	// Getter de tarjeta
	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	// Setter de tarjeta
	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	// ToString de cliente
	@Override
	public String toString() {
		return "Cliente{ID: " + getIdCliente() + ", NOMBRE: " + getNombre() + ", APELLIDO: " + getApellido()
				+ ", DOMICILIO: " + getDomicilio() + ", TELEFONO: " + getTelefono() + ", MAIL: " + getEmail()
				+ ", USUARIO: " + getUsuario() + ", CONTRASENIA: " + getContrasenia() + "}{" + getTarjeta() + "}";
	}
}