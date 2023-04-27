package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Pengemis extends SuperObject{
    GamePanel gamePanel;

    public OBJ_Pengemis(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 1;
        this.width = 1;
        setName("Pengemis");
        
        try {
            image = ImageIO.read(new File("././res/object/pengemis.png"));
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

    public void interact(Player player){
        gamePanel.player.setMoney(gamePanel.player.getMoney() - 2);
        gamePanel.player.setMood(gamePanel.player.getMood() + 10);
        gamePanel.playSoundEffect(12);
    }
}
