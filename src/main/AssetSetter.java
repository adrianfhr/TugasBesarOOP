package main;

import object.OBJ_SingleBed;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject(){

        gamePanel.obj[0] = new OBJ_SingleBed(gamePanel);
        gamePanel.obj[0].worldX = 10 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 10 * gamePanel.tileSize;

    }
}
