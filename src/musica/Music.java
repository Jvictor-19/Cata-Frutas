package musica;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Music {
    private Clip clip;

    // Metodo para tocar a música
    public void play(String arquivo) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL musicUrl = getClass().getClassLoader().getResource(arquivo);
        if (musicUrl == null) {
            System.out.println("Música não encontrada: " + arquivo);
            return;
        }

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicUrl);
        clip = AudioSystem.getClip();
        clip.open(audioStream);

        // Adiciona um listener para quando a música terminar
        clip.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) {
                System.out.println("A música acabou");
                clip.setFramePosition(0); // Reinicia a música
                clip.start(); // Começa a tocar novamente
            }
        });

        clip.start();
    }

    // Metodo para parar a música
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
