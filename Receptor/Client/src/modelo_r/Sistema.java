package modelo_r;


import configuracion.Configuracion;

import excepciones.AlarmaNoActivadaException;

import excepciones.MalTipoDeMensajeException;

import excepciones.MensajeMalFormadoException;

import excepciones.NoConexionException;

import configuracion.NoLecturaConfiguracionException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Observable;

import java.util.Observer;

import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import modelo_r.alarma.GestorAlarma;

import modelo_r.desencriptacion.DesencriptacionCesarStrategy;
import modelo_r.desencriptacion.IDesencriptacionStrategy;

import modelo_r.mensaje.Mensaje;

public class Sistema extends Observable implements Observer, ILoginAuthenticator {
    // clase
    private static Sistema instancia;
    private static final int NRO_PUERTO = 123;
    private static final String ARCHIVO_CONFIG = "configuracion.txt";
    // instancia
    private InternetManager internetManager;
    private GestorAlarma alarma;
    private Configuracion config;
    private Usuario usuario;
    private IDesencriptacionStrategy desencriptador;

    private Sistema() {
        config = new Configuracion(ARCHIVO_CONFIG);
        alarma = new GestorAlarma();
        internetManager = new InternetManager();
        internetManager.escuchar(NRO_PUERTO);
        desencriptador = new DesencriptacionCesarStrategy();
    }
    
    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }
    
    public void ingresar(Usuario usuario) {
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
            mensaje.setAsunto(desencriptador.desencriptar(mensaje.getAsunto()));
            mensaje.setDescripcion(desencriptador.desencriptar(mensaje.getDescripcion()));
            setChanged();
            notifyObservers(mensaje);
            mensaje.ejecutar();
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
        new Thread() {
            public void run() {
                try {
                    System.out.println("Se va a notificar el cambio con los siguientes valores:");
                    System.out.println(valor + " " + usuario.getNombre() + " IP: " + usuario.getNumeroDeIP());
                    internetManager.notificarCambioDeEstado(valor, usuario.getNombre(), usuario.getNumeroDeIP(),
                                                            config.getNroIPDir1(), config.getPuertoDir1());
                } catch (Exception e) {
                    try {
                        internetManager.notificarCambioDeEstado(valor, usuario.getNombre(), usuario.getNumeroDeIP(),
                                                                config.getNroIPDir2(), config.getPuertoDir2());
                    } catch (Exception f) {
                        System.out.println("Error al conectar con el Directorio.");
                    }
                }
            }
        }.start();
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
