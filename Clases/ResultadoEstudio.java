
package Clases;

import java.util.Date;

class ResultadoEstudio {
   
    private Date fecha;
    private String hora;
    private String tipoEstudio;
    private String informeEstudio;

    public ResultadoEstudio(Date fecha, String hora, String tipoEstudio) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoEstudio = tipoEstudio;
    }
     
    public Date getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getTipoEstudio() {
        return tipoEstudio;
    }

    public String getInformeEstudio() {
        return informeEstudio;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setTipoEstudio(String tipoEstudio) {
        this.tipoEstudio = tipoEstudio;
    }

    public void setInformeEstudio(String informeEstudio) {
        this.informeEstudio = informeEstudio;
    }
    
}
