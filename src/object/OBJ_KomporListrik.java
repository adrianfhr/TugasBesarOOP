package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_KomporListrik extends Barang {
    GamePanel gamePanel;
    String stateObject;
    public OBJ_KomporListrik(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 1;
        this.width = 2;
        stateObject = "Memasak";
        setName("Kompor Listrik");
        setDescription("[" + getName() + "]\nSama aja kayak kompor gas,\ntapi pake listrik.\n(Orang mampu only)");
        setStateOBJ("Memasak");
        setMenu();
        setPrice(200);

        try{
            image = ImageIO.read(new File("././res/object/kompor_listrik.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        
        this.collision = false;
        setsolidArea();
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
    }
}
