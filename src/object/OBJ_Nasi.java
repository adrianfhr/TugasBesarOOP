package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
public class OBJ_Nasi extends BahanMakanan {
    GamePanel gamePanel;
    public OBJ_Nasi (GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Nasi");
        setDescription("[" + getName() + "]\nMakanan pokok.");
        setPrice(5);
        setKekenyanganValue(5);

        try {
            image = ImageIO.read(new File("././res/object/nasi.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
