package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Timer;

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
        drawPlayerAttribute();

        
        
    }

    public void statistic(){
        String waktu = String.format("%02d:%02d", (gamePanel.clock/60), (gamePanel.clock%60));
        g2.drawString(waktu, 15*16*3, 10);
        g2.drawString("DAY-" + gamePanel.clock/(60*24),  15*16*3, 10 + 16);
        g2.drawString("FPS: " + gamePanel.fps, 5, 15*16*3 + 16);
        g2.drawString("X: " + (gamePanel.player.worldX/16) + " Y: " + (gamePanel.player.worldY/16 ), 5, 15*16*3 + 16*2);
        

        
        
        if(gamePanel.player.isInteracting){
            g2.drawString("Push 'E' for interact", gamePanel.player.screenX - 48, gamePanel.player.screenY - 16 );
        }
    }

    public void drawPlayerAttribute(){
        g2.drawString("Name: " + gamePanel.player.getName(), 5, 1*16*3 );
        g2.drawString("Job: " + gamePanel.player.getJob(), 5, 1*16*3 + 16);
        g2.drawString("Mood: " + gamePanel.player.getMood(), 5, 1*16*3 + 32);
        g2.drawString("Health: " + gamePanel.player.getHealth(), 5, 1*16*3 + 48);
        g2.drawString("Hunger: " + gamePanel.player.getHunger(), 5, 1*16*3 + 64);
        g2.drawString("Money: " + gamePanel.player.getMoney(), 5, 1*16*3 + 80);

    }
    
}
