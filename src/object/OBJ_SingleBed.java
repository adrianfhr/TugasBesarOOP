package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Player;
import main.GamePanel;

public class OBJ_SingleBed extends SuperObject{
    GamePanel gamePanel;

    public OBJ_SingleBed(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 4;
        this.width = 1;
        setDescription("tidur");
        setName(" Single Bed");
        
        try {
            image = ImageIO.read(new File("././res/object/single_bed.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
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
        if (gamePanel.player.jamTidur == 0 && gamePanel.player.getHealth() <= 80) {
            gamePanel.player.setHealth(gamePanel.player.getHealth() + 20);
        } else if (gamePanel.player.jamTidur == 0 && gamePanel.player.getHealth() <= 90){
            gamePanel.player.setHealth(gamePanel.player.getHealth() + 10);
        } else {
            gamePanel.player.setHealth(gamePanel.player.getHealth() + 0);
        }
        if (gamePanel.player.jamTidur == 0 && gamePanel.player.getMood() <= 70) {
            gamePanel.player.setMood(gamePanel.player.getMood() + 30);
        } else if (gamePanel.player.jamTidur == 0 && gamePanel.player.getMood() <= 80) {
            gamePanel.player.setMood(gamePanel.player.getMood() + 20);
        } else if (gamePanel.player.jamTidur == 0 && gamePanel.player.getMood() <= 90) {
            gamePanel.player.setMood(gamePanel.player.getMood() + 10);
        } else {
            gamePanel.player.setMood(gamePanel.player.getMood() + 0);
        }
        
    }

}
