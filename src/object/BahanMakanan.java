package object;

import main.GamePanel;

public class BahanMakanan extends SuperObject{
    private int harga;
    private int kekenyanganValue;

    public BahanMakanan (GamePanel gamePanel){
        super(gamePanel);
    }

    public void setHarga(int harga){
        this.harga = harga;
    }

    public int getHarga(){
        return harga;
    }

    public void setKekenyanganValue(int kekenyanganValue){
        this.kekenyanganValue = kekenyanganValue;
    }

    public int getKekenyanganValue(){
        return kekenyanganValue;
    }

    public void use(){
        if (gamePanel.player[gamePanel.currentPlayer].getHunger() + getKekenyanganValue() <= 100 && !getName().equals("Whiskas")){
            gamePanel.player[gamePanel.currentPlayer].setHunger(gamePanel.player[gamePanel.currentPlayer].getHunger() + getKekenyanganValue());
            gamePanel.player[gamePanel.currentPlayer].getInventory().remove(this);
            gamePanel.playSoundEffect(12);
            gamePanel.ui.addMessage("Hunger + " + getKekenyanganValue());
        } else if (getName().equals("Whiskas")){
            gamePanel.ui.addMessage("Makanan kucing! jangan dimakan!");
        } else if (gamePanel.player[gamePanel.currentPlayer].getHunger() + getKekenyanganValue() >= 100){
            gamePanel.player[gamePanel.currentPlayer].setHunger(100);
            gamePanel.ui.addMessage("Kamu sudah kenyang!!");
            gamePanel.player[gamePanel.currentPlayer].getInventory().remove(this);
            gamePanel.playSoundEffect(12);
        }
    }

}