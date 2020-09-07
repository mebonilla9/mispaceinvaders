package dev.manuel.mispaceinvaders.ui;

import dev.manuel.mispaceinvaders.constants.Constants;
import dev.manuel.mispaceinvaders.image.Image;
import dev.manuel.mispaceinvaders.image.ImageFactory;

import javax.swing.JFrame;

public class GameMainFrame extends JFrame {

  public GameMainFrame() {
    initializeLayout();
  }

  private void initializeLayout() {
    this.add(new GamePanel());
    this.setTitle(Constants.TITLE);
    this.setIconImage(ImageFactory.createImage(Image.BACKGROUND).getImage());
    this.pack();
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setVisible(true);
  }
}
