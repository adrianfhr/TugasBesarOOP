package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
public class OBJ_Kentang extends BahanMakanan{
    GamePanel gamePanel;
    public OBJ_Kentang(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Kentang");
        setPrice(3);
        setKekenyanganValue(4);
        setDescription("[" + getName() + "]\nPengganti nasi.\n\nHarga : " + getPrice());

        try {
            image = ImageIO.read(new File("././res/object/kentang.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
