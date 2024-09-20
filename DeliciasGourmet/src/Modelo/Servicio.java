package Modelo;

public class Servicio {

    private int idServicio;
    private int idMesa;
    private String fecha;
    private String hora;

    // Constructor vacío
    public Servicio() {}

    // Constructor con todos los campos
    public Servicio(int idServicio, int idMesa, String fecha, String hora) {
        this.idServicio = idServicio;
        this.idMesa = idMesa;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters y Setters
    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    // Método toString
    @Override
    public String toString() {
        return "Servicio{" +
                "idServicio=" + idServicio +
                ", idMesa=" + idMesa +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}