package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Hotel extends Barang{
    GamePanel gamePanel;
    int pemilik;

    public OBJ_Hotel(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 3;
        this.width = 3;
        setName("Hotel");

        try {
            image = ImageIO.read(new File("././res/object/hotel2.png"));
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
        gamePanel.setGameState(gamePanel.dialogueState);
        gamePanel.ui.setCurrentDialogue("Masih tutup...");
        gamePanel.playSoundEffect(27);
    }


}
