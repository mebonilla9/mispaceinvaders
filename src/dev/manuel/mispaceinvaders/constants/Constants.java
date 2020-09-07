package dev.manuel.mispaceinvaders.constants;

public class Constants {

  public static final String TITLE = "Space Invaders UMB Edition";
  public static final int BOARD_WIDTH = 900;
  public static final int BOARD_HEIGHT = 750;
  
  // Game state messages
  public static final String WIN = "Win!";
  public static final String GAME_OVER = "Game Over!";

  // UFO constants
  public static final int ENEMY_SHIP_WIDTH = 64;
  public static final int ENEMY_SHIP_HEIGHT = 64;
  public static final int ENEMY_SHIP_INIT_X = 155;
  public static final int ENEMY_SHIP_INIT_Y = 30;
  public static final int ENEMY_SHIPS_ROW = 4;
  public static final int ENEMY_SHIPS_COLUMN = 8;
  
  // Constant to the enemyships are not go till the border of the frame
  public static final int BORDER_PADDING = 50;
  // Every time the UFO's hit one side of the frame
  public static final int GO_DOWN = 10;
  
  public static final int BOMB_HEIGHT = 24;
  public static final double BOMB_DROPING_PROBABILITY = 0.0006;
  
  // Speed of the application
  public static final int GAME_SPEED = 10;
  public static final int SPACESHIP_WIDTH = 64;
  public static final int SPACESHIP_HEIGHT = 64;
  
  // Speed of the Laser
  public static final int LASER_HORIZONTAL_TRANSLATION = 8;

  // Images for the objects
  public static final String UFO_IMAGE_URL = "images/ufo.png";
  public static final String LASER_IMAGE_URL = "images/laser.png";
  public static final String BOMB_IMAGE_URL = "images/bomb.png";
  public static final String BACKGROUND_IMAGE_URL = "images/background.jpg";
  public static final String SPACESHIP_URL = "images/spaceship.png";
  
  
  public static final String LASER_SOUND = "sounds/laser.wav";
  public static final String EXPLOSION_SOUND = "sounds/explosion.wav";

  private Constants() {
  }

}
