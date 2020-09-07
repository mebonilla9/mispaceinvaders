package dev.manuel.mispaceinvaders.sound;

import dev.manuel.mispaceinvaders.constants.Constants;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundFactory {

  private Clip clip;

  public SoundFactory() {

  }

  public void laser() {
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
        new File(Constants.LASER_SOUND)
      );
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.start();
    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
      e.printStackTrace(System.err);
    }
  }
  
  public void explosion() {
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
        new File(Constants.EXPLOSION_SOUND)
      );
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.start();
    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
      e.printStackTrace(System.err);
    }
  }

}
