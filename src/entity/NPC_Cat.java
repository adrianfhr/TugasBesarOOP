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
import object.OBJ_Whiskas;
import object.SuperObject;

public class NPC_Cat extends Entity{
    GamePanel gamePanel;
    private int dialogueIndex;
    public NPC_Cat(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        solidArea = new Rectangle();
        solidArea.x = 3; 
        solidArea.y = 4;
    
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    
        solidArea.width = 42 ; // 16 sebelumnya
        solidArea.height = 42 ;

        direction = "left4";
        speed = 1;
        setStateNPC("cat");
        gamePanel.setGameState(gamePanel.dialogueState);
        getNPCImage();
        setDialogue();
    }

    public void getNPCImage(){

        up1 = setup("l0_cat_13");
        up2 = setup("l0_cat_12");
        up3 = setup("l0_cat_11");
        
        down1 = setup("l0_cat_08");
        down2 = setup("l0_cat_09");
        down3 = setup("l0_cat_10");
        
        left1 = setup("l0_cat_04");
        left2 = setup("l0_cat_03");
        left3 = setup("l0_cat_02");
        left4 = setup("l0_cat_01");
        
        right1 = setup("l0_cat_05");
        right2 = setup("l0_cat_06");
        right3 = setup("l0_cat_07");
    }

    public BufferedImage setup(String imageName){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File("././res/cat/" + imageName + ".png"));
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
                    } else {
                        image = up3;
                    }
                    break;
                case "down":
                    if (spriteNum == 1){
                        image = down1;
                    } else if (spriteNum == 2){
                        image = down2;
                    } else {
                        image = down3;
                    }
                    break;
                case "left":
                    if (spriteNum == 1){
                        image = left1;
                    } else if (spriteNum == 2){
                        image = left2;
                    } else if (spriteNum == 3){
                        image = left3;
                    } else {
                        image = left4;
                    }
                    break;
                case "right":
                    if (spriteNum == 1){
                        image = right1;
                    } else if (spriteNum == 2){
                        image = right2;
                    } else {
                        image = right3;
                    }
                    break;
                default:
                    image = left4;
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
                spriteNum = 3;
            } else {
                spriteNum = 1;
            }

            spriteCounter = 0;
        }
    }

    public void setDialogue() {
        getDialogues()[0] = "Meow meow meow...";
        getDialogues()[1] = "Kamu tidak punya Whiskas!";
    }

    public void speak() {
        gamePanel.setGameState(gamePanel.dialogueState);
        boolean hasWhiskas = false;
        int itemIndex = gamePanel.ui.getItemIndexFromSlot(gamePanel.ui.getPlayerSlotCol(), gamePanel.ui.getPlayerSlotRow());
        if (itemIndex < gamePanel.player[gamePanel.currentPlayer].getInventory().size()) {
                for (SuperObject recipe : gamePanel.player[gamePanel.currentPlayer].getInventory()){
                    if(recipe instanceof OBJ_Whiskas) hasWhiskas = true;
                }
                if (hasWhiskas == true){
                    gamePanel.player[gamePanel.currentPlayer].getInventory().remove(itemIndex);
                    gamePanel.player[gamePanel.currentPlayer].setMood(gamePanel.player[gamePanel.currentPlayer].getMood() + 5);
                    gamePanel.ui.addMessage("Mood + 5");
                    gamePanel.playSoundEffect(13);
                    dialogueIndex = 0;
                } else {
                        gamePanel.setGameState(gamePanel.dialogueState);
                        dialogueIndex = 1;
                }
        }
        
        gamePanel.ui.setCurrentDialogue(getDialogues()[dialogueIndex]);

        switch (gamePanel.player[gamePanel.currentPlayer].direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }

    
    }
}
