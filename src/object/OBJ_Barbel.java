package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Barbel extends Barang{
    GamePanel gamePanel;

    public OBJ_Barbel(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 1;
        this.width = 1;
        setDescription("Supaya kekar seperti saya");
        setStateOBJ("Olahraga");
        setName("Barbel");
        setPrice(50);
        
        try {
            image = ImageIO.read(new File("././res/object/barbel.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        } catch (IOException e) {
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

    public void interact(Player player ){
        if (gamePanel.player[gamePanel.currentPlayer].jamTidur == 0 && gamePanel.player[gamePanel.currentPlayer].getHealth() <= 80) {
            gamePanel.player[gamePanel.currentPlayer].setHealth(gamePanel.player[gamePanel.currentPlayer].getHealth() + 20);
            gamePanel.ui.addMessage("Health + 20");
        } else if (gamePanel.player[gamePanel.currentPlayer].jamTidur == 0 && gamePanel.player[gamePanel.currentPlayer].getHealth() <= 90){
            gamePanel.player[gamePanel.currentPlayer].setHealth(gamePanel.player[gamePanel.currentPlayer].getHealth() + 10);
            gamePanel.ui.addMessage("Health + 10");
        } else {
            gamePanel.player[gamePanel.currentPlayer].setHealth(gamePanel.player[gamePanel.currentPlayer].getHealth() + 0);
        }
        if (gamePanel.player[gamePanel.currentPlayer].jamTidur == 0 && gamePanel.player[gamePanel.currentPlayer].getMood() <= 70) {
            gamePanel.player[gamePanel.currentPlayer].setMood(gamePanel.player[gamePanel.currentPlayer].getMood() + 30);
            gamePanel.ui.addMessage("Mood + 30");
        } else if (gamePanel.player[gamePanel.currentPlayer].jamTidur == 0 && gamePanel.player[gamePanel.currentPlayer].getMood() <= 80) {
            gamePanel.player[gamePanel.currentPlayer].setMood(gamePanel.player[gamePanel.currentPlayer].getMood() + 20);
            gamePanel.ui.addMessage("Mood + 20");
        } else if (gamePanel.player[gamePanel.currentPlayer].jamTidur == 0 && gamePanel.player[gamePanel.currentPlayer].getMood() <= 90) {
            gamePanel.player[gamePanel.currentPlayer].setMood(gamePanel.player[gamePanel.currentPlayer].getMood() + 10);
            gamePanel.ui.addMessage("Mood + 10");
        } else {
            gamePanel.player[gamePanel.currentPlayer].setMood(gamePanel.player[gamePanel.currentPlayer].getMood() + 0);
        }
        
    }

}
