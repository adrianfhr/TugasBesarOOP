package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;

import javax.swing.table.AbstractTableModel;

import entity.Player;
import main.GamePanel;
import main.UtilityTool;

public abstract class SuperObject implements Asset{

    public BufferedImage image;
    public String name;
    public String description;
    public boolean collision = false;
    public int worldX, worldY;
    public int height = 1, width = 1;
    public Rectangle solidArea = new Rectangle();
    public int solidAreaDefaultX = 0; public int solidAreaDefaultY = 0;
    public UtilityTool utilityTool = new UtilityTool();
    private final GamePanel gamePanel;
    private int value;
    protected BufferedImage image1;
    
    public SuperObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setDescription("idle");
    }

    public void draw(Graphics2D g2d, GamePanel gamePanel){

        int screenX = worldX - gamePanel.player[gamePanel.currentPlayer].worldX + gamePanel.player[gamePanel.currentPlayer].screenX;; 
        int screenY = worldY - gamePanel.player[gamePanel.currentPlayer].worldY + gamePanel.player[gamePanel.currentPlayer].screenY;
            
        // cut processing hanya menggambar saat dibutuhkan
        if(worldX + gamePanel.tileSize > gamePanel.player[gamePanel.currentPlayer].worldX - gamePanel.player[gamePanel.currentPlayer].screenX && 
                        worldX - gamePanel.tileSize < gamePanel.player[gamePanel.currentPlayer].worldX + gamePanel.player[gamePanel.currentPlayer].screenX && 
                        worldY + gamePanel.tileSize > gamePanel.player[gamePanel.currentPlayer].worldY - gamePanel.player[gamePanel.currentPlayer].screenY &&
                        worldY - gamePanel.tileSize < gamePanel.player[gamePanel.currentPlayer].worldY + gamePanel.player[gamePanel.currentPlayer].screenY){
                
                //g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                g2d.drawImage(image, screenX, screenY, gamePanel.tileSize * width, gamePanel.tileSize * height, null);
            }
        
    }

    public String getName() {
        return name;
    }

    public Object setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Object setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getValue() {
        return value;
    }

    public Object setValue(int value) {
        this.value = value;
        return this;
    }

    public BufferedImage getImage1() {
        return image1;
    }

    public Object setImage1(BufferedImage image1) {
        this.image1 = image1;
        return this;
    }

    abstract public void setsolidArea();

    abstract public void interact(Player player);

    
}
