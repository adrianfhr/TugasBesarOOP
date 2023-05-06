package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
public class OBJ_Susu extends BahanMakanan {
    GamePanel gamePanel;
    public OBJ_Susu(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Susu");
        setPrice(2);
        setKekenyanganValue(1);
        setDescription("[" + getName() + "]\nDari sapi, bukan yang lain.\n\nHarga : " + getPrice());

        try {
            image = ImageIO.read(new File("././res/object/susu.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
