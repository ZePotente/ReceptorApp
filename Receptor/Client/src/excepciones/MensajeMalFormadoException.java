package excepciones;

public class MensajeMalFormadoException extends Exception {
    private int largo, largoEsperado;
     
    public MensajeMalFormadoException(int largo, int largoEsperado) {
         super("La cantidad de atributos del mensaje no era la adecuada.");
         this.largo = largo;
         this.largoEsperado = largoEsperado;
    }

    public int getLargo() {
        return largo;
    }
    
    public int getLargoEsperado() {
        return largoEsperado;
    }
}
