package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Whiskas extends BahanMakanan{
    GamePanel gamePanel;
    public OBJ_Whiskas(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Whiskas");
        setDescription("[" + getName() + "]\nMakanan kucing! jangan dimakan!");
        setPrice(10);
        setKekenyanganValue(5);

        try {
            image = ImageIO.read(new File("././res/object/whiskas.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
