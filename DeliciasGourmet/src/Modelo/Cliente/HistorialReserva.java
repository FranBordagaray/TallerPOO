package Modelo.Cliente;

public class HistorialReserva {
	private int idReserva;
	private String nombre;
	private String apellido;
    private String fecha;
    private String hora;
    private int idMesa;
    private int capacidad;
    private String ubicacion;
    private String comentario;
    private int estado;

	// Constructor
    public HistorialReserva(int idReserva, String fecha, String hora, int idMesa, int capacidad, String ubicacion, String comentario, int estado) {
        this.idReserva = idReserva;
    	this.fecha = fecha;
        this.hora = hora;
        this.idMesa = idMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.comentario = comentario;
        this.estado = estado;
    }
    
    public HistorialReserva(int idReserva, String fecha, String hora, int idMesa, int capacidad, String ubicacion, String comentario) {
        this.idReserva = idReserva;
    	this.fecha = fecha;
        this.hora = hora;
        this.idMesa = idMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.comentario = comentario;
    }
    
    public HistorialReserva(int idReserva,String nombre, String apellido, String fecha, String hora, int idMesa, int capacidad, String ubicacion, int estado) {
        this.idReserva = idReserva;
        this.nombre = nombre;
        this.apellido = apellido;
    	this.fecha = fecha;
        this.hora = hora;
        this.idMesa = idMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }
    public HistorialReserva(String ubicacion, String fecha, String hora, int capacidad, int idMesa, String comentario) {
    	this.ubicacion = ubicacion;
    	this.fecha = fecha;
    	this.hora = hora;
    	this.capacidad = capacidad;
    	this.idMesa = idMesa;
    	this.comentario = comentario;
    }

    // Get de idReserva
    public int getIdReserva() {
    	return idReserva;
    }
    
    //Get de Nombre
    public String getNombre(){
    	return this.nombre;
    }
    
    //Get de Apellido
    public String getApellido() {
    	return this.apellido;
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