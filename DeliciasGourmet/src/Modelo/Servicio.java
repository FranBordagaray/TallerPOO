package Modelo;

public class Servicio {

    private int idServicio;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private int eventoPrivado;

    // Constructor vacío
    public Servicio() {}

    // Getter de idServico
    public int getIdServicio() {
        return idServicio;
    }
    
    // Setter de  idServicio
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }
    
    // Getter de Fecha
    public String getFecha() {
        return fecha;
    }
    
    // Setter de Fecha
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    // Getter de HoraInicio
    public String getHoraInicio() {
        return horaInicio;
    }
    
 	// Setter de  HoraInicio
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
    
    // Getter de HoraFin
    public String getHoraFin() {
        return horaFin;
    }
    
 	// Setter de  HoraFin
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
    
    // Getter de Evento Privado
    public int getEventoPrivado() {
        return eventoPrivado;
    }
    
 	// Setter de Evento Privado
    public void setEventoPrivado(int eventoPrivado) {
        this.eventoPrivado = eventoPrivado;
    }
    
}