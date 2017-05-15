// Lucas Grönborg & Rickard Björklund

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundDevice extends Thread{

    public void playDrawSound() {
        try {
            File file = new File("C:\\Users\\LG-fi\\Google Drive\\Skola\\År 3\\Prutt\\lab3\\sounds\\jeff.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void playWinSound() {
        try {
            File file = new File("C:\\Users\\LG-fi\\Google Drive\\Skola\\År 3\\Prutt\\lab3\\sounds\\win.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void playLoseSound() {
        try {
            File file = new File("C:\\Users\\LG-fi\\Google Drive\\Skola\\År 3\\Prutt\\lab3\\sounds\\loss.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
