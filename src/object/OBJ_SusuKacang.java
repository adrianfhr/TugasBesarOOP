package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_SusuKacang extends Makanan{
    GamePanel gamePanel;
    public OBJ_SusuKacang(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Susu Kacang");
        setDescription("[" + getName() + "]\nResep : Susu dan Kacang");
        setKekenyanganValue(5);

        try{
            image = ImageIO.read(new File("././res/object/susu_kacang.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 3/4, gamePanel.tileSize * 3/4));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
}