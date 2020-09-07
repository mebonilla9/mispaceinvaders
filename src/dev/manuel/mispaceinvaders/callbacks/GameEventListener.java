package dev.manuel.mispaceinvaders.callbacks;

import dev.manuel.mispaceinvaders.ui.GamePanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameEventListener extends KeyAdapter{
  
  private GamePanel board;

  public GameEventListener(GamePanel board) {
    this.board = board;
  }

  @Override
  public void keyReleased(KeyEvent e) {
    this.board.keyReleased(e);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    this.board.keyPressed(e);
  }
  
  
  
}
