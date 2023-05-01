package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;


public class OBJ_Lampu extends Barang{
    GamePanel gamePanel;
    public OBJ_Lampu(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        setName("Lampu");
        setStateOBJ("Light");
        setDescription("Menerangi ruangan");
        setPrice(50);

        try {
            image = ImageIO.read(new File("././res/object/lampu.png"));
            setImage1(utilityTool.scaleImage(image, gamePanel.tileSize * 2/3, gamePanel.tileSize * 2/3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void setsolidArea() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setsolidArea'");
    }
    @Override
    public void interact(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }
    
}
