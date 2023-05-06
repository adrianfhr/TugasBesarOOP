package main;

import entity.NPC_Cat;
import entity.NPC_Wife;
import javax.swing.JOptionPane;

import object.OBJ_Barbel;
import object.OBJ_Car;
import object.OBJ_Gereja;
import object.OBJ_Gudang;
import object.OBJ_Hospital;
import object.OBJ_Hotel;
import object.OBJ_Jam;
import object.OBJ_KingBed;
import object.OBJ_KomporGas;
import object.OBJ_KomporListrik;
import object.OBJ_Masjid;
import object.OBJ_MejaKursi;
import object.OBJ_Mixue;
import object.OBJ_Parking;
import object.OBJ_Pengemis;
import object.OBJ_Pintu;
import object.OBJ_QueenBed;
import object.OBJ_Rumah;
import object.OBJ_Sale;
import object.OBJ_SingleBed;
import object.OBJ_TV;
import object.OBJ_Tanah;
import object.OBJ_Toilet;
import object.SuperObject;
import object.OBJ_Traktor;

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
        gamePanel.obj[0][3].worldX = 50 * gamePanel.tileSize;
        gamePanel.obj[0][3].worldY = 17 * gamePanel.tileSize;
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

        gamePanel.obj[0][5] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][5].worldX = 48 * gamePanel.tileSize;
        gamePanel.obj[0][5].worldY = 45 * gamePanel.tileSize;
        

        gamePanel.obj[0][6] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][6].worldX = 52 * gamePanel.tileSize;
        gamePanel.obj[0][6].worldY = 45 * gamePanel.tileSize;
       

        gamePanel.obj[0][7] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][7].worldX = 56 * gamePanel.tileSize;
        gamePanel.obj[0][7].worldY = 45 * gamePanel.tileSize;
        

        gamePanel.obj[0][8] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][8].worldX = 60 * gamePanel.tileSize;
        gamePanel.obj[0][8].worldY = 45 * gamePanel.tileSize;
        

        gamePanel.obj[0][9] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][9].worldX = 48 * gamePanel.tileSize;
        gamePanel.obj[0][9].worldY = 49 * gamePanel.tileSize;
       

        gamePanel.obj[0][10] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][10].worldX = 52 * gamePanel.tileSize;
        gamePanel.obj[0][10].worldY = 49 * gamePanel.tileSize;
        

        gamePanel.obj[0][11] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][11].worldX = 56 * gamePanel.tileSize;
        gamePanel.obj[0][11].worldY = 49 * gamePanel.tileSize;
        

        gamePanel.obj[0][12] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][12].worldX = 60 * gamePanel.tileSize;
        gamePanel.obj[0][12].worldY = 49 * gamePanel.tileSize;
       

        gamePanel.obj[0][13] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][13].worldX = 48 * gamePanel.tileSize;
        gamePanel.obj[0][13].worldY = 53 * gamePanel.tileSize;
        

        gamePanel.obj[0][14] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][14].worldX = 52 * gamePanel.tileSize;
        gamePanel.obj[0][14].worldY = 53 * gamePanel.tileSize;
        

        gamePanel.obj[0][15] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][15].worldX = 56 * gamePanel.tileSize;
        gamePanel.obj[0][15].worldY = 53 * gamePanel.tileSize;
        

        gamePanel.obj[0][16] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][16].worldX = 60 * gamePanel.tileSize;
        gamePanel.obj[0][16].worldY = 53 * gamePanel.tileSize;
        

        gamePanel.obj[0][17] = new OBJ_Gereja(gamePanel);
        gamePanel.obj[0][17].worldX = 17 * gamePanel.tileSize;
        gamePanel.obj[0][17].worldY = 42 * gamePanel.tileSize;
        for (int i = 17; i < 17 + gamePanel.obj[0][17].width; i++) {
            for (int j = 42; j < 42 + gamePanel.obj[0][17].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][18] = new OBJ_Hotel(gamePanel);
        gamePanel.obj[0][18].worldX = 7 * gamePanel.tileSize;
        gamePanel.obj[0][18].worldY = 20 * gamePanel.tileSize;
        for (int i = 7; i < 7 + gamePanel.obj[0][18].width; i++) {
            for (int j = 20; j < 20 + gamePanel.obj[0][18].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][19] = new OBJ_Gudang(gamePanel);
        gamePanel.obj[0][19].worldX = 3 * gamePanel.tileSize;
        gamePanel.obj[0][19].worldY = 2 * gamePanel.tileSize;
        for (int i = 3; i < 3 + gamePanel.obj[0][19].width; i++) {
            for (int j = 2; j < 2 + gamePanel.obj[0][19].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][20] = new OBJ_Traktor(gamePanel);
        gamePanel.obj[0][20].worldX = 3 * gamePanel.tileSize;
        gamePanel.obj[0][20].worldY = 5 * gamePanel.tileSize;
        for (int i = 3; i < 3 + gamePanel.obj[0][20].width; i++) {
            for (int j = 5; j < 5 + gamePanel.obj[0][20].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][21] = new OBJ_Traktor(gamePanel);
        gamePanel.obj[0][21].worldX = 3 * gamePanel.tileSize;
        gamePanel.obj[0][21].worldY = 6 * gamePanel.tileSize;
        for (int i = 3; i < 3 + gamePanel.obj[0][21].width; i++) {
            for (int j = 6; j < 6 + gamePanel.obj[0][21].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][22] = new OBJ_Parking(gamePanel);
        gamePanel.obj[0][22].worldX = 52 * gamePanel.tileSize;
        gamePanel.obj[0][22].worldY = 24 * gamePanel.tileSize;
        for (int i = 52; i < 52 + gamePanel.obj[0][22].width; i++) {
            for (int j = 24; j < 24 + gamePanel.obj[0][22].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][23] = new OBJ_Parking(gamePanel);
        gamePanel.obj[0][23].worldX = 35 * gamePanel.tileSize;
        gamePanel.obj[0][23].worldY = 9 * gamePanel.tileSize;
        for (int i = 35; i < 35 + gamePanel.obj[0][23].width; i++) {
            for (int j = 9; j < 9 + gamePanel.obj[0][23].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][24] = new OBJ_Parking(gamePanel);
        gamePanel.obj[0][24].worldX = 20 * gamePanel.tileSize;
        gamePanel.obj[0][24].worldY = 48 * gamePanel.tileSize;
        for (int i = 20; i < 20 + gamePanel.obj[0][24].width; i++) {
            for (int j = 48; j < 48 + gamePanel.obj[0][24].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][25] = new OBJ_Hospital(gamePanel);
        gamePanel.obj[0][25].worldX = 8 * gamePanel.tileSize;
        gamePanel.obj[0][25].worldY = 42 * gamePanel.tileSize;
        for (int i = 8; i < 8 + gamePanel.obj[0][25].width; i++) {
            for (int j = 42; j < 42 + gamePanel.obj[0][25].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

    }

    public void makeOBJ(String objek, int map, int player){
        int tempIndex = 0;

        while (gamePanel.obj[map][tempIndex] != null) {
            tempIndex++;
            if (tempIndex == gamePanel.obj[map].length) {
                gamePanel.ui.setCurrentDialogue("Array penuh, tidak bisa menambahkan objek baru!");
                return;
            }
        }

        String x,y;
        int worldX, worldY;

        
        switch (objek) {
            case "Rumah":
                
                gamePanel.obj[map][tempIndex] = new OBJ_Rumah(gamePanel,gamePanel.player[player].getId() );
                OBJ_Rumah tempRumah = (OBJ_Rumah) gamePanel.obj[map][tempIndex];
                gamePanel.obj[map][tempIndex].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][tempIndex].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                index = tempIndex;
                gamePanel.obj[tempRumah.pemilik][tempIndex+1] = new OBJ_Pintu(gamePanel);
                gamePanel.obj[tempRumah.pemilik][tempIndex+1].worldX = 53 * gamePanel.tileSize;
                gamePanel.obj[tempRumah.pemilik][tempIndex+1].worldY = 53 * gamePanel.tileSize;
                
                break;


            case "Single Bed":
                gamePanel.obj[map][tempIndex] = new OBJ_SingleBed(gamePanel);
                gamePanel.obj[map][tempIndex].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][tempIndex].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                index = tempIndex;
                
                break;

            case "Jam":
                gamePanel.obj[map][tempIndex] = new OBJ_Jam(gamePanel);
                gamePanel.obj[map][tempIndex].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][tempIndex].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                index = tempIndex;
                break;

            case "King Bed":
                gamePanel.obj[map][tempIndex] = new OBJ_KingBed(gamePanel);
                gamePanel.obj[map][tempIndex].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][tempIndex].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                index = tempIndex;
                break;
            
            case "Kompor Gas":
                gamePanel.obj[map][tempIndex] = new OBJ_KomporGas(gamePanel);
                gamePanel.obj[map][tempIndex].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][tempIndex].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                index = tempIndex;
                break;

            case "Kompor Listrik":
                gamePanel.obj[map][tempIndex] = new OBJ_KomporListrik(gamePanel);
                gamePanel.obj[map][tempIndex].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][tempIndex].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                index = tempIndex;
                
                break;
            
            case "Meja Kursi":
                gamePanel.obj[map][tempIndex] = new OBJ_MejaKursi(gamePanel);
                gamePanel.obj[map][tempIndex].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][tempIndex].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                index = tempIndex;
                break;

            case "Queen Bed":
                gamePanel.obj[map][tempIndex] = new OBJ_QueenBed(gamePanel);
                gamePanel.obj[map][tempIndex].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][tempIndex].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                index = tempIndex;
                break;
            
            case "Toilet":
                gamePanel.obj[map][tempIndex] = new OBJ_Toilet(gamePanel);
                gamePanel.obj[map][tempIndex].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][tempIndex].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                index = tempIndex;
                break;
            
            case "TV":
                gamePanel.obj[map][tempIndex] = new OBJ_TV(gamePanel);
                gamePanel.obj[map][tempIndex].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][tempIndex].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                index = tempIndex;
                break;

            case "Barbel":
                gamePanel.obj[map][tempIndex] = new OBJ_Barbel(gamePanel);
                gamePanel.obj[map][tempIndex].worldX = (((int)(gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize)) * gamePanel.tileSize);
                gamePanel.obj[map][tempIndex].worldY = (((int)(gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize)) * gamePanel.tileSize);
                index = tempIndex;
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

    public void removeValidMap(SuperObject o, int map){
        for (int i = o.worldX/ gamePanel.tileSize; i < o.worldX/ gamePanel.tileSize + o.width; i++) {
            for (int j = o.worldY/ gamePanel.tileSize; j < o.worldY/ gamePanel.tileSize + o.height; j++) {
                gamePanel.tileManager.mapTileValidation[map][i][j] = false;
            }
        }
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
