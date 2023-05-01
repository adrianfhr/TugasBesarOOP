package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Eskrim extends BahanMakanan{
    GamePanel gamePanel;
    public OBJ_Eskrim(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Es Krim Mixue");
        setDescription("[" + getName() + "]\nEnak tapi belum halal");
        setPrice(10);
        setKekenyanganValue(2);

        try {
            image = ImageIO.read(new File("././res/Mixue/eskrim.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
