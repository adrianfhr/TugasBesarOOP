package object;

import entity.Player;
import main.GamePanel;

public abstract class Barang extends SuperObject{
    
    public Barang (GamePanel gamePanel){
        super(gamePanel);
    }
    abstract public void setsolidArea();

    abstract public void interact(Player player);

        
}



