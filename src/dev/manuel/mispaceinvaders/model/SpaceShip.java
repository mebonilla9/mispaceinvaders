package dev.manuel.mispaceinvaders.model;

import dev.manuel.mispaceinvaders.constants.Constants;
import dev.manuel.mispaceinvaders.image.Image;
import dev.manuel.mispaceinvaders.image.ImageFactory;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class SpaceShip extends Sprite {

  public SpaceShip() {
    initialize();
  }

  private void initialize() {
    ImageIcon imageIcon = ImageFactory.createImage(Image.SPACESHIP);
    this.setImage(imageIcon.getImage());

    int startX = (Constants.BOARD_WIDTH / 2) - (Constants.SPACESHIP_WIDTH / 2);
    int startY = Constants.BOARD_HEIGHT - 100;

    setX(startX);
    setY(startY);
  }

  @Override
  public void move() {
    if (x < Constants.SPACESHIP_WIDTH) {
      x = Constants.SPACESHIP_WIDTH;
    }
    if (x >= Constants.BOARD_WIDTH - (2*Constants.SPACESHIP_WIDTH)) {
      x = Constants.BOARD_WIDTH - (2*Constants.SPACESHIP_WIDTH);
    }
    x += dx;
  }

  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    switch (key) {
      case KeyEvent.VK_LEFT:
      case KeyEvent.VK_A:
        dx = -5;
        break;
      case KeyEvent.VK_RIGHT:
      case KeyEvent.VK_D:
        dx = 5;
        break;
    }
  }

  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
    switch (key) {
      case KeyEvent.VK_LEFT:
      case KeyEvent.VK_A:
      case KeyEvent.VK_RIGHT:
      case KeyEvent.VK_D:
        dx = 0;
        break;
    }
  }

}
