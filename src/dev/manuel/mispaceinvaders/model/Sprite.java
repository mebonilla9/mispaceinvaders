package dev.manuel.mispaceinvaders.model;

import java.awt.Image;

public abstract class Sprite {
  
  private Image image;
  private boolean dead;

  protected int x;
  protected int y;
  protected int dx;

  public Sprite() {
    this.dead = false;
  }

  public abstract void move();

  public void die() {
    this.dead = true;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
  
  public boolean isDead() {
    return dead;
  }

  public void setDead(boolean dead) {
    this.dead = dead;
  }
  
}
