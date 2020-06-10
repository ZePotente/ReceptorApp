package modelo_r.alarma;

import excepciones.AlarmaNoActivadaException;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public interface IAlarma {
    public void activar() throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException;
    public void desactivar() throws AlarmaNoActivadaException;
}
