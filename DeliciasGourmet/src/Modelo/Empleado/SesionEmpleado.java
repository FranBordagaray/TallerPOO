package Modelo.Empleado;

public class SesionEmpleado {
	//Variable de clase
	private static Empleado empleadoActual;
	
	// Get sesion actual de
	public static Empleado getEmpleadoActual() {
		return empleadoActual;
	}

	// Set sesion actual de cliente
	public static void setEmpleadoActual(Empleado empleadoActual) {
		SesionEmpleado.empleadoActual = empleadoActual;
	}
	
	// Metodo para cerrar todas las sesiones
	public static void cerrarSesion() {
		empleadoActual = null;
	}
}