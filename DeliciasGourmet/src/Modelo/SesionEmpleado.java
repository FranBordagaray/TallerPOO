package Modelo;

public class SesionEmpleado {
	private static Empleado empleadoActual;

	public static Empleado getEmpleadoActual() {
		return empleadoActual;
	}

	public static void setEmpleadoActual(Empleado empleadoActual) {
		SesionEmpleado.empleadoActual = empleadoActual;
	}
	
	public static void cerrarSesion() {
		empleadoActual = null;
	}
}
