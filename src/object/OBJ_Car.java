package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Car extends Barang{
    GamePanel gamePanel;

    public OBJ_Car(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 1;
        this.width = 1;
        setName("Car");
        
        try {
            image = ImageIO.read(new File("././res/object/garage.png"));
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

    public void interact(Player player){
        if (gamePanel.player[gamePanel.currentPlayer].naikMobil){
            gamePanel.player[gamePanel.currentPlayer].naikMobil = false;
            gamePanel.player[gamePanel.currentPlayer].speed = 4;
            gamePanel.playSoundEffect(25);
        } else {
            gamePanel.player[gamePanel.currentPlayer].speed = 10;
            gamePanel.player[gamePanel.currentPlayer].naikMobil = true;
            gamePanel.playSoundEffect(16);
        }
        gamePanel.player[gamePanel.currentPlayer].direction = "up";

    }
}
