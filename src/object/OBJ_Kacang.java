package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Kacang extends BahanMakanan {
    GamePanel gamePanel;
    public OBJ_Kacang(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Kacang");
        setDescription("[" + getName() + "]\nMakanan pokok.");
        setHarga(2);
        setKekenyanganValue(2);
        
        try {
            image = ImageIO.read(new File("././res/object/kacang.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
