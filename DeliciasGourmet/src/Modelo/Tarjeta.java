package Modelo;

public class Tarjeta {
	private String titular;
	private String emisor;
	private String nroTarjeta;
	private int codVerificacion;

	public Tarjeta() {
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

	// Metodo para validar la tarjeta
	public static boolean ValidarTarjeta(String nroTarjeta) {
		int cantDigitos = nroTarjeta.length();
		int suma = 0;
		boolean alternar = false;

		for (int i = cantDigitos - 1; i >= 0; i--) {
			int nro = Integer.parseInt(nroTarjeta.substring(i, i + 1));

			if (alternar) {
				nro *= 2;
				if (nro > 9) {
					nro -= 9;
				}
			}
			suma += nro;
			alternar = !alternar;
		}
		return (suma % 10 == 0);
	}

	// ToString de tarjeta
	@Override
	public String toString() {
		return "Tarjeta[TITULAR: " + getTitular() + ", EMISOR: " + getEmisor() + ", NUMERO: "
				+ (nroTarjeta != null ? getNroTarjeta() : "No disponible") + ", CODIGO DE VERIFICACION: "
				+ getCodVerificacion() + "]";
	}
}