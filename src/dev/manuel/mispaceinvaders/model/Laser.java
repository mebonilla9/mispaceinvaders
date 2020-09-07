package dev.manuel.mispaceinvaders.model;

import dev.manuel.mispaceinvaders.constants.Constants;
import dev.manuel.mispaceinvaders.image.Image;
import dev.manuel.mispaceinvaders.image.ImageFactory;
import javax.swing.ImageIcon;

public class Laser extends Sprite {

  public Laser() {
  }

  public Laser(int x, int y) {
    this.x = x;
    this.y = y;
    initialize();
  }

  private void initialize() {
    ImageIcon imageIcon = ImageFactory.createImage(Image.LASER);
    setImage(imageIcon.getImage());
    setX(x + Constants.SPACESHIP_WIDTH / 2);
    setY(y);
  }

  @Override
  public void move() {
    this.y -= Constants.LASER_HORIZONTAL_TRANSLATION;
    if (this.y < 0) {
      this.die();
    }
  }

}
