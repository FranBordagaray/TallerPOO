package Modelo.Complementos;

/**
 * Representa una mesa en el sistema de reservas de un restaurante.
 * Cada mesa tiene un identificador único, capacidad de asientos, ubicación,
 * estado actual, y está asociada con un servicio específico.
 */
public class Mesa {

    // Variables de clase
    private int idMesa;
    private int capacidad;
    private String ubicacion;
    private EnumEstado estado;
    private int idServicio;

    /**
     * Constructor por defecto para crear una instancia de Mesa sin valores iniciales.
     */
    public Mesa() {}

    /**
     * Obtiene el identificador único de la mesa.
     * 
     * @return id de la mesa.
     */
    public int getIdMesa() {
        return idMesa;
    }

    /**
     * Asigna un identificador único a la mesa.
     * 
     * @param idMesa El id de la mesa.
     */
    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    /**
     * Obtiene la capacidad de la mesa en términos de número de asientos.
     * 
     * @return Capacidad de la mesa.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Asigna la capacidad de la mesa.
     * 
     * @param capacidad Número de asientos disponibles en la mesa.
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Obtiene la ubicación de la mesa dentro del restaurante.
     * 
     * @return Ubicación de la mesa.
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Asigna la ubicación de la mesa.
     * 
     * @param ubicacion Ubicación en la que se encuentra la mesa.
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene el estado actual de la mesa.
     * 
     * @return Estado de la mesa (LIBRE, OCUPADA o BLOQUEADA).
     */
    public EnumEstado getEstado() {
        return estado;
    }

    /**
     * Asigna el estado actual de la mesa.
     * 
     * @param estado Estado de la mesa (LIBRE, OCUPADA o BLOQUEADA).
     */
    public void setEstado(EnumEstado estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el identificador del servicio asociado a la mesa.
     * 
     * @return ID del servicio asignado a la mesa.
     */
    public int getIdServicio() {
        return this.idServicio;
    }

    /**
     * Asigna el identificador de servicio a la mesa.
     * 
     * @param idServicio ID del servicio que se asigna a la mesa.
     */
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }
}