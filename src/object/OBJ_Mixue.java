package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Mixue extends Barang{
    GamePanel gamePanel;
    int pemilik;

    public OBJ_Mixue(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 3;
        this.width = 3;
        setName("Mixue");
        setStateOBJ("Mixue");

        try {
            image = ImageIO.read(new File("././res/Mixue/gd_mixue2.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
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

    public void interact(Player player ){
        gamePanel.setGameState(gamePanel.dialogueState);
        gamePanel.ui.setCurrentDialogue("Selamat Menikmati!");
        gamePanel.playSoundEffect(14);
        gamePanel.player[gamePanel.currentPlayer].setMoney(gamePanel.player[gamePanel.currentPlayer].getMoney() - 2);
        gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_Eskrim(gamePanel));
        gamePanel.ui.addMessage("Money - 2");
    }


}
