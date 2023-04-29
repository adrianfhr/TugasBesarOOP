package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class NPC_Wife extends Entity {
    GamePanel gamePanel;
    private String[] dialogues = new String[20];
    private int dialogueIndex;


    public NPC_Wife(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        solidArea = new Rectangle();
        solidArea.x = 3; 
        solidArea.y = 4;
    
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    
        solidArea.width = 42 ; // 16 sebelumnya
        solidArea.height = 42 ;

        direction = "down";
        speed = 1;
        gamePanel.setGameState(gamePanel.dialogueState);
        getNPCImage();
        setDialogue();
    }

    public void getNPCImage(){

        up1 = setup("wife_up_1");
        up2 = setup("wife_up_2");
        down1 = setup("wife_down_1");
        down2 = setup("wife_down_2");
        left1 = setup("wife_left_1");
        left2 = setup("wife_left_2");
        right1 = setup("wife_right_1");
        right2 = setup("wife_right_2");
    }

    public BufferedImage setup(String imageName){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File("././res/wife/" + imageName + ".png"));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }

    public void draw(Graphics2D g2d) {
        BufferedImage image =  down1;
        
        int screenX = worldX - gamePanel.player[gamePanel.currentPlayer].worldX + gamePanel.player[gamePanel.currentPlayer].screenX;
        int screenY = worldY - gamePanel.player[gamePanel.currentPlayer].worldY + gamePanel.player[gamePanel.currentPlayer].screenY;
        
        if (worldX + gamePanel.tileSize > gamePanel.player[gamePanel.currentPlayer].worldX - gamePanel.player[gamePanel.currentPlayer].screenX && 
        worldX - gamePanel.tileSize < gamePanel.player[gamePanel.currentPlayer].worldX + gamePanel.player[gamePanel.currentPlayer].screenX && 
        worldY + gamePanel.tileSize > gamePanel.player[gamePanel.currentPlayer].worldY - gamePanel.player[gamePanel.currentPlayer].screenY && 
        worldY - gamePanel.tileSize < gamePanel.player[gamePanel.currentPlayer].worldY + gamePanel.player[gamePanel.currentPlayer].screenY) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1){
                        image = up1;
                    } else if (spriteNum == 2){
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1){
                        image = down1;
                    } else if (spriteNum == 2){
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1){
                        image = left1;
                    } else if (spriteNum == 2){
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1){
                        image = right1;
                    } else if (spriteNum == 2){
                        image = right2;
                    }
                    break;
                default:
                    image = down1;
                    break;
            }
            g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

    public int actionLockCounter = 0;

    public void setAction() {

        actionLockCounter++;
        
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100)+1; //acak 1-100

            if (i <= 25) {
                direction = "up";
            }

            if (i > 25 && i <= 50) {
                direction = "down";
            }

            if (i > 50 && i <= 75) {
                direction = "left";
            }

            if (i > 75 && i <= 100) {
                direction = "rigt";
            }

            actionLockCounter = 0;
        }

    
    }

    public void update() {
        setAction();

        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkPlayer(this);

        if (collisionOn == false) {
            switch(direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldY += speed; break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNum == 1) {
                spriteNum = 2;
            }

            else if (spriteNum == 2) {
                spriteNum = 1;
            }

            spriteCounter = 0;
        }
    }

    public void setDialogue() {
        getDialogues()[0] = "Hello, lad.";
        getDialogues()[1] = "So you've come to this island to find \nthe treasure?";
        getDialogues()[2] = "I used to be a great wizard, but now... \nI'm a bit too old for adventuring.";
        getDialogues()[3] = "Well, good luck to you lad.";
    }

    public void speak() {
        if (getDialogues()[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
            gamePanel.ui.setCurrentDialogue(getDialogues()[dialogueIndex]);
            dialogueIndex++;

            switch (gamePanel.player[gamePanel.currentPlayer].direction) {
                case "up" -> direction = "down";
                case "down" -> direction = "up";
                case "left" -> direction = "right";
                case "right" -> direction = "left";
            }
    }
}
