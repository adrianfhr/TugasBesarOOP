package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_TV extends Barang{
    GamePanel gamePanel;

    public OBJ_TV(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 1;
        this.width = 1;

        setStateOBJ("Nonton");
        setName("TV");
        setDescription("[" + getName() + "]\nLoh... jaman sekarang masih\nada orang nonton TV?");
        setPrice(75);
        
        try {
            image = ImageIO.read(new File("././res/object/tv.png"));
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
        
    }

}