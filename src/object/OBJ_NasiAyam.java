package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_NasiAyam extends Makanan{
    GamePanel gamePanel;
    public OBJ_NasiAyam(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Nasi Ayam");
        setDescription("[" + getName() + "]\nResep : Nasi dan Ayam");
        setKekenyanganValue(16);

        try{
            image = ImageIO.read(new File("././res/object/nasi_ayam.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 3/4, gamePanel.tileSize * 3/4));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}