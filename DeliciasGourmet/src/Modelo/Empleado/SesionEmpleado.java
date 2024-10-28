package Modelo.Empleado;

/**
 * Clase que gestiona la sesión del empleado actual en el sistema.
 * Permite obtener y establecer el empleado que ha iniciado sesión,
 * así como cerrar la sesión actual.
 */
public class SesionEmpleado {
    // Variable de clase
    private static Empleado empleadoActual;

    /**
     * Obtiene el empleado que ha iniciado sesión actualmente.
     *
     * @return El empleado actual, o null si no hay ningún empleado conectado.
     */
    public static Empleado getEmpleadoActual() {
        return empleadoActual;
    }

    /**
     * Establece el empleado que ha iniciado sesión.
     *
     * @param empleadoActual El empleado que se establece como el empleado conectado.
     */
    public static void setEmpleadoActual(Empleado empleadoActual) {
        SesionEmpleado.empleadoActual = empleadoActual;
    }

    /**
     * Cierra la sesión del empleado actual, estableciendo la variable a null.
     */
    public static void cerrarSesion() {
        empleadoActual = null;
    }
}
