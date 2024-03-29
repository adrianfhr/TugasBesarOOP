package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
    
    public BufferedImage scaleImage(BufferedImage image, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, image.getType()) ;
        Graphics2D g2 = scaledImage.createGraphics();
            g2.drawImage(image, 0, 0, width, height, null);
            g2.dispose();

        return scaledImage;
    }

    public static int getXForAlightToRightOfText(String text, int tailX, GamePanel gamePanel, Graphics2D graphics2D) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return tailX - length;
    }

    public static int getXForCenterOfText(String text, GamePanel gamePanel, Graphics2D graphics2D) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return gamePanel.screenWidth / 2 - length / 2;
    }

}
