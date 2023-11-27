
package Clases;
import java.util.Date;

public class Asignado {
    
    private Date fecha;
    private String hora;
    
    public Asignado(Date fecha, String hora) {
        this.fecha = fecha;
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
}
