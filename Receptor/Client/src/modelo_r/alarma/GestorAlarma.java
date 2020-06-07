package modelo_r.alarma;

import excepciones.AlarmaNoActivadaException;

import java.io.IOException;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GestorAlarma {
    private String nombreArchivoAudio = "Alarma.wav";
    private GestorArchivoAudio gestor;
    private HiloDeAudio hiloAlarma;
    private boolean alarmaActivada = false;
    
    public GestorAlarma() {
        gestor = new GestorArchivoAudio();
    }
    
    public void activar() throws LineUnavailableException, IOException, UnsupportedAudioFileException,
                                 InterruptedException {
        if (!alarmaActivada) {
            gestor.abrirArchivo(nombreArchivoAudio);
            reproducirAudio(gestor.getAudio());
            alarmaActivada = true;
        }     
    }    
    
    private void reproducirAudio(Clip audio) {
        hiloAlarma = new HiloDeAudio(audio);
        hiloAlarma.start();
    }

    public void desactivar() throws AlarmaNoActivadaException {
        if (alarmaActivada == true) {
            hiloAlarma.terminarHilo();
            gestor.cerrarArchivo();
            alarmaActivada = false;
        }
        else {
            throw new AlarmaNoActivadaException(alarmaActivada);
        }
    }
}
