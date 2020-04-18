package modelo;


import excepciones.AlarmaNoActivadaException;

import excepciones.MalTipoDeMensajeException;

import excepciones.MensajeMalFormadoException;

import java.io.IOException;

import java.net.InetAddress;

import java.net.UnknownHostException;

import java.util.Observable;

import java.util.Observer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import modelo.mensaje.Mensaje;

public class Sistema extends Observable implements Observer {
    // clase
    private static Sistema instancia;
    private static final int NRO_PUERTO = 123;
    // instancia
    private InternetManager internetManager;
    private GestorAlarma alarma;
    private Usuario usuario;

    private Sistema() {
        alarma = new GestorAlarma();
        internetManager = new InternetManager();
        internetManager.escuchar(NRO_PUERTO);
    }
    
    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void apagarAlarma() {
        try {
            alarma.desactivar();
        } catch (AlarmaNoActivadaException e) {
            // sin funcionalidad
        }
    }

    // TODO agregar como se muestran las excepciones
    public void encenderAlarma() {
        try {
            alarma.activar();
        } catch (IOException | InterruptedException | LineUnavailableException | UnsupportedAudioFileException e) {
            // error en la lectura del archivo
            // sin funcionalidad
        }
    }

    public void mensajeRecibido(String msg) {
        Mensaje mensaje;
        try {
            mensaje = Mensaje.armar(msg);
            setChanged();
            notifyObservers(mensaje);
            mensaje.ejecutar(); // hace cosas diferentes dependiendo del tipo de mensaje
        } catch (MalTipoDeMensajeException e) {
            // Mal tipo de mensaje
            // System.out.println("EXCEPCION MALTIPO.");
            // sin funcionalidad
        } catch (MensajeMalFormadoException e) {
            // Cantidad de atributos del archivo erronea
            // System.out.println("EXCEPCION LA CANTIDAD DE ATRIBUTOS NO COINCIDE.");
            // sin funcionalidad
        }
    }
    
    public void notificarCambioDeEstado(boolean valor) {
        internetManager.notificarCambioDeEstado(usuario.getNombre(), usuario.getNumeroDeIP(), valor, 100, usuario.getNumeroDeIP());
    }
    
    // los tres metodos que siguen son para manejo de recepcion de mensajes en el IMR

    public void conexionExitosa() {
    }
    
    public void errorConexion(String error) {
    }

    public void finConexion() {
    }

    @Override
    public void update(Observable observable, Object object) {
        setChanged();
        notifyObservers(object);
    }
}
