package object;

import java.awt.Container;
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
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void use(){
        gamePanel.player[gamePanel.currentPlayer].setHunger(gamePanel.player[gamePanel.currentPlayer].getHunger() + getKekenyanganValue());
        gamePanel.player[gamePanel.currentPlayer].getInventory().remove(this);
        gamePanel.playSoundEffect(12);
    }
}