package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Bayam extends BahanMakanan{

    GamePanel gamePanel;
    public OBJ_Bayam(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Bayam");
        setPrice(3);
        setDescription("[" + getName() + "]\nKesukaan si Popeye.\n\n" + "Harga : " + getPrice());
        setKekenyanganValue(2);

        try {
            image = ImageIO.read(new File("././res/object/bayam.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
