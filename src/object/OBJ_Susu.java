package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
public class OBJ_Susu extends BahanMakanan {
    GamePanel gamePanel;
    public OBJ_Susu(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Susu");
        setDescription("[" + getName() + "]\nDari sapi, bukan yang lain.");
        setHarga(2);
        setKekenyanganValue(1);

        try {
            image = ImageIO.read(new File("././res/object/susu.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void use(){
        gamePanel.player[gamePanel.currentPlayer].setHunger(gamePanel.player[gamePanel.currentPlayer].getHunger() + getKekenyanganValue());
        gamePanel.player[gamePanel.currentPlayer].getInventory().remove(this);
        gamePanel.playSoundEffect(12);
    }
}
