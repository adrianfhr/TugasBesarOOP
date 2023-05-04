package object;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_Rumah extends Barang{
    GamePanel gamePanel;
    public int pemilik;
    public String ruangan[];
    public int xRuangan[];
    public int yRuangan[];

    public OBJ_Rumah(GamePanel gamePanel, int pemilik){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 3;
        this.width = 3;
        setName("Rumah");
        this.pemilik = pemilik;
        this.ruangan = new String[10];
        this.xRuangan = new int[10];
        this.yRuangan = new int[10];
        ruangan[0] = "Utama";
        xRuangan[0] = 48;
        yRuangan[0] = 48;
        

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

    public void upgradeRumah(String namaRuangan, int x, int y){
        int index = 0;
        while(ruangan[index] != null){
            index++;
        }
        ruangan[index] = namaRuangan;
        xRuangan[index] = x;
        yRuangan[index] = y;
    }


}
