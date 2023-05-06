package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_SingleBed extends Barang{
    GamePanel gamePanel;

    public OBJ_SingleBed(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 4;
        this.width = 1;
        setStateOBJ("Tidur");
        setName("Single Bed");
        setPrice(50);
        setDescription("[Single Bed]\nUntuk tidur dan ditiduri.\n\nHarga : " + getPrice() );
        
        try {
            image = ImageIO.read(new File("././res/object/single_bed.png"));
            image_orang = ImageIO.read(new File("././res/object/tidur2.png"));
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
            gamePanel.player[gamePanel.currentPlayer].setHealth(gamePanel.player[gamePanel.currentPlayer].getHealth() + 20);
            gamePanel.ui.addMessage("Health + 20");
            if (gamePanel.player[gamePanel.currentPlayer].getHealth() > 100){
                gamePanel.player[gamePanel.currentPlayer].setHealth(100);
            }
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
            gamePanel.player[gamePanel.currentPlayer].setMood(gamePanel.player[gamePanel.currentPlayer].getMood() + 30);
            gamePanel.ui.addMessage("Mood + 30");
            if (gamePanel.player[gamePanel.currentPlayer].getMood() > 100){
                gamePanel.player[gamePanel.currentPlayer].setMood(100);
            }
        }
        
    }

}
