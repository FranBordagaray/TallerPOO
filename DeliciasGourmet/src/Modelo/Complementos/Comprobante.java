package Modelo.Complementos;

public class Comprobante {
	
	private int idComprobante;
	private String fecha;
	private String hora;
	private float importe;
	private int idReserva;
	private int idTarjeta;
	
	// Constructor
	public Comprobante() {}
	
	// Get de idComprobante
	public int getIdComprobante() {
		return idComprobante;
	}
	
	// Setter de idComprobante
	public void setIdComprobante(int idComprobante) {
		this.idComprobante = idComprobante;
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
	
	// Getter de Importe
	public float getImporte() {
		return importe;
	}
	
	// Setter de Importe
	public void setImporte(float importe) {
		this.importe = importe;
	}
	
	// Getter de idReserva
	public int getIdReserva() {
		return idReserva;
	}
	
	// Setter de idReserva
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	
	// Get de idTarjeta
	public int getIdTarjeta() {
		return idTarjeta;
	}
	
	// Setter de idTarjeta
	public void setIdTarjeta(int idTarjeta) {
		this.idTarjeta = idTarjeta;
	}
}
