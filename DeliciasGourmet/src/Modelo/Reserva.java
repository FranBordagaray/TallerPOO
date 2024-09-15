package Modelo;

public class Reserva {

	// Variables de clase
	private int idReserva;
	private String fecha;
	private String hora;
	private String comentario;
	private String dispocionMesa;
	private int idCliente;
	private int idMesa;


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
	public String getDispocionMesa() {
		return dispocionMesa;
	}

	// Setter de DisposicionMesa
	public void setDispocionMesa(String dispocionMesa) {
		this.dispocionMesa = dispocionMesa;
	}
	
	// Getter de idCliente
	public int getIdCliente() {
		return idCliente;
	}
	
	// Setter de DisposicionMesa
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	// Getter de idMesa
	public int getIdMesa() {
		return idMesa;
	}
	
	// Setter de DisposicionMesa
	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}
	
}
