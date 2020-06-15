package controladores;

import excepciones.NoConexionException;

import configuracion.NoLecturaConfiguracionException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.InetAddress;

import java.net.UnknownHostException;

import java.util.ArrayList;

import modelo_r.ILoginAuthenticator;
import modelo_r.Sistema;

import modelo_r.Usuario;

import modelo_r.mensaje.Mensaje;

import ventanas.Ventana;

import vista.InterfazVistaIngresaNombre;
import vista.InterfazVistaReceptor;

public class ControladorVentanaIngresaNombre implements ActionListener {
    private InterfazVistaIngresaNombre vista;
    private ILoginAuthenticator sistema;

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
                String nombre = vista.getNombre();
                if (nombre != null && !nombre.isEmpty()) {
                    try {
                        sistema.leerConfig();
                    String nroIP = InetAddress.getLocalHost().getHostAddress();
                    sistema.ingresar(new Usuario(vista.getNombre(), nroIP));
                    sistema.establecerConexion();
                    ventana.abrir();
                    vista.cerrar();
                    } catch (NoLecturaConfiguracionException e) {
                        vista.mostrarMensajeError("Error al leer la ip desde el archivo de configuracion.");
                    }
                }
            } catch (UnknownHostException e) {
                System.out.println("Error al obtener el numero de IP");
            }
        }
    }
    
    public void mostrarMensajeError() {
        vista.mostrarMensajeError("Error al intentar conectar al directorio.");
    }
}
