package main;

import object.OBJ_Masjid;
import object.OBJ_Pengemis;
import object.OBJ_Rumah;
import object.OBJ_SingleBed;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject(){

        gamePanel.obj[1][0] = new OBJ_SingleBed(gamePanel);
        gamePanel.obj[1][0].worldX = 51 * gamePanel.tileSize;
        gamePanel.obj[1][0].worldY = 50 * gamePanel.tileSize;


        gamePanel.obj[0][0] = new OBJ_Rumah(gamePanel);
        gamePanel.obj[0][0].worldX = 33 * gamePanel.tileSize;
        gamePanel.obj[0][0].worldY = 20 * gamePanel.tileSize;

        gamePanel.obj[0][1] = new OBJ_Masjid(gamePanel);
        gamePanel.obj[0][1].worldX = 17 * gamePanel.tileSize;
        gamePanel.obj[0][1].worldY = 29 * gamePanel.tileSize;

        
        gamePanel.obj[0][2] = new OBJ_Pengemis(gamePanel);
        gamePanel.obj[0][2].worldX = 33 * gamePanel.tileSize;
        gamePanel.obj[0][2].worldY = 33 * gamePanel.tileSize;

    }
}
