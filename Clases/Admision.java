

package Clases;
import java.util.Date;

public class Admision {
    
    private Date fecRegistro;
    private String infoPaciente;
    private String motivoConsulta;
    private String horaRegistro;
    
    public Admision(Date fecRegistro, String infoPaciente, String motivoConsulta, String horaRegistro) {
        this.fecRegistro = fecRegistro;
        this.infoPaciente = infoPaciente;
        this.motivoConsulta = motivoConsulta;
        this.horaRegistro = horaRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public void setInfoPaciente(String infoPaciente) {
        this.infoPaciente = infoPaciente;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public void setHoraRegistro(String horaRegistro) {
        this.horaRegistro = horaRegistro;
    }
    
    public Date getFecRegistro() {
        return fecRegistro;
    }

    public String getInfoPaciente() {
        return infoPaciente;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public String getHoraRegistro() {
        return horaRegistro;
    }
}


