package main;

import entity.NPC_Cat;
import entity.NPC_Wife;
import javax.swing.JOptionPane;

import object.OBJ_Car;
import object.OBJ_Gudang;
import object.OBJ_Hotel;
import object.OBJ_Jam;
import object.OBJ_KingBed;
import object.OBJ_KomporGas;
import object.OBJ_KomporListrik;
import object.OBJ_Masjid;
import object.OBJ_MejaKursi;
import object.OBJ_Mixue;
import object.OBJ_Pengemis;
import object.OBJ_Pintu;
import object.OBJ_Pitch;
import object.OBJ_QueenBed;
import object.OBJ_Rumah;
import object.OBJ_Sale;
import object.OBJ_SingleBed;
import object.OBJ_TV;
import object.OBJ_Tanah;
import object.OBJ_Toilet;
import object.OBJ_Traktor;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
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
        gamePanel.obj[0][5].worldX = 47 * gamePanel.tileSize;
        gamePanel.obj[0][5].worldY = 44 * gamePanel.tileSize;
        for (int i = 47; i < 47 + gamePanel.obj[0][5].width; i++) {
            for (int j = 44; j < 44 + gamePanel.obj[0][5].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][6] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][6].worldX = 51 * gamePanel.tileSize;
        gamePanel.obj[0][6].worldY = 44 * gamePanel.tileSize;
        for (int i = 51; i < 51 + gamePanel.obj[0][6].width; i++) {
            for (int j = 44; j < 44 + gamePanel.obj[0][6].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][7] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][7].worldX = 55 * gamePanel.tileSize;
        gamePanel.obj[0][7].worldY = 44 * gamePanel.tileSize;
        for (int i = 55; i < 55 + gamePanel.obj[0][7].width; i++) {
            for (int j = 44; j < 44 + gamePanel.obj[0][7].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][8] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][8].worldX = 59 * gamePanel.tileSize;
        gamePanel.obj[0][8].worldY = 44 * gamePanel.tileSize;
        for (int i = 59; i < 59 + gamePanel.obj[0][8].width; i++) {
            for (int j = 44; j < 44 + gamePanel.obj[0][8].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][9] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][9].worldX = 47 * gamePanel.tileSize;
        gamePanel.obj[0][9].worldY = 48 * gamePanel.tileSize;
        for (int i = 47; i < 47 + gamePanel.obj[0][5].width; i++) {
            for (int j = 48; j < 48 + gamePanel.obj[0][5].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][10] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][10].worldX = 51 * gamePanel.tileSize;
        gamePanel.obj[0][10].worldY = 48 * gamePanel.tileSize;
        for (int i = 51; i < 51 + gamePanel.obj[0][6].width; i++) {
            for (int j = 48; j < 48 + gamePanel.obj[0][6].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][11] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][11].worldX = 55 * gamePanel.tileSize;
        gamePanel.obj[0][11].worldY = 48 * gamePanel.tileSize;
        for (int i = 55; i < 55 + gamePanel.obj[0][7].width; i++) {
            for (int j = 48; j < 48 + gamePanel.obj[0][7].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][12] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][12].worldX = 59 * gamePanel.tileSize;
        gamePanel.obj[0][12].worldY = 48 * gamePanel.tileSize;
        for (int i = 59; i < 59 + gamePanel.obj[0][8].width; i++) {
            for (int j = 48; j < 48 + gamePanel.obj[0][8].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][13] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][13].worldX = 47 * gamePanel.tileSize;
        gamePanel.obj[0][13].worldY = 52 * gamePanel.tileSize;
        for (int i = 47; i < 47 + gamePanel.obj[0][5].width; i++) {
            for (int j = 52; j < 52 + gamePanel.obj[0][5].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][14] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][14].worldX = 51 * gamePanel.tileSize;
        gamePanel.obj[0][14].worldY = 52 * gamePanel.tileSize;
        for (int i = 51; i < 51 + gamePanel.obj[0][6].width; i++) {
            for (int j = 52; j < 52 + gamePanel.obj[0][6].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][15] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][15].worldX = 55 * gamePanel.tileSize;
        gamePanel.obj[0][15].worldY = 52 * gamePanel.tileSize;
        for (int i = 55; i < 55 + gamePanel.obj[0][7].width; i++) {
            for (int j = 52; j < 52 + gamePanel.obj[0][7].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][16] = new OBJ_Sale(gamePanel);
        gamePanel.obj[0][16].worldX = 59 * gamePanel.tileSize;
        gamePanel.obj[0][16].worldY = 52 * gamePanel.tileSize;
        for (int i = 59; i < 59 + gamePanel.obj[0][8].width; i++) {
            for (int j = 52; j < 52 + gamePanel.obj[0][8].height; j++) {
                gamePanel.tileManager.mapTileValidation[0][i][j] = true;
            }
        }

        gamePanel.obj[0][17] = new OBJ_Pitch(gamePanel);
        gamePanel.obj[0][17].worldX = 3 * gamePanel.tileSize;
        gamePanel.obj[0][17].worldY = 20 * gamePanel.tileSize;
        for (int i = 3; i < 3 + gamePanel.obj[0][17].width; i++) {
            for (int j = 20; j < 20 + gamePanel.obj[0][17].height; j++) {
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

    }

    public void makeOBJ(String objek, int map, int player){
        int index = 0;
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
                x = JOptionPane.showInputDialog(null, "Masukkan lokasi x rumah");
                y = JOptionPane.showInputDialog(null, "Masukkan lokasi y rumah");
                worldX = Integer.parseInt(x);
                worldY = Integer.parseInt(y);
                gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY - 1, 0);
                gamePanel.obj[map][index] = new OBJ_Rumah(gamePanel,gamePanel.player[player].getId() );
                gamePanel.obj[map][index].worldX = worldX * gamePanel.tileSize;
                gamePanel.obj[map][index].worldY = worldY * gamePanel.tileSize;
    

            
                break;
            case "Pintu":
                gamePanel.obj[map][index] = new OBJ_Pintu(gamePanel);
                gamePanel.obj[map][index].worldX = 53 * gamePanel.tileSize;
                gamePanel.obj[map][index].worldY = 53 * gamePanel.tileSize;
                break;

            case "Single Bed":
                x = JOptionPane.showInputDialog(null, "Masukkan lokasi x kasur");
                y = JOptionPane.showInputDialog(null, "Masukkan lokasi y kasur");
                worldX = Integer.parseInt(x);
                worldY = Integer.parseInt(y);
                gamePanel.obj[map][index] = new OBJ_SingleBed(gamePanel);
                gamePanel.obj[map][index].worldX = worldX * gamePanel.tileSize;
                gamePanel.obj[map][index].worldY = worldY * gamePanel.tileSize;
                if(setValidMap(worldX, worldY, gamePanel.obj[map][index].width, gamePanel.obj[map][index].height, map)){
                    gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Objek berhasil diletakkan");
                }else{
                    gamePanel.obj[map][index] = null;
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Tidak bisa menaruh objek di sini");
                    gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_SingleBed(gamePanel));
                }
                break;

            case "Jam":
                x = JOptionPane.showInputDialog(null, "Masukkan lokasi x jam");
                y = JOptionPane.showInputDialog(null, "Masukkan lokasi y jam");
                worldX = Integer.parseInt(x);
                worldY = Integer.parseInt(y);
                
                gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                gamePanel.obj[map][index] = new OBJ_Jam(gamePanel);
                gamePanel.obj[map][index].worldX = worldX * gamePanel.tileSize;
                gamePanel.obj[map][index].worldY = worldY * gamePanel.tileSize;
                if(setValidMap(worldX, worldY, gamePanel.obj[map][index].width, gamePanel.obj[map][index].height, map)){
                    gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Objek berhasil diletakkan");
                }else{
                    gamePanel.obj[map][index] = null;
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Tidak bisa menaruh objek di sini");
                    gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_Jam(gamePanel));
                }
                break;

            case "King Bed":
                x = JOptionPane.showInputDialog(null, "Masukkan lokasi x kasur");
                y = JOptionPane.showInputDialog(null, "Masukkan lokasi y kasur");
                worldX = Integer.parseInt(x);
                worldY = Integer.parseInt(y);
                gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                gamePanel.obj[map][index] = new OBJ_KingBed(gamePanel);
                gamePanel.obj[map][index].worldX = worldX * gamePanel.tileSize;
                gamePanel.obj[map][index].worldY = worldY * gamePanel.tileSize;
                if(setValidMap(worldX, worldY, gamePanel.obj[map][index].width, gamePanel.obj[map][index].height, map)){
                    gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Objek berhasil diletakkan");
                }else{
                    gamePanel.obj[map][index] = null;
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Tidak bisa menaruh objek di sini");
                    gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_KingBed(gamePanel));
                }
                break;
            
            case "Kompor Gas":
                x = JOptionPane.showInputDialog(null, "Masukkan lokasi x kompor");
                y = JOptionPane.showInputDialog(null, "Masukkan lokasi y kompor");
                worldX = Integer.parseInt(x);
                worldY = Integer.parseInt(y);
                gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                gamePanel.obj[map][index] = new OBJ_KomporGas(gamePanel);
                gamePanel.obj[map][index].worldX = worldX * gamePanel.tileSize;
                gamePanel.obj[map][index].worldY = worldY * gamePanel.tileSize;
                if(setValidMap(worldX, worldY, gamePanel.obj[map][index].width, gamePanel.obj[map][index].height, map)){
                    gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Objek berhasil diletakkan");
                }else{
                    gamePanel.obj[map][index] = null;
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Tidak bisa menaruh objek di sini");
                    gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_KomporGas(gamePanel));
                }
                break;

            case "Kompor Listrik":
                x = JOptionPane.showInputDialog(null, "Masukkan lokasi x kompor");
                y = JOptionPane.showInputDialog(null, "Masukkan lokasi y kompor");
                worldX = Integer.parseInt(x);
                worldY = Integer.parseInt(y);
                gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                gamePanel.obj[map][index] = new OBJ_KomporListrik(gamePanel);
                gamePanel.obj[map][index].worldX = worldX * gamePanel.tileSize;
                gamePanel.obj[map][index].worldY = worldY * gamePanel.tileSize;
                if(setValidMap(worldX, worldY, gamePanel.obj[map][index].width, gamePanel.obj[map][index].height, map)){
                    gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Objek berhasil diletakkan");
                }else{
                    gamePanel.obj[map][index] = null;
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Tidak bisa menaruh objek di sini");
                    gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_KomporListrik(gamePanel));
                }
                break;
            
            case "Meja Kursi":
                x = JOptionPane.showInputDialog(null, "Masukkan lokasi x meja kursi");
                y = JOptionPane.showInputDialog(null, "Masukkan lokasi y meja kursi");
                worldX = Integer.parseInt(x);
                worldY = Integer.parseInt(y);
                gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                gamePanel.obj[map][index] = new OBJ_MejaKursi(gamePanel);
                gamePanel.obj[map][index].worldX = worldX * gamePanel.tileSize;
                gamePanel.obj[map][index].worldY = worldY * gamePanel.tileSize;
                if(setValidMap(worldX, worldY, gamePanel.obj[map][index].width, gamePanel.obj[map][index].height, map)){
                    gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Objek berhasil diletakkan");
                }else{
                    gamePanel.obj[map][index] = null;
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Tidak bisa menaruh objek di sini");
                    gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_MejaKursi(gamePanel));
                }
                break;

            case "Queen Bed":
                x = JOptionPane.showInputDialog(null, "Masukkan lokasi x kasur");
                y = JOptionPane.showInputDialog(null, "Masukkan lokasi y kasur");
                worldX = Integer.parseInt(x);
                worldY = Integer.parseInt(y);
                gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                gamePanel.obj[map][index] = new OBJ_QueenBed(gamePanel);
                gamePanel.obj[map][index].worldX = worldX * gamePanel.tileSize;
                gamePanel.obj[map][index].worldY = worldY * gamePanel.tileSize;
                if(setValidMap(worldX, worldY, gamePanel.obj[map][index].width, gamePanel.obj[map][index].height, map)){
                    gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Objek berhasil diletakkan");
                }else{
                    gamePanel.obj[map][index] = null;
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Tidak bisa menaruh objek di sini");
                    gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_QueenBed(gamePanel));
                }
                break;
            
            case "Toilet":
                x = JOptionPane.showInputDialog(null, "Masukkan lokasi x toilet");
                y = JOptionPane.showInputDialog(null, "Masukkan lokasi y toilet");
                worldX = Integer.parseInt(x);
                worldY = Integer.parseInt(y);
                gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                gamePanel.obj[map][index] = new OBJ_Toilet(gamePanel);
                gamePanel.obj[map][index].worldX = worldX * gamePanel.tileSize;
                gamePanel.obj[map][index].worldY = worldY * gamePanel.tileSize;
                if(setValidMap(worldX, worldY, gamePanel.obj[map][index].width, gamePanel.obj[map][index].height, map)){
                    gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Objek berhasil diletakkan");
                }else{
                    gamePanel.obj[map][index] = null;
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Tidak bisa menaruh objek di sini");
                    gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_Toilet(gamePanel));
                }
                break;
            
            case "TV":
                x = JOptionPane.showInputDialog(null, "Masukkan lokasi x tv");
                y = JOptionPane.showInputDialog(null, "Masukkan lokasi y tv");
                worldX = Integer.parseInt(x);
                worldY = Integer.parseInt(y);
                gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                gamePanel.obj[map][index] = new OBJ_TV(gamePanel);
                gamePanel.obj[map][index].worldX = worldX * gamePanel.tileSize;
                gamePanel.obj[map][index].worldY = worldY * gamePanel.tileSize;
                if(setValidMap(worldX, worldY, gamePanel.obj[map][index].width, gamePanel.obj[map][index].height, map)){
                    gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Objek berhasil diletakkan");
                }else{
                    gamePanel.obj[map][index] = null;
                    gamePanel.setGameState(gamePanel.dialogueState);
                    gamePanel.ui.setCurrentDialogue("Tidak bisa menaruh objek di sini");
                    gamePanel.player[gamePanel.currentPlayer].getInventory().add(new OBJ_TV(gamePanel));
                }
                break;

            default:
                break;
        }

    }

    public void setNPC() {
        gamePanel.npc[0] = new NPC_Wife(gamePanel);
        gamePanel.npc[0]. worldX = gamePanel.tileSize*28;
        gamePanel.npc[0]. worldY = gamePanel.tileSize*30;

        gamePanel.cat[1] = new NPC_Cat(gamePanel);
        gamePanel.cat[1]. worldX = gamePanel.tileSize*35;
        gamePanel.cat[1]. worldY = gamePanel.tileSize*35;
    }

    public boolean setValidMap(int x, int y, int width, int height, int map) {
        boolean valid = false;
        int tempX = x;
        int tempY = y;

        while(!valid && tempX < x + width) {
            while(!valid && tempY < y + height) {
                if(gamePanel.tileManager.mapTileValidation[map][tempX][tempY]) {
                    valid = true;
                }
                tempY++;
            }

            if(tempY == y + width){
                tempY = y;
            }

            tempX++;
        }

        if(!valid){
            for (int i = x; i < x + width; i++) {
                for (int j = y; j < y + height; j++) {
                    gamePanel.tileManager.mapTileValidation[map][i][j] = true;
                }
            }
            return true;
        }else{
            return false;
        }
    }
}
