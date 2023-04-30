package object;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Bistik extends Makanan{
    GamePanel gamePanel;
    public OBJ_Bistik(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Bistik");
        setDescription("[" + getName() + "]\nResep : Kentang dan Sapi");
        setKekenyanganValue(22);

        try{
            image = ImageIO.read(new File("././res/object/bistik.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 3/4, gamePanel.tileSize * 3/4));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
}