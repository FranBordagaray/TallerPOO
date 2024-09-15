package Modelo;

public class Mesa {
	
	// Variables de clase
    private int idMesa;
	private int capacidad;
    private String ubicacion;
    private String estado;

    // Constructor por defecto
    public Mesa() {}
    

    // Getters y Id Mesa
    public int getIdMesa() {
        return idMesa;
    }

    // Setter de Id Mesa
    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    // Getter de Capacidad
    public int getCapacidad() {
        return capacidad;
    }
    
    // Setter de Capacidad
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    // Getter de Ubicacion
    public String getUbicacion() {
        return ubicacion;
    }
    
    // Setter de Ubicacion
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    // Getter de Estado
    public String getEstado() {
        return estado;
    }
    
    // Setter de Estado
    public void setEstado(String estado) {
        this.estado = estado;
    }


}