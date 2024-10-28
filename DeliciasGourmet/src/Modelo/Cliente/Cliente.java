package Modelo.Cliente;

import java.util.Random;

import Modelo.Complementos.Persona;

/**
 * Clase que representa un cliente en el sistema. Extiende la clase {@link Persona}.
 * Cada cliente tiene un ID único, un código de recuperación, y una tarjeta asociada.
 */
public class Cliente extends Persona {

    /**
     * Identificador único del cliente.
     */
    private int idCliente;

    /**
     * Código de recuperación que permite al cliente recuperar su cuenta.
     */
    private String codRecuperacion;

    /**
     * Tarjeta asociada al cliente.
     */
    private Tarjeta tarjeta;

    /**
     * Constructor vacío de la clase Cliente.
     */
    public Cliente() {
    }

    /**
     * Obtiene el identificador único del cliente.
     * 
     * @return el ID del cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el identificador único del cliente.
     * 
     * @param idCliente el ID a establecer para el cliente.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtiene la tarjeta asociada al cliente.
     * 
     * @return la tarjeta del cliente.
     */
    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    /**
     * Establece la tarjeta asociada al cliente.
     * 
     * @param tarjeta la tarjeta a asociar con el cliente.
     */
    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    /**
     * Obtiene el código de recuperación del cliente.
     * 
     * @return el código de recuperación del cliente.
     */
    public String getCodRecuperacion() {
        return codRecuperacion;
    }

    /**
     * Genera un código de recuperación aleatorio de seis dígitos.
     * Este código puede ser utilizado para recuperar la cuenta del cliente.
     * 
     * @return un código de recuperación de seis dígitos como {@code String}.
     */
    public String generarCodigoRecuperacion() {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);
        return String.valueOf(codigo);
    }
}