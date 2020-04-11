package excepciones;

public class AlarmaNoActivadaException extends Exception {
    private boolean alarmaActivada;
    public AlarmaNoActivadaException(boolean alarmaActivada) {
        super("Se intento apagar la alarma estando ya apagada.");
        this.alarmaActivada = alarmaActivada;
    }
}
