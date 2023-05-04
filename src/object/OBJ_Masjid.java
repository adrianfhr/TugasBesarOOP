package object;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Masjid extends SuperObject{
    GamePanel gamePanel;

    public OBJ_Masjid(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 2;
        this.width = 2;
        setName("Masjid");
        setStateOBJ("Ibadah");
        
        try {
            image = ImageIO.read(new File("././res/object/masjid.png"));
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
        solidArea.width = gamePanel.tileSize * width *2;
        solidArea.height = gamePanel.tileSize * height*2;
    }

    public void interact(Player player){
        gamePanel.player[gamePanel.currentPlayer].setMood(gamePanel.player[gamePanel.currentPlayer].getMood() + 5);
    }

    public void draw(Graphics2D g2d, GamePanel gamePanel){

        int screenX = worldX - gamePanel.player[gamePanel.currentPlayer].worldX + gamePanel.player[gamePanel.currentPlayer].screenX;; 
        int screenY = worldY - gamePanel.player[gamePanel.currentPlayer].worldY + gamePanel.player[gamePanel.currentPlayer].screenY;
            
        // cut processing hanya menggambar saat dibutuhkan
        if(worldX + gamePanel.tileSize > gamePanel.player[gamePanel.currentPlayer].worldX - gamePanel.player[gamePanel.currentPlayer].screenX && 
                        worldX - gamePanel.tileSize < gamePanel.player[gamePanel.currentPlayer].worldX + gamePanel.player[gamePanel.currentPlayer].screenX && 
                        worldY + gamePanel.tileSize > gamePanel.player[gamePanel.currentPlayer].worldY - gamePanel.player[gamePanel.currentPlayer].screenY &&
                        worldY - gamePanel.tileSize < gamePanel.player[gamePanel.currentPlayer].worldY + gamePanel.player[gamePanel.currentPlayer].screenY){
                
                //g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                g2d.drawImage(image, screenX, screenY, gamePanel.tileSize * width * 2, gamePanel.tileSize * height * 2, null);
            }
        
    }

}
