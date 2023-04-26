package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Player;
import main.GamePanel;

public class OBJ_Pintu extends SuperObject{
    GamePanel gamePanel;

    public OBJ_Pintu(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 1;
        this.width = 1;
        setName("Pintu");
        
        try {
            image = ImageIO.read(new File("././res/object/door.png"));
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
        gamePanel.player.teleport(36, 24, 0);
    }

}