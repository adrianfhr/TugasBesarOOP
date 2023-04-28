package object;

import main.GamePanel;
import object.SuperObject;

import java.awt.*;


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

}
