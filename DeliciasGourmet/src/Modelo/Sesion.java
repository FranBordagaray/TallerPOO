package Modelo;

public class Sesion {
	private static Cliente clienteActual;
	
	public static void setClienteActual(Cliente cliente) {
		clienteActual = cliente;
	}

	public static Cliente getClienteActual() {
		return clienteActual;
	}

	public static void cerrarSesion() {
		clienteActual = null;
	}
}
