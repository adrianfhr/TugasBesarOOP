package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Restaurant extends Barang{
    GamePanel gamePanel;

    public OBJ_Restaurant(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 3;
        this.width = 3;
        setName("Restaurant");
        setPrice(150);
        
        try {
            image = ImageIO.read(new File("././res/object/restaurant.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
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
        gamePanel.setGameState(gamePanel.dialogueState);
        gamePanel.ui.setCurrentDialogue("Masih tutup...");
        gamePanel.playSoundEffect(27);
    }


}
