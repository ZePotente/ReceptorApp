package excepciones;

public class NoConexionException extends Exception {
    public NoConexionException(String string, Throwable throwable, boolean b, boolean b1) {
        super(string, throwable, b, b1);
    }

    public NoConexionException(Throwable throwable) {
        super(throwable);
    }

    public NoConexionException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public NoConexionException(String string) {
        super(string);
    }

    public NoConexionException() {
        super();
    }
}
