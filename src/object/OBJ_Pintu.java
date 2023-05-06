package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Pintu extends Barang{
    GamePanel gamePanel;
    OBJ_Rumah rumah;

    public OBJ_Pintu(GamePanel gamePanel, OBJ_Rumah rumah){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 1;
        this.width = 1;
        setName("Pintu");
        this.rumah = rumah;
        
        try {
            image = ImageIO.read(new File("././res/object/door.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
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
        gamePanel.player[gamePanel.currentPlayer].teleport(rumah.worldX/gamePanel.tileSize  + 1 , rumah.worldY/gamePanel.tileSize + 3 , 0);
        gamePanel.playSoundEffect(2);
        gamePanel.player[gamePanel.currentPlayer].isBerkunjungAction = false;
        gamePanel.player[gamePanel.currentPlayer].currentRumah = this.rumah.pemilik;
    }
}
