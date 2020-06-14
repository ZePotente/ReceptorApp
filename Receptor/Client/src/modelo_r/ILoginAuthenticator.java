package modelo_r;

import configuracion.NoLecturaConfiguracionException;

public interface ILoginAuthenticator {
    public void ingresar(Usuario usuario);
    public void notificarCambioDeEstado();
}
