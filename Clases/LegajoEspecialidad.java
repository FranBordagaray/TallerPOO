
package Clases;

import java.util.Date;

public class LegajoEspecialidad {
    
    private Date fechaTitulo;
    private String universidades;
    
    public LegajoEspecialidad(Date fechaTitulo, String universidades) {
        this.fechaTitulo = fechaTitulo;
        this.universidades = universidades;
    }

    public Date getFechaTitulo() {
        return fechaTitulo;
    }

    public String getUniversidades() {
        return universidades;
    }
    
    public void setFechaTitulo(Date fechaTitulo) {
        this.fechaTitulo = fechaTitulo;
    }

    public void setUniversidades(String universidades) {
        this.universidades = universidades;
    }
}
