package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font arial_40;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 12);
        
    }

    public void draw(Graphics2D g2d){
        this.g2 = g2d;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        statistic();

        
        
    }

    public void statistic(){
        g2.drawString("FPS: " + gamePanel.fps, 5, 15*16*3 );
        g2.drawString("X: " + (gamePanel.player.worldX/16) + " Y: " + (gamePanel.player.worldY/16 ), 5, 15*16*3 + 16);
        
        
        if(gamePanel.player.isInteracting){
            g2.drawString("Push 'E' for interact", gamePanel.player.screenX - 48, gamePanel.player.screenY - 16 );
        }
    }

    public void drawPlayerAttribute(){
        
    }
    
}
