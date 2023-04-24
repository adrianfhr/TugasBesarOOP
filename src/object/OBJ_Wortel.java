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
        setDescription("[" + getName() + "]\nUntuk kesehatan MUATAMU lho mas.");
        setHarga(3);
        setKekenyanganValue(2);

        try {
            image = ImageIO.read(new File("././res/object/wortel.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
