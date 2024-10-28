package Modelo.Cliente;

/**
 * Clase que representa una tarjeta de crédito o débito en el sistema.
 * Contiene información sobre el titular, emisor, número de tarjeta y código de verificación.
 */
public class Tarjeta {
    
    /** Identificador único de la tarjeta. */
    private int idTarjeta;
    
    /** Nombre del titular de la tarjeta. */
    private String titular;
    
    /** Entidad emisora de la tarjeta. */
    private String emisor;
    
    /** Número de la tarjeta. */
    private String nroTarjeta;
    
    /** Código de verificación de la tarjeta. */
    private int codVerificacion;

    /**
     * Constructor sin parámetros para la clase Tarjeta.
     */
    public Tarjeta() {
    }

    /**
     * Obtiene el identificador de la tarjeta.
     * 
     * @return Identificador único de la tarjeta.
     */
    public int getIdTarjeta() {
        return idTarjeta;
    }

    /**
     * Establece el identificador de la tarjeta.
     * 
     * @param idTarjeta Identificador único de la tarjeta.
     */
    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    /**
     * Obtiene el nombre del titular de la tarjeta.
     * 
     * @return Nombre del titular de la tarjeta.
     */
    public String getTitular() {
        return titular;
    }

    /**
     * Establece el nombre del titular de la tarjeta.
     * 
     * @param titular Nombre del titular de la tarjeta.
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     * Obtiene el emisor de la tarjeta.
     * 
     * @return Emisor de la tarjeta.
     */
    public String getEmisor() {
        return emisor;
    }

    /**
     * Establece el emisor de la tarjeta.
     * 
     * @param emisor Emisor de la tarjeta.
     */
    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    /**
     * Obtiene el número de la tarjeta.
     * 
     * @return Número de la tarjeta.
     */
    public String getNroTarjeta() {
        return nroTarjeta;
    }

    /**
     * Establece el número de la tarjeta.
     * 
     * @param nroTarjeta Número de la tarjeta.
     */
    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    /**
     * Obtiene el código de verificación de la tarjeta.
     * 
     * @return Código de verificación de la tarjeta.
     */
    public int getCodVerificacion() {
        return codVerificacion;
    }

    /**
     * Establece el código de verificación de la tarjeta.
     * 
     * @param codVerificacion Código de verificación de la tarjeta.
     */
    public void setCodVerificacion(int codVerificacion) {
        this.codVerificacion = codVerificacion;
    }
}