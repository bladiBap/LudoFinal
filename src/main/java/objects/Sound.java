package objects;

import javax.sound.sampled.*;

import java.io.IOException;
import java.io.InputStream;

public class Sound {

    private final Clip audioClip;

    public Sound(InputStream ruta) {
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(ruta);
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }

        try {
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);

        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void play (){
        new Thread(() -> {
            audioClip.setFramePosition(0);
            audioClip.start();
        }){}.start();
    }

    public void loop (){
        new Thread(() -> {
            audioClip.setFramePosition(0);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        }){}.start();
    }

    public void stop (){
        new Thread(() -> {
            audioClip.stop();
        }){}.start();
    }
}
