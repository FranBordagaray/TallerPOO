package Modelo.Complementos;

/**
 * Clase que representa un comprobante de pago, el cual contiene información de la reserva, 
 * la tarjeta de pago, fecha, hora e importe de la transacción.
 */
public class Comprobante {
    
    /** Identificador único del comprobante. */
    private int idComprobante;
    
    /** Fecha en la que se emitió el comprobante. */
    private String fecha;
    
    /** Hora en la que se emitió el comprobante. */
    private String hora;
    
    /** Importe total de la transacción. */
    private float importe;
    
    /** Identificador de la reserva asociada al comprobante. */
    private int idReserva;
    
    /** Identificador de la tarjeta utilizada para la transacción. */
    private int idTarjeta;
    
    /**
     * Constructor vacío para la clase Comprobante.
     */
    public Comprobante() {}

    /**
     * Obtiene el identificador único del comprobante.
     * 
     * @return Identificador del comprobante.
     */
    public int getIdComprobante() {
        return idComprobante;
    }

    /**
     * Establece el identificador único del comprobante.
     * 
     * @param idComprobante Identificador del comprobante.
     */
    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    /**
     * Obtiene la fecha en la que se emitió el comprobante.
     * 
     * @return Fecha de emisión del comprobante.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de emisión del comprobante.
     * 
     * @param fecha Fecha de emisión del comprobante.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la hora en la que se emitió el comprobante.
     * 
     * @return Hora de emisión del comprobante.
     */
    public String getHora() {
        return hora;
    }

    /**
     * Establece la hora de emisión del comprobante.
     * 
     * @param hora Hora de emisión del comprobante.
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Obtiene el importe total de la transacción.
     * 
     * @return Importe de la transacción.
     */
    public float getImporte() {
        return importe;
    }

    /**
     * Establece el importe total de la transacción.
     * 
     * @param importe Importe de la transacción.
     */
    public void setImporte(float importe) {
        this.importe = importe;
    }

    /**
     * Obtiene el identificador de la reserva asociada al comprobante.
     * 
     * @return Identificador de la reserva.
     */
    public int getIdReserva() {
        return idReserva;
    }

    /**
     * Establece el identificador de la reserva asociada al comprobante.
     * 
     * @param idReserva Identificador de la reserva.
     */
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * Obtiene el identificador de la tarjeta utilizada para la transacción.
     * 
     * @return Identificador de la tarjeta.
     */
    public int getIdTarjeta() {
        return idTarjeta;
    }

    /**
     * Establece el identificador de la tarjeta utilizada para la transacción.
     * 
     * @param idTarjeta Identificador de la tarjeta.
     */
    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }
}