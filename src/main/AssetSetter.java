package main;

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
        gamePanel.obj[0][0].worldX = 37 * gamePanel.tileSize;
        gamePanel.obj[0][0].worldY = 24 * gamePanel.tileSize;


    }
}
