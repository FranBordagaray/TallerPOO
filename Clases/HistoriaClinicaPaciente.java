
package Clases;

import java.util.Date;

public class HistoriaClinicaPaciente  {
    
    private Date fecha;
    private String historialDiagnostico;
    private Enum lugardeAtencion;
    private String ultimoDiagnostico;
    private ResultadoEstudio resEstudios;
    private String hora;

    public HistoriaClinicaPaciente(Date fecha, String historialDiagnostico, Enum lugardeAtencion, String ultimoDiagnostico, ResultadoEstudio resEstudios, String hora) {
        this.fecha = fecha;
        this.historialDiagnostico = historialDiagnostico;
        this.lugardeAtencion = lugardeAtencion;
        this.ultimoDiagnostico = ultimoDiagnostico;
        this.resEstudios = resEstudios;
        this.hora = hora;
    }
    public Date getFecha() {
        return fecha;
    }

    public String getHistorialDiagnostico() {
        return historialDiagnostico;
    }

    public Enum getLugardeAtencion() {
        return lugardeAtencion;
    }

    public String getUltimoDiagnostico() {
        return ultimoDiagnostico;
    }

    public ResultadoEstudio getResEstudios() {
        return resEstudios;
    }

    public String getHora() {
        return hora;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHistorialDiagnostico(String historialDiagnostico) {
        this.historialDiagnostico = historialDiagnostico;
    }

    public void setLugardeAtencion(Enum lugardeAtencion) {
        this.lugardeAtencion = lugardeAtencion;
    }

    public void setUltimoDiagnostico(String ultimoDiagnostico) {
        this.ultimoDiagnostico = ultimoDiagnostico;
    }

    public void setResEstudios(ResultadoEstudio resEstudios) {
        this.resEstudios = resEstudios;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
