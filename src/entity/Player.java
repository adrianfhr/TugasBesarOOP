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

public class Player extends Entity{
    //player attributes
    private String name, job;
    private String state;
    private int id, mood, health, hunger, money ;

    //tidur, kerja, makan
    public int jamTidur, jamTidakTidur, jamKerja, jamMules, jamTidakMules, jamOlahraga, jamMakan, jamMemasak, jamBerkunjung;


    //player game system
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler, String name, int id){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.id = id;

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
        setDagangan();

        //set atribut
        this.name = name;
        this.job = "Student";
        this.state = "Idle";
        this.mood = 80;
        this.health = 80;
        this.hunger = 80;
        this.money = 100;

        //set jam kerja default
        jamKerja = 30*2;
        jamOlahraga = 20*2;
        jamTidur = 4 * 60 * 2;
        jamTidakTidur = 10 * 60 * 2;
        jamMakan = 30 * 2;
        jamMemasak = 30;  //karena berubah-ubah tergantung masakannya
        jamBerkunjung = 30 * 2;
        jamMules = 10 * 2;
        jamTidakMules = 4 * 60 * 2;
    }

   
    public void setDefaultvalues(){ //position player in x and y
        worldX = gamePanel.tileSize * 34;
        worldY = gamePanel.tileSize * 23;
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
                state = gamePanel.obj[gamePanel.currentMap][targetIndex].getDescription();
                if(state.equals("Tidur")) gamePanel.isActiveAction = true; 
                if(state.equals("Memasak")) gamePanel.setGameState(gamePanel.masakState);
                

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

        //check npc collision
        int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);


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

    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
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
        getInventory().add(new OBJ_Nasi(gamePanel));
        getInventory().add(new OBJ_Ayam(gamePanel));
        getInventory().add(new OBJ_Bayam(gamePanel));
        getInventory().add(new OBJ_Wortel(gamePanel));
        getInventory().add(new OBJ_Susu(gamePanel));
        getInventory().add(new OBJ_Kentang(gamePanel));
        getInventory().add(new OBJ_Kentang(gamePanel));
        getInventory().add(new OBJ_Beef(gamePanel));
    }

    private void setDefaultBahanMakanan() {
        setCurrentBahanMakanan(new OBJ_Nasi(gamePanel));
    }

    public void selectItem() {
        int itemIndex = gamePanel.ui.getItemIndexFromSlot(gamePanel.ui.getPlayerSlotCol(), gamePanel.ui.getPlayerSlotRow());

        if (itemIndex < getInventory().size()) {
            Asset selectedItem = getInventory().get(itemIndex);

            if (selectedItem instanceof BahanMakanan) {
                selectedItem.use();
            } else if (selectedItem instanceof Makanan) {
                selectedItem.use();
            } 
        }
    }

    public void selectMenu() {
        boolean hasAyam, hasNasi, hasKacang, hasKentang, hasSusu, hasBayam, hasWortel, hasBeef;
        hasAyam = hasNasi = hasKacang = hasKentang = hasSusu = hasBayam = hasWortel = hasBeef = false;
        int itemIndex = gamePanel.ui.getItemIndexFromSlot(gamePanel.ui.getKomporSlotCol(), gamePanel.ui.getKomporSlotRow());

        if (itemIndex < gamePanel.obj[1][2].getMenu().size()) {
            Asset selectedItem = gamePanel.obj[1][2].getMenu().get(itemIndex);
                for (Asset recipe : gamePanel.player[gamePanel.currentPlayer].getInventory()){
                    if(recipe instanceof OBJ_Ayam) hasAyam = true;
                    if(recipe instanceof OBJ_Nasi) hasNasi = true;
                    if(recipe instanceof OBJ_Kacang) hasKacang = true;
                    if(recipe instanceof OBJ_Kentang) hasKentang = true;
                    if(recipe instanceof OBJ_Susu) hasSusu = true;
                    if(recipe instanceof OBJ_Bayam) hasBayam = true;
                    if(recipe instanceof OBJ_Wortel) hasWortel = true;
                    if(recipe instanceof OBJ_Beef) hasBeef = true;
                }

            String namaMakanan = selectedItem.getName();

            switch (namaMakanan) {
                case "Nasi Ayam":
                    if(hasAyam && hasNasi){
                        gamePanel.isActiveAction = true;
                        gamePanel.player[gamePanel.currentPlayer].useInventory("Ayam");
                        gamePanel.player[gamePanel.currentPlayer].useInventory("Nasi");
                        gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_NasiAyam(gamePanel));
                    }else{
                        gamePanel.isActiveAction = false;
    

                        
                    }
                    break;
                case "Bistik":
                    if(hasBeef && hasKentang){
                        gamePanel.isActiveAction = true;
                        gamePanel.player[gamePanel.currentPlayer].useInventory("Kentang");
                        gamePanel.player[gamePanel.currentPlayer].useInventory("Beef");
                        gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_Bistik(gamePanel));
                        break;
                    }else{
                        gamePanel.isActiveAction = false;
                
                    }
                    break;
                case "Nasi Kari":
                    if(hasNasi && hasKentang && hasWortel && hasBeef){
                        gamePanel.isActiveAction = true;
                        gamePanel.player[gamePanel.currentPlayer].useInventory("Nasi");
                        gamePanel.player[gamePanel.currentPlayer].useInventory("Kentang");
                        gamePanel.player[gamePanel.currentPlayer].useInventory("Wortel");
                        gamePanel.player[gamePanel.currentPlayer].useInventory("Beef");
                        gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_NasiKari(gamePanel));
                        
                    }else{
                        gamePanel.isActiveAction = false;
                
                    }
                    break;
                case "Susu Kacang":
                    if(hasSusu && hasKacang){
                        gamePanel.isActiveAction = true;
                        gamePanel.player[gamePanel.currentPlayer].useInventory("Susu");
                        gamePanel.player[gamePanel.currentPlayer].useInventory("Kacang");
                        gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_SusuKacang(gamePanel));
                        
                    }else{
                        gamePanel.isActiveAction = false;
                
                    }
                    break;
                case "Tumis Sayuran":
                    if(hasBayam && hasWortel){
                        gamePanel.isActiveAction = true;
                        gamePanel.player[gamePanel.currentPlayer].useInventory("Bayam");
                        gamePanel.player[gamePanel.currentPlayer].useInventory("Wortel");  
                        gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_TumisSayur(gamePanel));
                        
                    }else{
                        gamePanel.isActiveAction = false;
                
                    }
                    break;
                default:
                    break;
            }
                
                if (gamePanel.player[gamePanel.currentPlayer].getInventory().size() == gamePanel.player[gamePanel.currentPlayer].getMaxInventorySize()) {
                    gamePanel.setGameState(gamePanel.playState);
                }
            }
        }

        public void setDagangan(){
            getDagangan().add(new OBJ_Nasi(gamePanel));
            getDagangan().add(new OBJ_Ayam(gamePanel));
            getDagangan().add(new OBJ_Bayam(gamePanel));
            getDagangan().add(new OBJ_Wortel(gamePanel));
            getDagangan().add(new OBJ_Susu(gamePanel));
            getDagangan().add(new OBJ_Kentang(gamePanel));
            getDagangan().add(new OBJ_Beef(gamePanel));
            getDagangan().add(new OBJ_Kacang(gamePanel));
            getDagangan().add(new OBJ_SingleBed(gamePanel));
            getDagangan().add(new OBJ_QueenBed(gamePanel));
            getDagangan().add(new OBJ_KingBed(gamePanel));
            getDagangan().add(new OBJ_Toilet(gamePanel));
            getDagangan().add(new OBJ_KomporGas(gamePanel));
            getDagangan().add(new OBJ_KomporListrik(gamePanel));
            getDagangan().add(new OBJ_MejaKursi(gamePanel));
            getDagangan().add(new OBJ_Jam(gamePanel));
        }
}
