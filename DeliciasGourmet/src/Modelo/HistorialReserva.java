package Modelo;

public class HistorialReserva {
    private String fecha;
    private String hora;
    private int idMesa;
    private int capacidad;
    private String ubicacion;
    private String comentario;

    // Constructor
    public HistorialReserva(String fecha, String hora, int idMesa, int capacidad, String ubicacion, String comentario) {
        this.fecha = fecha;
        this.hora = hora;
        this.idMesa = idMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.comentario = comentario;
    }

    // Getters
    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getComentario() {
        return comentario;
    }
}
