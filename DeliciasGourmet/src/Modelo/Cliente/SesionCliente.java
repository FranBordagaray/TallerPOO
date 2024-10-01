package Modelo.Cliente;

public class SesionCliente {
	
	//Variable de clase
	private static Cliente clienteActual;

	// Set sesion actual de cliente
	public static void setClienteActual(Cliente cliente) {
		clienteActual = cliente;
	}

	// Get sesion actual de cliente
	public static Cliente getClienteActual() {
		return clienteActual;
	}
	
	// Metodo para cerrar todas las sesiones
	public static void cerrarSesion() {
		clienteActual = null;
	}
}