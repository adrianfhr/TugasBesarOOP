package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
public class OBJ_Wortel extends BahanMakanan {
    GamePanel gamePanel;
    public OBJ_Wortel(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Wortel");
        setDescription("[" + getName() + "]\nUntuk kesehatan MUATAMU \nlho mas.");
        setPrice(3);
        setKekenyanganValue(2);

        try {
            image = ImageIO.read(new File("././res/object/wortel.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
