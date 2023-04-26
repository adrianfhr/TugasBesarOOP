package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Player;
import main.GamePanel;

public class OBJ_SingleBed extends SuperObject{
    GamePanel gamePanel;

    public OBJ_SingleBed(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 4;
        this.width = 1;
        setDescription("tidur");
        setName(" Single Bed");
        
        try {
            image = ImageIO.read(new File("././res/object/single_bed.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.collision = false;
        setsolidArea();
    }

    public void setsolidArea(){
        solidArea.x = worldX;
        solidArea.y = worldY;
        solidArea.width = gamePanel.tileSize * width;
        solidArea.height = gamePanel.tileSize * height;
    }

    public void interact(Player player ){
        gamePanel.player.setHealth(gamePanel.player.getHealth() + 30);
    
    }

}
