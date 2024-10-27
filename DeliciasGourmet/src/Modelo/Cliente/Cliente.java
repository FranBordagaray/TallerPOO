package Modelo.Cliente;

import java.util.Random;

import Modelo.Complementos.Persona;

public class Cliente extends Persona {
	// Variables de clase
	private int idCliente;
	private String codRecuperacion;
	private Tarjeta tarjeta;

	// Constructor de clase
	public Cliente() {
	}

	// Getter de idCliente
	public int getIdCliente() {
		return idCliente;
	}

	// Setter de idCliente
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	// Getter de tarjeta
	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	// Setter de tarjeta
	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	// Getter codRecuperacion
	public String getCodRecuperacion() {
		return codRecuperacion;
	}

	// Genera un codigo aleatorio para recuperar su clave
	public String generarCodigoRecuperacion() {
	    Random random = new Random();
	    int codigo = 100000 + random.nextInt(900000);
	    return String.valueOf(codigo);
	}

	
}