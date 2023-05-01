package environment;

import main.GamePanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Lighting {
    GamePanel gamePanel;
    BufferedImage darknessFilter;
    public int dayCounter;
    float filterAlpha;
    public int lightRadius = 500;

    final int day = 0;
    final int dusk  = 1;
    final int night = 2;
    final int dawn  = 3;
    public int dayState = day;
    private Font maruMonica;

    public Lighting(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setLightSource();
        maruMonica = new Font("x12y16pxMaruMonica", Font.PLAIN, 12);
    }

    public void setLightSource(){
        // Bikin Buffered Image
        darknessFilter = new BufferedImage(gamePanel.screenWidth, gamePanel.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        // Get posisi lighting
        int centerX = gamePanel.player[gamePanel.currentPlayer].screenX + (gamePanel.tileSize) / 2;
        int centerY = gamePanel.player[gamePanel.currentPlayer].screenY + (gamePanel.tileSize) / 2;

        // Set warna
        Color color[] = new Color[5];
        float fraction[] = new float[5];

        color [0] = new Color(0, 0, 0, 0f);
        color [1] = new Color(0, 0, 0, 0.25f);
        color [2] = new Color(0, 0, 0, 0.5f);
        color [3] = new Color(0, 0, 0, 0.75f);
        color [4] = new Color(0, 0, 0, 0.98f);

        fraction[0] = 0f;
        fraction[1] = 0.25f;
        fraction[2] = 0.5f;
        fraction[3] = 0.75f;
        fraction[4] = 1f;

        // Set gradasi
        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, lightRadius/2, fraction, color);

        g2.setPaint(gPaint);

        g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        g2.dispose();
    }

    public void update(){
        System.out.println("daycount" + dayCounter);
        if (dayState == day){
            if ((gamePanel.clock/60)%24 == 13){
                dayState = dusk;
                dayCounter = 0;
            }
        }
        else if (dayState == dusk){
            filterAlpha += 0.0001f;
            if (filterAlpha > 1f){
                filterAlpha = 1f;
                dayState = night;
            }
        }
        else if (dayState == night){
            if ((gamePanel.clock/60)%24 == 0){
                dayState = dawn;
                dayCounter = 0;
            }
        }
        else if (dayState == dawn){
            filterAlpha -= 0.0001f;
            if (filterAlpha < 0f){
                filterAlpha = 0;
                dayState = day;
            }
        }
    }


    public void draw (Graphics2D g2){
        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        g2.drawImage(darknessFilter, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        String situation = "";

        switch(dayState){
            case day:
                situation = "Day";
                break;
            case dusk:
                situation = "Dusk";
                break;
            case night:
                situation = "Night";
                break;
            case dawn:
                situation = "Dawn";
                break;
        }
        g2.setFont(g2.getFont().deriveFont(40f));
        g2.drawString(situation, 600, 675);
    }
}
