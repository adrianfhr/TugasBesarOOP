package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_TumisSayur extends Makanan{
    GamePanel gamePanel;
    public OBJ_TumisSayur(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Tumis Sayaran");
        setDescription("[" + getName() + "]\nResep : Wortel dan Bayam");
        setKekenyanganValue(5);

        try{
            image = ImageIO.read(new File("././res/object/tumis_sayur.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 3/4, gamePanel.tileSize * 3/4));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}