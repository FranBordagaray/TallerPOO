package Modelo;

public class HistorialReserva {
    private String fecha;
    private String hora;
    private int idMesa;
    private int capacidad;
    private String ubicacion;
    private String comentario;
    private int estado;

	// Constructor
    public HistorialReserva(String fecha, String hora, int idMesa, int capacidad, String ubicacion, String comentario, int estado) {
        this.fecha = fecha;
        this.hora = hora;
        this.idMesa = idMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.comentario = comentario;
        this.estado = estado;
    }

    // Get de fecha
    public String getFecha() {
        return fecha;
    }

    // Get de hora
    public String getHora() {
        return hora;
    }

    // Get de identificador de mesa
    public int getIdMesa() {
        return idMesa;
    }

    // Get de capacidad de mesa 
    public int getCapacidad() {
        return capacidad;
    }

    // Get de ubicacion de mesa
    public String getUbicacion() {
        return ubicacion;
    }

    // Get de comentario de reserva
    public String getComentario() {
        return comentario;
    }
    
    // Get de estado de reserva
    public int getEstado() {
		return estado;
	}
}