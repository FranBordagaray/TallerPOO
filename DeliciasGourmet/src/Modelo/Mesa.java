package Modelo;

public class Mesa {
    private int idMesa;
    private int capacidad;
    private String ubicacion;
    private String estado;
    private String horaReservada;

    // Constructor por defecto
    public Mesa() {}

    // Constructor con parámetros
    public Mesa(int idMesa, int capacidad, String ubicacion, String estado, String horaReservada) {
        this.idMesa = idMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.horaReservada = horaReservada;
    }

    // Getters y Setters
    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHoraReservada() {
        return horaReservada;
    }

    public void setHoraReservada(String horaReservada) {
        this.horaReservada = horaReservada;
    }

    // Método para mostrar la información de la mesa
    @Override
    public String toString() {
        return "Mesa [idMesa=" + idMesa + ", capacidad=" + capacidad + ", ubicacion=" + ubicacion + 
               ", estado=" + estado + ", horaReservada=" + horaReservada + "]";
    }
}