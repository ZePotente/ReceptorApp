package modelo.alarma;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GestorArchivoAudio {
    private File archivoAudio; //Es un path, no un archivo
    private Clip audio;
    
    public GestorArchivoAudio() {
        /*
        archivoAudio = null;
        audio = null;
        */
    }
    
    public void abrirArchivo(String nombreArchivoAudio) throws LineUnavailableException, IOException,
                                                               UnsupportedAudioFileException, InterruptedException {
        archivoAudio = new File(nombreArchivoAudio);
        audio = AudioSystem.getClip();
        audio.open(AudioSystem.getAudioInputStream(archivoAudio));
    }
    
    //si hubiera mas archivos habria que tener una coleccion y buscarlo
    public void cerrarArchivo() {
        audio.close(); //no parece necesario
    }

    public Clip getAudio() {
        return audio;
    }
}
