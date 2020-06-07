package modelo_r;


import excepciones.AlarmaNoActivadaException;

import excepciones.MalTipoDeMensajeException;

import excepciones.MensajeMalFormadoException;

import excepciones.NoConexionException;

import excepciones.NoLecturaConfiguracionException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Observable;

import java.util.Observer;

import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import modelo_r.alarma.GestorAlarma;

import modelo_r.mensaje.Mensaje;

public class Sistema extends Observable implements Observer {
    // clase
    private static Sistema instancia;
    private static final int NRO_PUERTO = 123, NRO_PUERTO_DIRECTORIO = 100;
    private static final String ARCHIVO_CONFIG = "configuracion.txt";
    // instancia
    private String NRO_IP_DIRECTORIO = "";
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
    
    /**
     * Lee el archivo de configuracion.txt
     * y asigna la IP leida a la variable local que la contiene.
     * 
     * @throws NoLecturaConfiguracionException Si ocurre un error con la lectura del archivo de configuracion.
     */
    public void leerConfig() throws NoLecturaConfiguracionException {
        try {
            FileInputStream arch;
            arch = new FileInputStream(ARCHIVO_CONFIG);
            Scanner sc = new Scanner(arch);    
            
            this.NRO_IP_DIRECTORIO = sc.nextLine();
            sc.close();
        } catch (FileNotFoundException e) {
            throw new NoLecturaConfiguracionException(e);
        }
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
            // update()?
            setChanged();
            notifyObservers(mensaje);
            //
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
    /*
    public void notificarCambioDeEstado(boolean valor) throws NoConexionException {
        try {
            internetManager.notificarCambioDeEstado(valor, usuario.getNombre(), usuario.getNumeroDeIP(),
                                                    NRO_IP_DIRECTORIO, NRO_PUERTO_DIRECTORIO);
        } catch (Exception e) {
            throw new NoConexionException(e);
        }
    }
    */
    public void notificarCambioDeEstado(boolean valor) { 
        new Thread() {
            public void run() {
                try {
                    System.out.println("Se va a notificar el cambio con los siguientes valores:");
                    System.out.println(valor + " " + usuario.getNombre() + " IP: " + usuario.getNumeroDeIP());
                    internetManager.notificarCambioDeEstado(valor, usuario.getNombre(), usuario.getNumeroDeIP(),
                                                            NRO_IP_DIRECTORIO, NRO_PUERTO_DIRECTORIO);
                } catch (Exception e) {
                    System.out.println("Error al conectar con el Directorio.");
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
