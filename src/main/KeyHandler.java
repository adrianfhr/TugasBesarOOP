package main;


import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed;
    public int limit = 1;
     
    public KeyHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        int code = e.getKeyCode();
            if(gamePanel.gameState == gamePanel.playState){
                if(code == KeyEvent.VK_W){
                    upPressed = true;
                }
                if(code == KeyEvent.VK_S){
                    downPressed = true;
                }
                if(code == KeyEvent.VK_A){
                    leftPressed = true;
                }
                if(code == KeyEvent.VK_D){
                    rightPressed = true;
                }
                if(code == KeyEvent.VK_E  && limit == 1){
                    ePressed = true;
                    limit = 0;
                }
            }else if(gamePanel.gameState == gamePanel.interactObjState){
                if(gamePanel.isActiveAction){
                    ePressed = false;
                    gamePanel.isActiveAction = false;
                }
            }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_E){
            ePressed = false;
            limit = 1;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }
}
