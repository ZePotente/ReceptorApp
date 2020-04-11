package modelo;

public class Usuario implements ArmableDesarmable {
    protected String nombre;
    protected String numeroDeIP;

    public Usuario(String nombre, String numeroDeIP) {
        this.nombre = nombre;
        this.numeroDeIP = numeroDeIP;
    }
    
    public String getNumeroDeIP() {
        return numeroDeIP;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public String toString() {
        return nombre + ArmableDesarmable.SEPARADOR + 
               numeroDeIP;
    }
}
