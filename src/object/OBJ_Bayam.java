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
        setDescription("[" + getName() + "]\nKesukaan si Popeye.");
        setHarga(3);
        setKekenyanganValue(2);

        try {
            image = ImageIO.read(new File("././res/object/bayam.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void use(){
        gamePanel.player.setHunger(gamePanel.player.getHunger() + getKekenyanganValue());
        gamePanel.player.getInventory().remove(this);
        gamePanel.playSoundEffect(12);
    }
}
