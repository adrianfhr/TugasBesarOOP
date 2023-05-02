package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Pitch extends Barang{
    GamePanel gamePanel;
    int pemilik;

    public OBJ_Pitch(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 3;
        this.width = 3;
        setName("Pitch");

        try {
            image = ImageIO.read(new File("././res/object/coffe_shop.png"));
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
        if(pemilik == gamePanel.player[gamePanel.currentPlayer].getId() ){
            gamePanel.player[gamePanel.currentPlayer].teleport(50, 50, gamePanel.player[gamePanel.currentPlayer].getId());
            gamePanel.playSoundEffect(2);
        }else{ 
            System.out.println("Berkunjung");
        }
    }


}
