package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Beef extends BahanMakanan{
    GamePanel gamePanel;
    public OBJ_Beef(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Beef");
        setDescription("[" + getName() + "]\nPotongan daging sapi segar.");
        setHarga(12);
        setKekenyanganValue(15);

        try {
            image = ImageIO.read(new File("././res/object/beef.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
