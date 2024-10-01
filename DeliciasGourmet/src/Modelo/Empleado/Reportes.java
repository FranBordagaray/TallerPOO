package Modelo.Empleado;

public class Reportes {
	
	//Variables de clase
	private int totalReservas;
	private int totalCapacidad;
	private String nombre;
    private String apellido;
    private String fecha;
    private String hora;
    private int capacidad;
    private String ubicacion;
    private String comentario;
    
    // Contructor personalizado para temporadas
    public Reportes(int totalReservas, int totalCapacidad) {
		this.totalReservas = totalReservas;
		this.totalCapacidad = totalCapacidad;
    }
    
    // Constructor de clase
	public Reportes(String nombre, String apellido, String fecha, String hora, int capacidad, String ubicacion, String comentario) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha = fecha;
		this.hora = hora;
		this.capacidad = capacidad;
		this.ubicacion = ubicacion;
		this.comentario = comentario;
	}
	
	// Get de totalReservas
	public int getTotalReservas() {
		return totalReservas;
	}
	
	// Get de totalCapacidad
	public int getTotalCapacidad() {
		return totalCapacidad;
	}
	
	// Get de nombre
	public String getNombre() {
		return nombre;
	}
	
	// Get de apellido
	public String getApellido() {
		return apellido;
	}
	
	// Get de fecha
	public String getFecha() {
		return fecha;
	}
	
	// Get de hora
	public String getHora() {
		return hora;
	}
	
	// Get de capacidad
	public int getCapacidad() {
		return capacidad;
	}
	
	// Get de ubicacion
	public String getUbicacion() {
		return ubicacion;
	}
	
	// Get de comentario 
	public String getComentario() {
		return comentario;
	}
}