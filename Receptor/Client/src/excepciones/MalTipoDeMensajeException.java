package excepciones;

public class MalTipoDeMensajeException extends Exception {
    private String tipo;
    public MalTipoDeMensajeException(String tipo) {
        super("El tipo de Mensaje no era el correcto.");
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
