
public class Triage {
    
        private String fecha; 
    private String colorSugerido;
    private String colorFinal;
    private String motivoCambio;
    private String hora;

    public Triage(String fecha, String colorSugerido, String colorFinal, String motivoCambio, String hora) {
        this.fecha = fecha;
        this.colorSugerido = colorSugerido;
        this.colorFinal = colorFinal;
        this.motivoCambio = motivoCambio;
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public String getColorSugerido() {
        return colorSugerido;
    }

    public String getColorFinal() {
        return colorFinal;
    }

    public String getMotivoCambio() {
        return motivoCambio;
    }

    public String getHora() {
        return hora;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setColorSugerido(String colorSugerido) {
        this.colorSugerido = colorSugerido;
    }

    public void setColorFinal(String colorFinal) {
        this.colorFinal = colorFinal;
    }

    public void setMotivoCambio(String motivoCambio) {
        this.motivoCambio = motivoCambio;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    

           
}
