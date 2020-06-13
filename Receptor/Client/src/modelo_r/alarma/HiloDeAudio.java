package modelo_r.alarma;

import javax.sound.sampled.Clip;

public class HiloDeAudio extends Thread {
    private boolean botonApretado;
    private Clip audio;
    
    public HiloDeAudio(Clip audio) {
        this.audio = audio;
    }
    
    @Override
    public void run() {
        botonApretado = false;
        this.audio.start();
        this.audio.loop(Clip.LOOP_CONTINUOUSLY);
        while(botonApretado == false) {
            try {
                Thread.sleep(audio.getMicrosecondLength() / 1000);
            } catch (InterruptedException e) {
                this.botonApretado = true;
            }
        }
    }

    public void terminarHilo() {
        this.audio.stop();
        this.interrupt(); //interrumpe el hilo del ciclo del run
    }

    public boolean isBotonApretado() {
        return botonApretado;
    }
}
