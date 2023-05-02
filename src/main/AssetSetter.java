package main;

import entity.NPC_Cat;
import entity.NPC_Wife;
import javax.swing.JOptionPane;

import object.OBJ_Car;
import object.OBJ_Jam;
import object.OBJ_KingBed;
import object.OBJ_KomporGas;
import object.OBJ_KomporListrik;
import object.OBJ_Masjid;
import object.OBJ_MejaKursi;
import object.OBJ_Mixue;
import object.OBJ_Pengemis;
import object.OBJ_Pintu;
import object.OBJ_QueenBed;
import object.OBJ_Rumah;
import object.OBJ_SingleBed;
import object.OBJ_TV;
import object.OBJ_Toilet;
import object.SuperObject;

public class AssetSetter {
    GamePanel gamePanel;
    String direction;
    int index;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        direction = "";
        index = 0;
    }

    public void setObject(){

        gamePanel.obj[1][0] = new OBJ_SingleBed(gamePanel);
        gamePanel.obj[1][0].worldX = 48 * gamePanel.tileSize;
        gamePanel.obj[1][0].worldY = 48 * gamePanel.tileSize;
        for (int i = 48; i < 48 + gamePanel.obj[1][0].width; i++) {
            for (int j = 48; j < 48 + gamePanel.obj[1][0].height; j++) {
                gamePanel.tileManager.mapTileValidation[1][i][j] = true;
            }
        }


        gamePanel.obj[1][1] = new OBJ_Pintu(gamePanel);
        gamePanel.obj[1][1].worldX = 53 * gamePanel.tileSize;
        gamePanel.obj[1][1].worldY = 53 * gamePanel.tileSize;
        for (int i = 53; i < 53 + gamePanel.obj[1][1].width; i++) {
            for (int j = 53; j < 53 + gamePanel.obj[1][1].height; j++) {
                gamePanel.tileManager.mapTileValidation[1][i][j] = true;
            }
        }

        gamePanel.obj[1][2] = new OBJ_KomporGas(gamePanel);
        gamePanel.obj[1][2].worldX = 52 * gamePanel.tileSize;
        gamePanel.obj[1][2].worldY = 48 * gamePanel.tileSize;
        for (int i = 52; i < 52 + gamePanel.obj[1][2].width; i++) {
            for (int j = 48; j < 48 + gamePanel.obj[1][2].height; j++) {
                gamePanel.tileManager.mapTileValidation[1][i][j] = true;
            }
        }

        gamePanel.obj[1][3] = new OBJ_KomporListrik(gamePanel);
        gamePanel.obj[1][3].worldX = 49 * gamePanel.tileSize;
        gamePanel.obj[1][3].worldY = 48 * gamePanel.tileSize;
        for (int i = 49; i < 49 + gamePanel.obj[1][3].width; i++) {
            for (int j = 48; j < 48 + gamePanel.obj[1][3].height; j++) {
                gamePanel.tileManager.mapTileValidation[1][i][j] = true;
            }
        }

        gamePanel.obj[1][4] = new OBJ_Toilet(gamePanel);
        gamePanel.obj[1][4].worldX = 48 * gamePanel.tileSize;
        gamePanel.obj[1][4].worldY = 52 * gamePanel.tileSize;
        for (int i = 48; i < 48 + gamePanel.obj[1][4].width; i++) {
            for (int j = 52; j < 52 + gamePanel.obj[1][4].height; j++) {
                gamePanel.tileManager.mapTileValidation[1][i][j] = true;
            }
        }

        gamePanel.obj[1][5] = new OBJ_TV(gamePanel);
        gamePanel.obj[1][5].worldX = 50 * gamePanel.tileSize;
        gamePanel.obj[1][5].worldY = 52 * gamePanel.tileSize;
        for (int i = 50; i < 50 + gamePanel.obj[1][5].width; i++) {
            for (int j = 52; j < 52 + gamePanel.obj[1][5].height; j++) {
                gamePanel.tileManager.mapTileValidation[1][i][j] = true;
            }
        }

        gamePanel.obj[0][0] = new OBJ_Rumah(gamePanel, 1);
        gamePanel.obj[0][0].worldX = 41 * gamePanel.tileSize;
        gamePanel.obj[0][0].worldY = 19 * gamePanel.tileSize;
        for (int i = 41; i < 41 + gamePanel.obj[0][0].width; i++) {
            for (int j = 19; j < 19 + gamePanel.obj[0][0].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][1] = new OBJ_Masjid(gamePanel);
        gamePanel.obj[0][1].worldX = 30 * gamePanel.tileSize;
        gamePanel.obj[0][1].worldY = 5 * gamePanel.tileSize;
        for (int i = 30; i < 30 + gamePanel.obj[0][1].width; i++) {
            for (int j = 5; j < 5 + gamePanel.obj[0][1].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        
        gamePanel.obj[0][2] = new OBJ_Pengemis(gamePanel);
        gamePanel.obj[0][2].worldX = 33 * gamePanel.tileSize;
        gamePanel.obj[0][2].worldY = 33 * gamePanel.tileSize;
        for (int i = 33; i < 33 + gamePanel.obj[0][2].width; i++) {
            for (int j = 33; j < 33 + gamePanel.obj[0][2].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][3] = new OBJ_Mixue(gamePanel);
        gamePanel.obj[0][3].worldX = 51 * gamePanel.tileSize;
        gamePanel.obj[0][3].worldY = 19 * gamePanel.tileSize;
        for (int i = 51; i < 51 + gamePanel.obj[0][3].width; i++) {
            for (int j = 19; j < 19 + gamePanel.obj[0][3].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][4] = new OBJ_Car(gamePanel);
        gamePanel.obj[0][4].worldX = 42 * gamePanel.tileSize;
        gamePanel.obj[0][4].worldY = 24 * gamePanel.tileSize;
        for (int i = 42; i < 42 + gamePanel.obj[0][4].width; i++) {
            for (int j = 24; j < 24 + gamePanel.obj[0][4].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

    }

    public void makeOBJ(String objek, int map, int player){
        index = 0;

        while (gamePanel.obj[map][index] != null) {
            index++;
            if (index == gamePanel.obj[map].length) {
                gamePanel.ui.setCurrentDialogue("Array penuh, tidak bisa menambahkan objek baru!");
                return;
            }
        }

        String x,y;
        int worldX, worldY;

        switch (objek) {
            case "Rumah":
                gamePanel.obj[map][index] = new OBJ_Rumah(gamePanel,gamePanel.player[player].getId() );
                gamePanel.obj[map][index].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][index].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
            
                break;
            case "Pintu":
                gamePanel.obj[map][index] = new OBJ_Pintu(gamePanel);
                gamePanel.obj[map][index].worldX = 53 * gamePanel.tileSize;
                gamePanel.obj[map][index].worldY = 53 * gamePanel.tileSize;
                break;

            case "Single Bed":
                gamePanel.obj[map][index] = new OBJ_SingleBed(gamePanel);
                gamePanel.obj[map][index].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][index].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                
                
                break;

            case "Jam":
    
                gamePanel.obj[map][index] = new OBJ_Jam(gamePanel);
                gamePanel.obj[map][index].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][index].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
               
                break;

            case "King Bed":
                gamePanel.obj[map][index] = new OBJ_KingBed(gamePanel);
                gamePanel.obj[map][index].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][index].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                
                break;
            
            case "Kompor Gas":
                gamePanel.obj[map][index] = new OBJ_KomporGas(gamePanel);
                gamePanel.obj[map][index].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][index].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                
                break;

            case "Kompor Listrik":
                gamePanel.obj[map][index] = new OBJ_KomporListrik(gamePanel);
                gamePanel.obj[map][index].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][index].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                
                
                break;
            
            case "Meja Kursi":
                gamePanel.obj[map][index] = new OBJ_MejaKursi(gamePanel);
                gamePanel.obj[map][index].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][index].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
               
                break;

            case "Queen Bed":
                gamePanel.obj[map][index] = new OBJ_QueenBed(gamePanel);
                gamePanel.obj[map][index].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][index].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                
                break;
            
            case "Toilet":
                gamePanel.obj[map][index] = new OBJ_Toilet(gamePanel);
                gamePanel.obj[map][index].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][index].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                
                break;
            
            case "TV":
                gamePanel.obj[map][index] = new OBJ_TV(gamePanel);
                gamePanel.obj[map][index].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][index].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                
                break;

            default:
                break;
        }

        

        
        
    }

    public void setNPC() {
        gamePanel.npc[0] = new NPC_Wife(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize*28;
        gamePanel.npc[0].worldY = gamePanel.tileSize*30;

        gamePanel.cat[1] = new NPC_Cat(gamePanel);
        gamePanel.cat[1].worldX = gamePanel.tileSize*35;
        gamePanel.cat[1].worldY = gamePanel.tileSize*35;
    }

    public boolean setValidMap(SuperObject o, int map) {
        boolean valid = false;
        int tempX = o.worldX / gamePanel.tileSize;
        int tempY = o.worldY / gamePanel.tileSize;

        while(!valid && tempX < o.worldX/ gamePanel.tileSize + o.width) {
            while(!valid && tempY < o.worldY / gamePanel.tileSize + o.height) {
                if(gamePanel.tileManager.mapTileValidation[map][tempX][tempY]) {
                    valid = true;
                }
                tempY++;
            }

            if(tempY == o.worldY / gamePanel.tileSize + o.width){
                tempY = o.worldY / gamePanel.tileSize;
            }

            tempX++;
        }

        if(!valid){
            for (int i = o.worldX/ gamePanel.tileSize; i < o.worldX/ gamePanel.tileSize + o.width; i++) {
                for (int j = o.worldY/ gamePanel.tileSize; j < o.worldY/ gamePanel.tileSize + o.height; j++) {
                    gamePanel.tileManager.mapTileValidation[map][i][j] = true;
                }
            }
            return true;
        }else{
            return false;
        }
    }

    public void update(){
        if(gamePanel.keyHandler.upPressed || gamePanel.keyHandler.downPressed || gamePanel.keyHandler.leftPressed || gamePanel.keyHandler.rightPressed){
            if (gamePanel.keyHandler.upPressed){
                direction = "up";
                
            } else if (gamePanel.keyHandler.downPressed){
                direction = "down";
                
            } else if (gamePanel.keyHandler.leftPressed){
                direction = "left";
            
            } else if (gamePanel.keyHandler.rightPressed){
                direction = "right";
            } else {
                direction = "";
            }
        }

        switch (direction) {
            case "up":
                gamePanel.obj[gamePanel.currentMap][index].worldY -= 1 * gamePanel.tileSize;
                direction = "";
                gamePanel.keyHandler.upPressed = false;
                break;
            case "down":
                gamePanel.obj[gamePanel.currentMap][index].worldY += 1 * gamePanel.tileSize;
                direction = "";
                gamePanel.keyHandler.downPressed = false;
                break;
            case "left":
                gamePanel.obj[gamePanel.currentMap][index].worldX -= 1 * gamePanel.tileSize;
                direction = "";
                gamePanel.keyHandler.leftPressed = false;
                break;
            case "right":
                gamePanel.obj[gamePanel.currentMap][index].worldX += 1 * gamePanel.tileSize;
                direction = "";
                gamePanel.keyHandler.rightPressed = false;
                break;
            default:
                break;
            
        }
        
        if(gamePanel.keyHandler.enterPressed){
            if(setValidMap(gamePanel.obj[gamePanel.currentMap][index], gamePanel.currentMap)){
                gamePanel.setGameState(gamePanel.playState);
                gamePanel.keyHandler.enterPressed = false;
            }//else if(!setValidMap(gamePanel.obj[gamePanel.currentMap][index], gamePanel.currentMap)){
            //     gamePanel.player[gamePanel.currentPlayer].getInventory().add(gamePanel.obj[gamePanel.currentMap][index]);
            //     gamePanel.obj[gamePanel.currentMap][index] = null;
            //     gamePanel.ui.addMessage("Anda tidak bisa meletakkan objek disini!");
            //     gamePanel.setGameState(gamePanel.playState);
    
            // }
        } 
    }
}
