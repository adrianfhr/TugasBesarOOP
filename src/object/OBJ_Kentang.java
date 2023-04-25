package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
public class OBJ_Kentang extends BahanMakanan{
    GamePanel gamePanel;
    public OBJ_Kentang(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Kentang");
        setDescription("[" + getName() + "]\nPengganti nasi.");
        setHarga(3);
        setKekenyanganValue(4);

        try {
            image = ImageIO.read(new File("././res/object/kentang.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}