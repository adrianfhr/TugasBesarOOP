package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Hospital extends Barang{
    GamePanel gamePanel;
    int pemilik;

    public OBJ_Hospital(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 4;
        this.width = 5;
        setName("Hospital");

        try {
            image = ImageIO.read(new File("././res/object/hospital.png"));
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
