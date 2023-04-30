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
    public int mapTileNump[][][];

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[100];
        getTileImage();
        mapTileNump = new int[gamePanel.maxMap][gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        loadMap("res/map/worldMap.txt", 0);
        loadMap("res/map/homeMap.txt", 1);

    }

    public void getTileImage(){

        setup(0, "000", false);
        setup(1, "001_rumput1", false);
        setup(2, "002_rumput2", false);
        setup(3, "003_water1", true);
        setup(4, "004_water2", true);
        setup(5, "005_water3", true);
        setup(6, "006_water4", true);
        setup(7, "007_water5", true);
        setup(8, "008_water6", true);
        setup(9, "009_water7", true);
        setup(10, "010_water8", true);
        setup(11, "011_water9", true);
        setup(12, "012_water10", true);
        setup(13, "013_water11", true);
        setup(14, "014_water12", true);
        setup(15, "015_water13", true);
        setup(16, "016_jalan1", false);
        setup(17, "017_jalan2", false);
        setup(18, "018_jalan3", false);
        setup(19, "019_jalan4", false);
        setup(20, "020_jalan5", false);
        setup(21, "021_lantairumah", false);
        setup(22, "022_wall", true);    
        setup(23, "023_bush2", false);
        setup(24, "024_jalan6", false);
        setup(25, "025_jalan7", false);
        setup(26, "026_jalan8", false);
        setup(27, "027_jalan9", false);
        setup(28, "028_jalan10", false);
        setup(29, "029_jalan11", false);
        setup(30, "030_jalan12", false);
        setup(31, "031_jalan13", false);
        setup(32, "032_jalan14", false);
        setup(33, "033_jalan15", false);
        setup(34, "034_jalan16", false);
        setup(35, "035_jalan17", false);
        setup(36, "036_jalan18", false);
        setup(37, "037_jalan19", false);
        setup(38, "038_jalan20", false);
        setup(39, "039_jalan21", false);
        setup(40, "040_jalan22", false);
        setup(41, "041_jalan23", false);
        
           
            
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

    public void loadMap(String path, int mapIndex){
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
                    mapTileNump[mapIndex][col][row] = num;
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
            int tileNum = mapTileNump[gamePanel.currentMap][worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            
            int screenX = worldX - gamePanel.player[gamePanel.currentPlayer].worldX + gamePanel.player[gamePanel.currentPlayer].screenX;; 
            int screenY = worldY - gamePanel.player[gamePanel.currentPlayer].worldY + gamePanel.player[gamePanel.currentPlayer].screenY;
            
            // cut processing hanya menggambar saat dibutuhkan
            if(worldX + gamePanel.tileSize > gamePanel.player[gamePanel.currentPlayer].worldX - gamePanel.player[gamePanel.currentPlayer].screenX && 
                        worldX - gamePanel.tileSize < gamePanel.player[gamePanel.currentPlayer].worldX + gamePanel.player[gamePanel.currentPlayer].screenX && 
                        worldY + gamePanel.tileSize > gamePanel.player[gamePanel.currentPlayer].worldY - gamePanel.player[gamePanel.currentPlayer].screenY &&
                        worldY - gamePanel.tileSize < gamePanel.player[gamePanel.currentPlayer].worldY + gamePanel.player[gamePanel.currentPlayer].screenY){
                
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

