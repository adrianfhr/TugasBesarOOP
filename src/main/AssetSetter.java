package main;

import entity.NPC_Wife;
import javax.swing.JOptionPane;

import object.OBJ_KomporGas;
import object.OBJ_KomporListrik;
import object.OBJ_Masjid;
import object.OBJ_Pengemis;
import object.OBJ_Pintu;
import object.OBJ_Rumah;
import object.OBJ_SingleBed;

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

        gamePanel.obj[0][0] = new OBJ_Rumah(gamePanel, 1);
        gamePanel.obj[0][0].worldX = 33 * gamePanel.tileSize;
        gamePanel.obj[0][0].worldY = 20 * gamePanel.tileSize;

        gamePanel.obj[0][1] = new OBJ_Masjid(gamePanel);
        gamePanel.obj[0][1].worldX = 17 * gamePanel.tileSize;
        gamePanel.obj[0][1].worldY = 29 * gamePanel.tileSize;

        
        gamePanel.obj[0][2] = new OBJ_Pengemis(gamePanel);
        gamePanel.obj[0][2].worldX = 33 * gamePanel.tileSize;
        gamePanel.obj[0][2].worldY = 33 * gamePanel.tileSize;

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

        if(objek.equals("rumah")){
            String x = JOptionPane.showInputDialog(null, "Masukkan lokasi x rumah");
            String y = JOptionPane.showInputDialog(null, "Masukkan lokasi y rumah");
            int worldX = Integer.parseInt(x);
            int worldY = Integer.parseInt(y);
            gamePanel.player[gamePanel.currentPlayer].teleport(worldX, worldY - 1, 0);
            gamePanel.obj[map][index] = new OBJ_Rumah(gamePanel,gamePanel.player[player].getId() );
            gamePanel.obj[map][index].worldX = worldX * gamePanel.tileSize;
            gamePanel.obj[map][index].worldY = worldY * gamePanel.tileSize;
            
        }if(objek.equals("Pintu")){
            
            gamePanel.obj[map][index] = new OBJ_Pintu(gamePanel);
            gamePanel.obj[map][index].worldX = 53 * gamePanel.tileSize;
            gamePanel.obj[map][index].worldY = 53 * gamePanel.tileSize;
          
        }
    }

    public void setNPC() {
        gamePanel.npc[0] = new NPC_Wife(gamePanel);
        gamePanel.npc[0]. worldX = gamePanel.tileSize*30;
        gamePanel.npc[0]. worldY = gamePanel.tileSize*30;
    }

}
