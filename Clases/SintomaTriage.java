
public class SintomaTriage {
    
    private int respiracion; 
    private int pulso; 
    private int estaodMetal; 
    private int conciencia; 
    private int dolorAbdominal; 
    private int signosDeShock; 
    private int lesionesLeves; 
    private int sangrado;

    public SintomaTriage(int respiracion, int pulso, int estaodMetal, int conciencia, int dolorAbdominal, int signosDeShock, int lesionesLeves, int sangrado) {
        this.respiracion = respiracion;
        this.pulso = pulso;
        this.estaodMetal = estaodMetal;
        this.conciencia = conciencia;
        this.dolorAbdominal = dolorAbdominal;
        this.signosDeShock = signosDeShock;
        this.lesionesLeves = lesionesLeves;
        this.sangrado = sangrado;
    }

    public int getRespiracion() {
        return respiracion;
    }

    public int getPulso() {
        return pulso;
    }

    public int getEstaodMetal() {
        return estaodMetal;
    }

    public int getConciencia() {
        return conciencia;
    }

    public int getDolorAbdominal() {
        return dolorAbdominal;
    }

    public int getSignosDeShock() {
        return signosDeShock;
    }

    public int getLesionesLeves() {
        return lesionesLeves;
    }

    public int getSangrado() {
        return sangrado;
    }

    public void setRespiracion(int respiracion) {
        this.respiracion = respiracion;
    }

    public void setPulso(int pulso) {
        this.pulso = pulso;
    }

    public void setEstaodMetal(int estaodMetal) {
        this.estaodMetal = estaodMetal;
    }

    public void setConciencia(int conciencia) {
        this.conciencia = conciencia;
    }

    public void setDolorAbdominal(int dolorAbdominal) {
        this.dolorAbdominal = dolorAbdominal;
    }

    public void setSignosDeShock(int signosDeShock) {
        this.signosDeShock = signosDeShock;
    }

    public void setLesionesLeves(int lesionesLeves) {
        this.lesionesLeves = lesionesLeves;
    }

    public void setSangrado(int sangrado) {
        this.sangrado = sangrado;
    }
    
}
