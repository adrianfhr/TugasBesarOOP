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

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject(){

        gamePanel.obj[1][0] = new OBJ_SingleBed(gamePanel);
        gamePanel.obj[1][0].worldX = 48 * gamePanel.tileSize;
        gamePanel.obj[1][0].worldY = 48 * gamePanel.tileSize;

        gamePanel.obj[1][1] = new OBJ_Pintu(gamePanel);
        gamePanel.obj[1][1].worldX = 53 * gamePanel.tileSize;
        gamePanel.obj[1][1].worldY = 53 * gamePanel.tileSize;

        gamePanel.obj[1][2] = new OBJ_KomporGas(gamePanel);
        gamePanel.obj[1][2].worldX = 52 * gamePanel.tileSize;
        gamePanel.obj[1][2].worldY = 48 * gamePanel.tileSize;

        gamePanel.obj[1][3] = new OBJ_KomporListrik(gamePanel);
        gamePanel.obj[1][3].worldX = 49 * gamePanel.tileSize;
        gamePanel.obj[1][3].worldY = 48 * gamePanel.tileSize;

        gamePanel.obj[1][4] = new OBJ_Toilet(gamePanel);
        gamePanel.obj[1][4].worldX = 48 * gamePanel.tileSize;
        gamePanel.obj[1][4].worldY = 52 * gamePanel.tileSize;

        gamePanel.obj[1][5] = new OBJ_TV(gamePanel);
        gamePanel.obj[1][5].worldX = 50 * gamePanel.tileSize;
        gamePanel.obj[1][5].worldY = 52 * gamePanel.tileSize;

        gamePanel.obj[0][0] = new OBJ_Rumah(gamePanel, 1);
        gamePanel.obj[0][0].worldX = 41 * gamePanel.tileSize;
        gamePanel.obj[0][0].worldY = 19 * gamePanel.tileSize;

        gamePanel.obj[0][1] = new OBJ_Masjid(gamePanel);
        gamePanel.obj[0][1].worldX = 30 * gamePanel.tileSize;
        gamePanel.obj[0][1].worldY = 5 * gamePanel.tileSize;

        
        gamePanel.obj[0][2] = new OBJ_Pengemis(gamePanel);
        gamePanel.obj[0][2].worldX = 33 * gamePanel.tileSize;
        gamePanel.obj[0][2].worldY = 33 * gamePanel.tileSize;

        gamePanel.obj[0][3] = new OBJ_Mixue(gamePanel);
        gamePanel.obj[0][3].worldX = 51 * gamePanel.tileSize;
        gamePanel.obj[0][3].worldY = 19 * gamePanel.tileSize;

        gamePanel.obj[0][4] = new OBJ_Car(gamePanel);
        gamePanel.obj[0][4].worldX = 42 * gamePanel.tileSize;
        gamePanel.obj[0][4].worldY = 24 * gamePanel.tileSize;

    }

    public void makeOBJ(String objek, int map, int player){
        int index = 0;
        while (gamePanel.obj[map][index] != null) {
            index++;
            if (index == gamePanel.obj[map].length) {
                System.out.println("Array penuh, tidak bisa menambahkan objek baru!");
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
                gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY, gamePanel.currentMap);
                gamePanel.obj[map][index] = new OBJ_SingleBed(gamePanel);
                gamePanel.obj[map][index].worldX = worldX * gamePanel.tileSize;
                gamePanel.obj[map][index].worldY = worldY * gamePanel.tileSize;
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

    public void setValidMap(int x, int y, int length, int width, int map) {
        for (int i = x; i < x + length; i++) {
            for (int j = y; j < y + width; j++) {
                gamePanel.tileManager.mapTileValidation[map][i][j] = true;
            }
        }
    }
}
