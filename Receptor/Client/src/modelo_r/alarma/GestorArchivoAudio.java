package modelo_r.alarma;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GestorArchivoAudio {
    private File archivoAudio;
    private Clip audio;
    
    public void abrirArchivo(String nombreArchivoAudio) throws LineUnavailableException, IOException,
                                                               UnsupportedAudioFileException, InterruptedException {
        archivoAudio = new File(nombreArchivoAudio);
        audio = AudioSystem.getClip();
        audio.open(AudioSystem.getAudioInputStream(archivoAudio));
    }
    
    public void cerrarArchivo() {
        audio.close();
    }

    public Clip getAudio() {
        return audio;
    }
}
