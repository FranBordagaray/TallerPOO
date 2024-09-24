package Modelo;

public class Reserva {

	// Variables de clase
	private int idReserva;
	private int idCliente;
	private String fecha;
	private String hora;
	private int idMesa;
	private String comentario;
	private String dispocicionMesa;
	private int estado;
	private int idServicio;

	// Constructor de clase
	public Reserva() {
	}

	// Getter de idReserva
	public int getIdReserva() {
		return idReserva;
	}

	// Setter de idReserva
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	// Getter de Fecha
	public String getFecha() {
		return fecha;
	}

	// Setter de Fecha
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	// Getter de Hora
	public String getHora() {
		return hora;
	}

	// Setter de Hora
	public void setHora(String hora) {
		this.hora = hora;
	}

	// Getter de Comentario
	public String getComentario() {
		return comentario;
	}

	// Setter de Comentario
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	// Getter de DispocionMesa
	public String getDispocicionMesa() {
		return dispocicionMesa;
	}

	// Setter de DisposicionMesa
	public void setDispocicionMesa(String dispocicionMesa) {
		this.dispocicionMesa = dispocicionMesa;
	}
	
	// Getter de idCliente
	public int getIdCliente() {
		return idCliente;
	}
	
	// Setter de idCliente
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	// Getter de idMesa
	public int getIdMesa() {
		return idMesa;
	}
	
	// Setter de idMesa
	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}
	
	// Getter de Estado
		public int getEstado() {
			return estado;
		}
		
		// Setter de estap
		public void setEstado(int estado) {
			this.estado = estado;
		}
	
	// Getter de idServicio
    public int getIdServicio() {
    	return this.idServicio;
    }
    
    // Setter de idServicio
    public void setIdServicio(int idServicio) {
    	this.idServicio = idServicio;
    }
	
}
