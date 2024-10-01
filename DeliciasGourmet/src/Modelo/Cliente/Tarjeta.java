package Modelo.Cliente;

public class Tarjeta {
    private int idTarjeta;
    private String titular;
    private String emisor;
    private String nroTarjeta;
    private int codVerificacion;

    public Tarjeta() {
    }

    // Getter de idTaarjeta
    public int getIdTarjeta() {
        return idTarjeta;
    }

    // Setter de idTarjeta
    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    // Getter de titular
    public String getTitular() {
        return titular;
    }

    // Setter de titular
    public void setTitular(String titular) {
        this.titular = titular;
    }

    // Getter de emisor
    public String getEmisor() {
        return emisor;
    }

    // Setter de emisor
    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    // Getter de nroTarjeta
    public String getNroTarjeta() {
        return nroTarjeta;
    }

    // Setter de nroTarjeta
    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    // Getter de codVerificacion
    public int getCodVerificacion() {
        return codVerificacion;
    }

    // Setter de codVerificacion
    public void setCodVerificacion(int codVerificacion) {
        this.codVerificacion = codVerificacion;
    }
}