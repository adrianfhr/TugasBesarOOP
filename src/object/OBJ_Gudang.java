package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Gudang extends Barang{
    GamePanel gamePanel;
    public OBJ_Gudang(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 3;
        this.width = 2;
        setName("Gudang");

        try {
            image = ImageIO.read(new File("././res/object/gudang.png"));
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
        System.out.println("Berkunjung");
    }


}
