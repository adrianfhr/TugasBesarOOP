package object;

import javax.swing.JOptionPane;

import entity.Player;
import main.AssetSetter;
import main.GamePanel;

public abstract class Barang extends SuperObject{
    
    public Barang (GamePanel gamePanel){
        super(gamePanel);
    }

    public void use(){
        gamePanel.player[gamePanel.currentPlayer].getInventory().remove(this);
    }

    abstract public void setsolidArea();

    abstract public void interact(Player player);

        
}



