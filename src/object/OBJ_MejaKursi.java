package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_MejaKursi extends Barang {
    GamePanel gamePanel;

    public OBJ_MejaKursi(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 3;
        this.width = 3;
        setName("Meja Kursi");
        setStateOBJ("makan");
        setPrice(50);
        setDescription("[" + getName() + "]\nMeja dan kursi untuk makan.\n\nHarga : " + getPrice());

        try{
            image = ImageIO.read(new File("././res/object/mejakursi.png"));
            image_orang = ImageIO.read(new File("././res/object/mejakursi_orang.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        }
        catch (IOException e){
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
    }
}
