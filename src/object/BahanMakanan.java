package object;

import main.GamePanel;
import object.SuperObject;

import java.awt.*;

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

    @Override
    public void setsolidArea() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setsolidArea'");
    }
}