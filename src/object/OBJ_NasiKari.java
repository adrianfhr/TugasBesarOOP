package object;

import java.awt.Container;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_NasiKari extends Makanan{
    GamePanel gamePanel;
    public OBJ_NasiKari(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Nasi Kari");
        setDescription("[" + getName() + "]\nResep : Nasi, Kentang, Wortel, Sapi");
        setKekenyanganValue(30);

        try{
            image = ImageIO.read(new File("././res/object/nasi_kari.png"));
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