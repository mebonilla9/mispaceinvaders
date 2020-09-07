package dev.manuel.mispaceinvaders.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop implements ActionListener {

    private GamePanel gamePanel;

    public GameLoop(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.gamePanel.doOneLoop();
    }
    
}
