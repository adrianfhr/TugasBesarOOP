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
        setHarga(5);
        setKekenyanganValue(5);

        try {
            image = ImageIO.read(new File("././res/object/nasi.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
