package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.InetAddress;

import java.net.UnknownHostException;

import java.util.ArrayList;

import modelo.Sistema;

import modelo.Usuario;

import modelo.mensaje.Mensaje;

import ventanas.Ventana;

import vista.InterfazVistaIngresaNombre;
import vista.InterfazVistaReceptor;

public class ControladorVentanaIngresaNombre implements ActionListener {
    private InterfazVistaIngresaNombre vista;
    private Sistema sistema;

    public ControladorVentanaIngresaNombre(InterfazVistaIngresaNombre vista) {
        this.vista = vista;
        this.sistema = Sistema.getInstancia();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals(InterfazVistaIngresaNombre.CONTINUAR)) {
            Ventana ventana = new Ventana("Mensajes");
            ControladorVentana controlador = new ControladorVentana(ventana);
            ventana.setControlador(controlador);
            try {
                String nroIP = InetAddress.getLocalHost().getHostAddress();
                sistema.setUsuario(new Usuario(vista.getNombre(), nroIP));
                sistema.notificarCambioDeEstado(true);
                ventana.abrir();
                vista.cerrar();
            } catch (UnknownHostException e) {
                System.out.println("Error al obtener el numero de IP");
            }
        }
    }
}
