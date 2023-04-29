package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Beef extends BahanMakanan{
    GamePanel gamePanel;
    public OBJ_Beef(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Beef");
        setDescription("[" + getName() + "]\nPotongan daging sapi segar.");
        setPrice(12);
        setKekenyanganValue(15);

        try {
            image = ImageIO.read(new File("././res/object/beef.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void use(){
        gamePanel.player[gamePanel.currentPlayer].setHunger(gamePanel.player[gamePanel.currentPlayer].getHunger() + getKekenyanganValue());
        gamePanel.player[gamePanel.currentPlayer].getInventory().remove(this);
        gamePanel.playSoundEffect(12);
        gamePanel.ui.addMessage("Hunger + " + getKekenyanganValue());
    }
}
