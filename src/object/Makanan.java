package object;

import main.GamePanel;

public class Makanan extends SuperObject{
    int kekenyanganValue;

    public Makanan (GamePanel gamePanel){
        super(gamePanel);
    }

    public void setKekenyanganValue(int kekenyanganValue){
        this.kekenyanganValue = kekenyanganValue;
    }

    public int getKekenyanganValue(){
        return kekenyanganValue;
    }

    public void use(){
        if (gamePanel.player[gamePanel.currentPlayer].getHunger() + getKekenyanganValue() <= 100){
            System.out.println("ini : " + gamePanel.player[gamePanel.currentPlayer].getHunger());
            System.out.println("ini : " + getKekenyanganValue());
            gamePanel.player[gamePanel.currentPlayer].setHunger(gamePanel.player[gamePanel.currentPlayer].getHunger() + getKekenyanganValue());
            gamePanel.player[gamePanel.currentPlayer].getInventory().remove(this);
            gamePanel.playSoundEffect(12);
            gamePanel.ui.addMessage("Hunger + " + getKekenyanganValue());
        } else if (gamePanel.player[gamePanel.currentPlayer].getHunger() + getKekenyanganValue() >= 100){
            gamePanel.player[gamePanel.currentPlayer].setHunger(100);
            gamePanel.ui.addMessage("Kamu sudah kenyang!!");
            gamePanel.player[gamePanel.currentPlayer].getInventory().remove(this);
            gamePanel.playSoundEffect(12);
        }
    }
}
