package modelo_r;

import excepciones.NoLecturaConfiguracionException;

public interface ILoginAuthenticator {
    public void leerConfig() throws NoLecturaConfiguracionException;
    public void ingresar(Usuario usuario);
    public void notificarCambioDeEstado(boolean estado);
}