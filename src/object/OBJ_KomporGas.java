package object;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_KomporGas extends Barang {
    GamePanel gamePanel;

    public OBJ_KomporGas(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 1;
        this.width = 2;
        setName("Kompor Gas");
        
        setStateOBJ("Memasak");
        setPrice(100);
        setMenu();
        setDescription("[" + getName() + "]\nUntuk memasak.\n\nHarga : " + getPrice());

        try{
            image = ImageIO.read(new File("././res/object/kompor_gas.png"));
            image_orang = ImageIO.read(new File("././res/object/masak2.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        
        this.collision = false;
        setsolidArea();
    }

    @Override
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
                    g2d.drawImage(image_orang, screenX, screenY - 12, gamePanel.tileSize * width, gamePanel.tileSize * height *2, null);
                }else{
                    g2d.drawImage(image, screenX, screenY, gamePanel.tileSize * width, gamePanel.tileSize * height, null);

                }
            }
        
    }

    public void setMenu(){
        getMenu().add(new OBJ_Bistik(gamePanel));
        getMenu().add(new OBJ_NasiKari(gamePanel));
        getMenu().add(new OBJ_NasiAyam(gamePanel));
        getMenu().add(new OBJ_SusuKacang(gamePanel));
        getMenu().add(new OBJ_TumisSayur(gamePanel));
    }

    public void setsolidArea(){
        solidArea.x = worldX;
        solidArea.y = worldY;
        solidArea.width = gamePanel.tileSize * width;
        solidArea.height = gamePanel.tileSize * height;
    }

    public void interact(Player player){
        gamePanel.player[gamePanel.currentPlayer].setMood(gamePanel.player[gamePanel.currentPlayer].getMood() + 10);
        if (gamePanel.player[gamePanel.currentPlayer].getMood() > 100){
            gamePanel.player[gamePanel.currentPlayer].setMood(100);
        }
        gamePanel.ui.addMessage("Mood + 10");
    }

    
}
