package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Toilet extends Barang{
    GamePanel gamePanel;

    public OBJ_Toilet(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 1;
        this.width = 1;
        setName("Toilet");
        setStateOBJ("buang air");
        setPrice(50);
        setDescription("[" + getName() + "]\nSelamat menikmati.\n\nHarga : " + getPrice());

        try{
            image = ImageIO.read(new File("././res/object/toilet.png"));
            image_orang = ImageIO.read(new File("././res/object/toilet_orang.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        this.collision = false;
        setsolidArea();
    }

    public void setsolidArea(){
        solidArea.x = worldX;
        solidArea.y = worldY;
        solidArea.width = gamePanel.tileSize * width;
        solidArea.height = gamePanel.tileSize * height;
    }

    @Override
    public void interact(Player player) {
        gamePanel.player[gamePanel.currentPlayer].setHunger(gamePanel.player[gamePanel.currentPlayer].getHunger() - 20);
        gamePanel.ui.addMessage("Hunger - 20");
        gamePanel.playSoundEffect(12);
        if (gamePanel.player[gamePanel.currentPlayer].jamMules == 0 && gamePanel.player[gamePanel.currentPlayer].getMood() <= 90) {
            gamePanel.player[gamePanel.currentPlayer].setMood(gamePanel.player[gamePanel.currentPlayer].getMood() + 10);
            gamePanel.ui.addMessage("Mood +  " + 10);
        } else if(gamePanel.player[gamePanel.currentPlayer].jamMules == 0 && gamePanel.player[gamePanel.currentPlayer].getMood() > 90){
            gamePanel.ui.addMessage("Mood +  " + (100 - gamePanel.player[gamePanel.currentPlayer].getMood()));
            gamePanel.player[gamePanel.currentPlayer].setMood(100);
        }
    }
}
