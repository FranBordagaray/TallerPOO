package Modelo.Cliente;

/**
 * Clase que gestiona la sesión actual de un cliente en el sistema.
 * Permite establecer, obtener y cerrar la sesión de un cliente.
 */
public class SesionCliente {
    
    /** 
     * Variable de clase que almacena el cliente actual en sesión.
     */
    private static Cliente clienteActual;

    /**
     * Establece el cliente actual en sesión.
     * 
     * @param cliente Cliente a establecer como cliente actual en sesión.
     */
    public static void setClienteActual(Cliente cliente) {
        clienteActual = cliente;
    }

    /**
     * Obtiene el cliente actual en sesión.
     * 
     * @return Cliente actual en sesión, o null si no hay un cliente en sesión.
     */
    public static Cliente getClienteActual() {
        return clienteActual;
    }

    /**
     * Cierra la sesión del cliente actual, eliminando el cliente almacenado.
     */
    public static void cerrarSesion() {
        clienteActual = null;
    }
}