package object;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Rumah extends Barang{
    GamePanel gamePanel;
    int pemilik;
    public int xRumah[];
    int yRumah[];

    public OBJ_Rumah(GamePanel gamePanel, int pemilik){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 3;
        this.width = 3;
        setName("Rumah");
        //setStateOBJ("Berkunjung");
        this.pemilik = pemilik;
        xRumah = new int[4];
        yRumah = new int[4];
        //0 kiri atas
        xRumah[0] = 48;
        yRumah[0] = 48;

        //1 kiri bawah
        xRumah[1] = 48;
        yRumah[1] = 48 + 6;

        //2 kanan atas
        xRumah[2] = 48 + 6;
        yRumah[2] = 48;

        //3 kanan bawah
        xRumah[3] = 48 + 6;
        yRumah[3] = 48 + 6;

        try {
            image = ImageIO.read(new File("././res/object/rumah.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.collision = true;
        setsolidArea();     
    }

    public void setsolidArea(){
        solidArea.x = worldX;
        solidArea.y = worldY;
        solidArea.width = gamePanel.tileSize * width;
        solidArea.height = gamePanel.tileSize * height;
    }

    public void interact(Player player ){
        gamePanel.player[gamePanel.currentPlayer].naikMobil = false;
        gamePanel.player[gamePanel.currentPlayer].speed = 4;
        if(pemilik == gamePanel.player[gamePanel.currentPlayer].getId() ){
            gamePanel.player[gamePanel.currentPlayer].teleport(50, 50, gamePanel.player[gamePanel.currentPlayer].getId());
            gamePanel.playSoundEffect(2);
        }else{ 
            gamePanel.player[gamePanel.currentPlayer].setState("Berkunjung");
            gamePanel.isActiveAction = true;

        }
    }


}
