package dev.manuel.mispaceinvaders.ui;

import dev.manuel.mispaceinvaders.callbacks.GameEventListener;
import dev.manuel.mispaceinvaders.constants.Constants;
import dev.manuel.mispaceinvaders.image.Image;
import dev.manuel.mispaceinvaders.image.ImageFactory;
import dev.manuel.mispaceinvaders.model.Bomb;
import dev.manuel.mispaceinvaders.model.EnemyShip;
import dev.manuel.mispaceinvaders.model.Laser;
import dev.manuel.mispaceinvaders.model.SpaceShip;
import dev.manuel.mispaceinvaders.sound.SoundFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {

  private ImageIcon backgroundImage;
  private SoundFactory soundFactory;
  private Timer timer;
  private SpaceShip spaceShip;
  private Laser laser;
  private boolean inGame;
  private int direction;
  private List<EnemyShip> enemyShips;
  private List<Bomb> bombs;
  private Random generator;
  private String message;
  private int deaths;
  private int score;
  private int shields;

  public GamePanel() {
    initializeVariables();
    initializeLayout();
    initializeGame();
  }

  private void initializeVariables() {
    this.soundFactory = new SoundFactory();
    this.generator = new Random();
    this.enemyShips = new ArrayList<>();
    this.bombs = new ArrayList<>();
    this.inGame = true;
    this.direction = -1;
    this.shields = 2;
    this.spaceShip = new SpaceShip();
    this.laser = new Laser();
    this.backgroundImage = ImageFactory.createImage(Image.BACKGROUND);
    this.timer = new Timer(Constants.GAME_SPEED, new GameLoop(this));
    this.timer.start();
  }

  private void initializeGame() {
    for (int i = 0; i < Constants.ENEMY_SHIPS_ROW; i++) {
      for (int j = 0; j < Constants.ENEMY_SHIPS_COLUMN; j++) {
        EnemyShip enemyShip = new EnemyShip(
          Constants.ENEMY_SHIP_INIT_X + 75 * j,
          Constants.ENEMY_SHIP_INIT_Y + 60 * i
        );
        enemyShips.add(enemyShip);
      }
    }
  }

  private void initializeLayout() {
    addKeyListener(new GameEventListener(this));
    setFocusable(true);
    this.setPreferredSize(new Dimension(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT));
  }

  private void drawPlayer(Graphics graphics) {
    graphics.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
  }

  private void drawLaser(Graphics graphics) {
    if (!laser.isDead()) {
      graphics.drawImage(laser.getImage(), laser.getX(), laser.getY(), this);
    }
  }

  private void drawAliens(Graphics graphics) {
    for (EnemyShip enemyShip : enemyShips) {
      if (enemyShip.isVisible()) {
        graphics.drawImage(enemyShip.getImage(), enemyShip.getX(), enemyShip.getY(), this);
      }
    }
  }

  private void drawBombs(Graphics graphics) {
    for (Bomb bomb : bombs) {
      if (!bomb.isDead()) {
        graphics.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), this);
      }
    }
  }

  private void drawScore(Graphics graphics) {

    if (!inGame) {
      return;
    }

    Font font = new Font("Helvetica", Font.BOLD, 20);
    graphics.setColor(Color.GREEN);
    graphics.setFont(font);
    graphics.drawString("Score: " + score, Constants.BOARD_WIDTH - 100, 25);
    graphics.drawString("Shields: " + shields, 25, 25);
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    graphics.drawImage(
      this.backgroundImage.getImage(),
      0,
      0,
      null);
    doDrawing(graphics);
  }

  private void doDrawing(Graphics graphics) {
    if (!inGame && this.timer.isRunning()) {
      this.timer.stop();
      drawGameOver(graphics);
      return;
    }
    drawScore(graphics);
    drawPlayer(graphics);
    drawLaser(graphics);
    drawAliens(graphics);
    drawBombs(graphics);
    Toolkit.getDefaultToolkit().sync();
  }

  public void doOneLoop() {
    update();
    repaint();
  }

  private void update() {
    if (this.spaceShip.isDead()) {
      this.inGame = false;
      message = Constants.GAME_OVER;
    }
    if (deaths == this.enemyShips.size()) {
      this.inGame = false;
      message = Constants.WIN;
    }
    this.spaceShip.move();
    detectCollisionLaserUfo();
    this.moveEnemies();
  }

  public void keyReleased(KeyEvent e) {
    this.spaceShip.keyReleased(e);
  }

  public void keyPressed(KeyEvent e) {
    this.spaceShip.keyPressed(e);
    int key = e.getKeyCode();
    if (key == KeyEvent.VK_SPACE) {
      int laserX = this.spaceShip.getX();
      int laserY = this.spaceShip.getY();
      if (inGame && laser.isDead()) {
        laser = new Laser(laserX, laserY);
        soundFactory.laser();
      }
    }
  }

  private void moveEnemies() {
    for (EnemyShip enemyShip : this.enemyShips) {
      if (enemyShip.getX()
        >= Constants.BOARD_WIDTH - 2 * Constants.BORDER_PADDING
        && direction != -1
        || enemyShip.getX() <= Constants.BORDER_PADDING
        && direction != 1) {
        direction *= -1;
        moveVerticalEnemies();
      }
      if (enemyShip.isVisible()) {
        if (enemyShip.getY() > Constants.BOARD_HEIGHT - 100 - Constants.SPACESHIP_HEIGHT) {
          spaceShip.die();
        }
        enemyShip.move(direction);
      }
    }

    generateBombsForEnemies();
  }

  private void moveVerticalEnemies() {
    Iterator<EnemyShip> enemyIterator = enemyShips.iterator();
    while (enemyIterator.hasNext()) {
      EnemyShip nextEnemy = enemyIterator.next();
      nextEnemy.setY(nextEnemy.getY() + Constants.GO_DOWN);
    }
  }

  private void generateBombsForEnemies() {
    for (EnemyShip enemyShip : this.enemyShips) {
      if (enemyShip.isVisible()
        && generator.nextDouble() < Constants.BOMB_DROPING_PROBABILITY) {
        Bomb bomb = new Bomb(enemyShip.getX(), enemyShip.getY());
        bombs.add(bomb);
      }
    }

    moveBombs();
  }

  private void moveBombs() {
    for (Bomb bomb : this.bombs) {

      int bombX = bomb.getX();
      int bombY = bomb.getY();
      int spaceShipX = this.spaceShip.getX();
      int spaceShipY = this.spaceShip.getY();

      if (!bomb.isDead() && !spaceShip.isDead()) {

        if (bombX >= spaceShipX
          && bombX <= (spaceShipX + Constants.SPACESHIP_WIDTH)
          && bombY >= spaceShipY
          && bombY <= (spaceShipY + Constants.SPACESHIP_HEIGHT)) {
          bomb.die();

          soundFactory.explosion();
          shields--;
          if (shields <= 0) {
            spaceShip.die();
            shields = 0;
          }

        }

      }

      if (!bomb.isDead()) {
        bomb.move();
      }
    }
  }

  private void detectCollisionLaserUfo() {
    if (!laser.isDead()) {
      int shotX = laser.getX();
      int shotY = laser.getY();
      for (EnemyShip enemyShip : this.enemyShips) {
        int enemyX = enemyShip.getX();
        int enemyY = enemyShip.getY();
        if (!enemyShip.isVisible()) {
          continue;
        }
        // Logic of the collision detection
        if (shotX >= enemyX
          && shotX <= (enemyX + Constants.ENEMY_SHIP_WIDTH)
          && shotY >= enemyY
          && shotY <= (enemyY + Constants.ENEMY_SHIP_HEIGHT)) {
          enemyShip.setVisible(false);
          laser.die();
          deaths++;
          soundFactory.explosion();
          score += 20;
        }
      }
      this.laser.move();
    }
  }

  private void drawGameOver(Graphics graphics) {
    graphics.drawImage(
      this.backgroundImage.getImage(),
      0,
      0,
      null);
    Font font = new Font("Helvetica", Font.BOLD, 50);
    FontMetrics fontMetrics = this.getFontMetrics(font);
    graphics.setColor(Color.white);
    graphics.setFont(font);
    graphics.drawString(
      message,
      (Constants.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
      Constants.BOARD_HEIGHT / 2 - 100
    );
  }
}
