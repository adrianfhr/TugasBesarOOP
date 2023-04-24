package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public abstract class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public int height = 1, width = 1;
    public Rectangle solidArea = new Rectangle();
    public int solidAreaDefaultX = 0; public int solidAreaDefaultY = 0;
    public UtilityTool utilityTool = new UtilityTool();
    
    public void draw(Graphics2D g2d, GamePanel gamePanel){

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;; 
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
            
        // cut processing hanya menggambar saat dibutuhkan
        if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX && 
                        worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX && 
                        worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                        worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){
                
                //g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                g2d.drawImage(image, screenX, screenY, gamePanel.tileSize * width, gamePanel.tileSize * height, null);
            }
        
    }

    abstract public void setsolidArea();

    
}
