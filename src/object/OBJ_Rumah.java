package object;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Rumah extends SuperObject{
    GamePanel gamePanel;

    public OBJ_Rumah(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 3;
        this.width = 3;
        setName("Rumah");
        
        try {
            image = ImageIO.read(new File("././res/object/rumah.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.collision = true;
        setsolidArea();
    }

    public void setsolidArea(){
        solidArea.x = worldX;
        solidArea.y = worldY;
        solidArea.width = gamePanel.tileSize * width;
        solidArea.height = gamePanel.tileSize * height;
    }

    public void interact(Player player ){
        gamePanel.player.teleport(50, 50, 1);
    }

    public void draw(Graphics2D g2d, GamePanel gamePanel){

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;; 
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
            
        // cut processing hanya menggambar saat dibutuhkan
        if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX && 
                        worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX && 
                        worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                        worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){
                
                //g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                g2d.drawImage(image, screenX, screenY, gamePanel.tileSize * width , gamePanel.tileSize * height , null);
            }
        
    }

}
