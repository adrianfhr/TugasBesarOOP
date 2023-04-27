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
        gamePanel.player[gamePanel.currentPlayer].teleport(50, 50, 1);
        gamePanel.playSoundEffect(2);
    }


}
