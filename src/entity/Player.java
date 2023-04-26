package entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sql.rowset.spi.SyncResolver;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
    //player attributes
    private String name, job, state;
    private int mood, health, hunger, money ;


    //player game system
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler, String name){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        
        setDefaultvalues();
        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 3; 
        solidArea.y = 4;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 8; // 16 sebelumnya
        solidArea.height = 8;
    
        getPlayerImage();

        //set atribut
        this.name = name;
        this.job = "Student";
        this.state = "Idle";
        this.mood = 80;
        this.health = 80;
        this.hunger = 80;
        this.money = 100;
       

    }

   
    public void setDefaultvalues(){ //position player in x and y
        worldX = gamePanel.tileSize * 34;
        worldY = gamePanel.tileSize * 35;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){

        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
    }

    public BufferedImage setup(String imageName){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File("././res/player/" + imageName + ".png"));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }

    public void update(){
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed){
            if (keyHandler.upPressed){
                direction = "up";
                
            } else if (keyHandler.downPressed){
                direction = "down";
                
            } else if (keyHandler.leftPressed){
                direction = "left";
            
            } else if (keyHandler.rightPressed){
                direction = "right";
            
            }
        }


            if(keyHandler.ePressed || gamePanel.isActiveAction){
                gamePanel.gameState = gamePanel.interactObjState;
            } else{
                gamePanel.gameState = gamePanel.playState;
            }
        

            if(gamePanel.gameState == gamePanel.interactObjState && isInteracting){                
                interactOBJ();
                keyHandler.ePressed = false;
            }
            
        

        


        //check tile collision
        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);

        //check object collision
        isInteracting = false;
        gamePanel.collisionChecker.checkObject(this, true);


        //IF COLLISION IS FALSE, THEN MOVE THE PLAYER
        if ((!gamePanel.isActiveAction)&& (collisionOn == false)  && (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed)){
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":   
                    worldX += speed;
                    break;
                default:
                    break;
            }
        }
        
        spriteCounter++;

        if (spriteCounter > 12){
            if (spriteNum == 1){
                spriteNum = 2;
            } else if (spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
          }

        
        
        

    }


    public void draw(Graphics2D g2d){
        BufferedImage image =  down1;
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

    // method untuk mengatur dan memengirim nilai atribut
    public String getName(){
        return name;
    }

    public void setJob(String job){
        this.job = job;
    }

    public String getJob(){
        return job;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public void setMood(int mood){
        this.mood = mood;
    }

    public int getMood(){
        return mood;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getHealth(){
        return health;
    }

    public void setHunger(int hunger){
        this.hunger = hunger;
    }

    public int getHunger(){
        return hunger;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public int getMoney(){
        return money;
    }

    //
    public synchronized void interactOBJ(){
        gamePanel.obj[gamePanel.currentMap][this.targetIndex].interact(this);
        gamePanel.gameState = gamePanel.playState;
    }

    public void teleport(int x, int y, int map) {
		gamePanel.currentMap = map;
        worldX = x * gamePanel.tileSize;
		worldY = y* gamePanel.tileSize;
	}

}
