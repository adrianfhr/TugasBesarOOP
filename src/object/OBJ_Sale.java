package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Sale extends Barang{
    GamePanel gamePanel;

    public OBJ_Sale(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 1;
        this.width = 1;
        setName("Sale");
        setPrice(10);
        
        try {
            image = ImageIO.read(new File("././res/object/sale.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize/3 * 2, gamePanel.tileSize/3 * 2));
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

    @Override
    public void interact(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

}
