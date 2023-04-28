package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class OBJ_KomporGas extends SuperObject {
    GamePanel gamePanel;

    public OBJ_KomporGas(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.height = 1;
        this.width = 2;
        setName("Kompor Gas");
        gamePanel.setGameState(gamePanel.masakState);
        setMenu();

        try{
            image = ImageIO.read(new File("././res/object/kompor_gas.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        
        this.collision = true;
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
        gamePanel.ui.masak();
        gamePanel.player[gamePanel.currentPlayer].setMood(gamePanel.player[gamePanel.currentPlayer].getMood() + 10);
    }
}
