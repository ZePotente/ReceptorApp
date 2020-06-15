package controladores;

import excepciones.NoConexionException;

import modelo_r.mensaje.Mensaje;
import modelo_r.Sistema;

import vista.InterfazVistaReceptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ControladorVentana implements ActionListener, Observer {
    private InterfazVistaReceptor vista;
    private Sistema sistema;
    private ArrayList<Mensaje> mensajesRecibidos;

    public ControladorVentana(InterfazVistaReceptor vista) {
        this.vista = vista;
        this.sistema = Sistema.getInstancia();
        mensajesRecibidos = new ArrayList<Mensaje>();
        sistema.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getActionCommand().equals(InterfazVistaReceptor.APAGARALARMA)) {
            sistema.apagarAlarma();
        }
    }
    
    public ArrayList<Mensaje> getMensajesRecibidos() {
        return mensajesRecibidos;
    }
    
    public void notificarCambioDeEstado() {
        sistema.establecerConexion();
    }

    @Override
    public void update(Observable observable, Object object) {
        Mensaje mensaje = (Mensaje) object;
        mensajesRecibidos.add(mensaje);
        vista.agregarMensaje(mensaje);
    }
}
