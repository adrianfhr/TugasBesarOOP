package entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import object.*;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.Asset;
import object.BahanMakanan;
import object.OBJ_Jam;
import object.OBJ_Nasi;

public class Player extends Entity{
    //player attributes
    private String name, job;
    private String state;
    private int mood, health, hunger, money ;

    //tidur, kerja, makan
    public int jamTidur, jamTidakTidur, jamKerja, jamMules, jamTidakMules, jamOlahraga, jamMakan, jamMemasak, jamBerkunjung;


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

        solidArea.width = 42 ; // 16 sebelumnya
        solidArea.height = 42 ;
    
        getPlayerImage();
        setItems();

        //set atribut
        this.name = name;
        this.job = "Student";
        this.state = "Idle";
        setMood(80);
        this.health = 80;
        this.hunger = 80;
        this.money = 100;

        //set jam kerja default
        jamKerja = 30*2;
        jamOlahraga = 20*2;
        jamTidur = 4 * 60 * 2;
        jamTidakTidur = 10 * 60 * 2;
        jamMakan = 30 * 2;
        jamMemasak = 0;  //karena berubah-ubah tergantung masakannya
        jamBerkunjung = 30 * 2;
        jamMules = 10 * 2;
        jamTidakMules = 4 * 60 * 2;
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
        //jika dia melakukan aksi
        if(keyHandler.ePressed || gamePanel.isActiveAction){
            gamePanel.gameState = gamePanel.interactObjState;
            if(!gamePanel.obj[gamePanel.currentMap][targetIndex].getDescription().equals("idle") && isInteracting){
                gamePanel.isActiveAction = true;
                state = gamePanel.obj[gamePanel.currentMap][targetIndex].getDescription();

                if(jamTidur == 0 ){
                    interactOBJ();
                    jamTidur = 4 * 60 * 2;
                }

                if(jamTidakTidur == 0){
                    setHealth(getHealth() - 5);
                    setMood(getMood() - 5);
                    jamTidakTidur = 10 * 60 * 2;
                }

                if(jamKerja == 0){
                    interactOBJ();
                    jamKerja = 30 * 2;
                }

                if(jamMules == 0){
                    interactOBJ();
                    jamMules = 10 * 2;
                }

                if(jamTidakMules == 0){ //RIBEETTTTT TANDAINN
                    setHealth(getHealth() - 5);
                    setMood(getMood() - 5);
                    jamTidakMules = 4 * 60 * 2;     
                }

                if(jamOlahraga == 0){
                    //+5 ksehatan, -5 kekenyangan, +10 mood
                    setHealth(getHealth() + 5);
                    setMood(getMood() + 10);
                    setHunger(getHunger() - 5);
                    jamOlahraga = 20 * 2;
                }

                //jMASIH BINGUNG ISINYA
                if(jamMakan == 0){
                    //BELUM TAU ISINYA GMNA
                }

                // if(jamMemasak == 0){
                //     //belum tau isinya
                //     setMood(getMood() + 10);
                // }

                if(jamBerkunjung == 0){
                    setMood(getMood() + 10);
                    setHunger(getHunger() - 10);
                }

                if(jamBerkunjung == 0){
                    //MASIH BINGUNG
                }
                

            }
        } else{
            gamePanel.gameState = gamePanel.playState;
            this.state = "idle";
            
        }
    

        if(gamePanel.gameState == gamePanel.interactObjState && isInteracting && gamePanel.obj[gamePanel.currentMap][targetIndex].getDescription().equals("idle")){                
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

          //fungsi
        //interactOBJ();

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
    public void interactOBJ(){
        if((gamePanel.gameState == gamePanel.interactObjState && isInteracting)){
            gamePanel.obj[gamePanel.currentMap][this.targetIndex].interact(this);
        }
    }

    public void teleport(int x, int y, int map) {
		gamePanel.currentMap = map;
        worldX = x * gamePanel.tileSize;
		worldY = y* gamePanel.tileSize;
	}

    public void setItems(){
        getInventory().clear();
        setDefaultBahanMakanan();
        getInventory().add(getCurrentBahanMakanan());
        getInventory().add(new OBJ_Jam(gamePanel));
        getInventory().add(new OBJ_Nasi(gamePanel));
        getInventory().add(new OBJ_Nasi(gamePanel));
        getInventory().add(new OBJ_Nasi(gamePanel));
        getInventory().add(new OBJ_Nasi(gamePanel));
        getInventory().add(new OBJ_Nasi(gamePanel));
        getInventory().add(new OBJ_Nasi(gamePanel));
    }

    private void setDefaultBahanMakanan() {
        setCurrentBahanMakanan(new OBJ_Nasi(gamePanel));
    }

    public void selectItem() {
        int itemIndex = gamePanel.ui.getItemIndexFromSlot(gamePanel.ui.getPlayerSlotCol(), gamePanel.ui.getPlayerSlotRow());

        if (itemIndex < getInventory().size()) {
            Asset selectedItem = getInventory().get(itemIndex);

            if (selectedItem instanceof BahanMakanan) {
                setCurrentBahanMakanan((BahanMakanan) selectedItem);
                setKekenyangan(getKekenyangan());
            }
        }
    }
}
