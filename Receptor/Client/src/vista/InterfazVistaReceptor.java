package vista;

import modelo_r.mensaje.Mensaje;


public interface InterfazVistaReceptor extends InterfazVista {
    static final String APAGARALARMA = "ApagarAlarma";
    
    public void agregarMensaje(Mensaje mensaje);
}
