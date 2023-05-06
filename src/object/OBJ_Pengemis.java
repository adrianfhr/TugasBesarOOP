package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Pengemis extends SuperObject{
    GamePanel gamePanel;

    public OBJ_Pengemis(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 1;
        this.width = 1;
        setName("Pengemis");
        
        try {
            image = ImageIO.read(new File("././res/object/pengemis.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.collision = true;
        setsolidArea();
    }

    public void setsolidArea(){
        solidArea.x = worldX;
        solidArea.y = worldY;
        solidArea.width = gamePanel.tileSize * width;
        solidArea.height = gamePanel.tileSize * height;
    }

    public void interact(Player player){
        if (gamePanel.player[gamePanel.currentPlayer].getMoney() >= 2 && gamePanel.player[gamePanel.currentPlayer].getMood() <= 90) {
            gamePanel.player[gamePanel.currentPlayer].setMoney(gamePanel.player[gamePanel.currentPlayer].getMoney() - 2);
            gamePanel.player[gamePanel.currentPlayer].setMood(gamePanel.player[gamePanel.currentPlayer].getMood() + 10);
            gamePanel.ui.addMessage("Mood + 10");
            gamePanel.playSoundEffect(12);
        } else if (gamePanel.player[gamePanel.currentPlayer].getMoney() >= 2 && gamePanel.player[gamePanel.currentPlayer].getMood() < 90) {
            gamePanel.player[gamePanel.currentPlayer].setMoney(gamePanel.player[gamePanel.currentPlayer].getMoney() - 2);
            gamePanel.player[gamePanel.currentPlayer].setMood(gamePanel.player[gamePanel.currentPlayer].getMood() + 10);
            gamePanel.ui.addMessage("Mood + 10");
            gamePanel.playSoundEffect(12);
        } else if (gamePanel.player[gamePanel.currentPlayer].getMoney() >= 2 && gamePanel.player[gamePanel.currentPlayer].getMood() == 100) {
            gamePanel.player[gamePanel.currentPlayer].setMoney(gamePanel.player[gamePanel.currentPlayer].getMoney() - 2);
            gamePanel.player[gamePanel.currentPlayer].setMood(gamePanel.player[gamePanel.currentPlayer].getMood() + 0);
        }
        
        else {
            gamePanel.player[gamePanel.currentPlayer].setMood(gamePanel.player[gamePanel.currentPlayer].getMood() + 0);
        }
    }
}
