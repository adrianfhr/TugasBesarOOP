package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import entity.Player;
import main.GamePanel;
import main.UtilityTool;
import java.util.ArrayList;
import java.util.List;

public class SuperObject{

    public BufferedImage image, image_orang;
    public String name, direction;
    public String description;
    public boolean collision = false, isActiveActionOBJ = false;
    public int worldX, worldY;
    public int height = 1, width = 1;
    public Rectangle solidArea = new Rectangle();
    public int solidAreaDefaultX = 0; public int solidAreaDefaultY = 0;
    public UtilityTool utilityTool = new UtilityTool();
    protected GamePanel gamePanel;
    private int value;
    protected BufferedImage image1;
    private List<SuperObject> menu = new ArrayList<>();
    private List<SuperObject> dagangan = new ArrayList<>();
    private int price;
    private String stateOBJ;
    
    public SuperObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setDescription("idle");
        setStateOBJ("Idle");
    }

    public void update(){
        
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
                if(isActiveActionOBJ){
                    g2d.drawImage(image_orang, screenX, screenY, gamePanel.tileSize * width, gamePanel.tileSize * height, null);
                }else{
                    g2d.drawImage(image, screenX, screenY, gamePanel.tileSize * width, gamePanel.tileSize * height, null);

                }
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

    public List<SuperObject> getMenu() {
        return menu;
    }

    public int getPrice() {
        return price;
    }

    public Object setPrice(int price) {
        this.price = price;
        return this;
    }

    public void setStateOBJ(String stateOBJ) {
        this.stateOBJ = stateOBJ;
    }

    public String getState(){
        return stateOBJ;
    }

    public void selectMenu() {
    }

    public void interact(Player player){
        
    }

    public void use(){
        System.out.println("use");
    }

    public void moveUp(){
        if (this.worldX > 0){
            this.worldX -= 1 * gamePanel.tileSize;
        }
    }

    public void moveDown(){
        if (this.worldX < gamePanel.worldWidth * gamePanel.tileSize - gamePanel.tileSize){
            this.worldX += 1 * gamePanel.tileSize;
        }
    }

    public void moveLeft(){
        if (this.worldY > 0){
            this.worldY -= 1 * gamePanel.tileSize;
        }
    }

    public void moveRight(){
        if (this.worldY < gamePanel.worldHeight * gamePanel.tileSize - gamePanel.tileSize){
            this.worldY += 1 * gamePanel.tileSize;
        }
    }
    
}
