package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_MejaKursi extends SuperObject {
    GamePanel gamePanel;

    public OBJ_MejaKursi(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 3;
        this.width = 3;
        setName("Meja Kursi");

        try{
            image = ImageIO.read(new File("././res/object/mejakursi.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        }
        catch (IOException e){
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
}