package tile;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import java.util.Scanner;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNump[][];

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[100];
        getTileImage();
        mapTileNump = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        loadMap("res/map/worldMap.txt");
    }

    public void getTileImage(){

        setup(0, "000", false);
        setup(1, "001_rumput1", false);
        setup(2, "002_rumput2", false);
        setup(3, "003_water01", true);
        setup(4, "004_water02", true);
        setup(5, "005_water03", true);
        setup(6, "006_water04", true);
        setup(7, "007_water05", true);
        setup(8, "008_water06", true);
        setup(9, "009_water07", true);
        setup(10, "010_water08", true);
        setup(11, "011_water09", true);
        setup(12, "012_water10", true);
        setup(13, "013_water11", true);
        setup(14, "014_water12", true);
        setup(15, "015_water13", true);
        setup(16, "016_jalan1", false);
        setup(17, "017_jalan2", false);
        setup(18, "018_jalan3", false);
        setup(19, "019_jalan4", false);

            
    }

    public void setup(int index, String imagePath, boolean collision){
        UtilityTool utilityTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image =  ImageIO.read(new File ("././res/worldmap/" + imagePath + ".png"));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
    }

    }

    public void loadMap(String path){
        try {

            File mapFile = new File(path);
            Scanner scanner = new Scanner(mapFile);

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow && scanner.hasNextLine()) {
                String line = scanner.nextLine();

                while (col < gamePanel.maxWorldCol) {
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);
                    mapTileNump[col][row] = num;
                    col++;
                }

                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

            scanner.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
        

        while(worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow){
            int tileNum = mapTileNump[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;; 
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
            
            // cut processing hanya menggambar saat dibutuhkan
            if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX && 
                        worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX && 
                        worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                        worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){
                
                g2.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }

          
            worldCol++;
          
            if (worldCol == gamePanel.maxWorldCol){
                worldCol = 0;
                worldRow++;
               
            }    
        }

        
    }
}

