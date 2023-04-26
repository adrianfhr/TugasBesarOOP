package main;

import java.awt.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Timer;
import entity.Entity;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font arial_40;
    private int playerSlotCol;
    private int playerSlotRow;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 12);
        
    }

    public void draw(Graphics2D g2d){
        this.g2 = g2d;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        //statistic();
        drawCharacterScreen();
        drawInventoryScreen(gamePanel.player, false);
        gameMenuScreen();
        if(gamePanel.isActiveAction){
            drawActiveStateScreen();
        }
        
        if(gamePanel.player.isInteracting && !gamePanel.isActiveAction){
            g2.drawString("Push 'E' for interact", gamePanel.player.screenX - 48, gamePanel.player.screenY - 16 );
        }
    }


    public void gameMenuScreen(){
        int frameX = 40 * gamePanel.tileSize;
        int frameY = (-9) * gamePanel.tileSize;
        int frameWidth = 7 * gamePanel.tileSize;
        int frameHeight = 7 * gamePanel.tileSize; 

        drawSubWindowStat((frameX), frameY, frameWidth, frameHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12));

        int textX = frameX + 20;
        int textY = 46;
        int lineHeight = 20;

        drawTextStat(textX, textY, lineHeight);


    }

    public void drawTextStat(int x, int y, int lineHeight){
        String waktu = String.format("%02d:%02d", (gamePanel.clock/60)%24, (gamePanel.clock%60));
        g2.drawString(waktu, x, y);
        g2.drawString("DAY - " + gamePanel.clock/(60*24),  x, y + lineHeight);
        g2.drawString("FPS: " + gamePanel.fps, x , (y + lineHeight * 2));
        g2.drawString("X: " + (gamePanel.player.worldX/16) + " Y: " + (gamePanel.player.worldY/16 ), x, y + lineHeight * 3);

    }

    public void drawActiveStateScreen(){
        
        int frameX = 31;
        int frameY = 16*17;
        int frameWidth = 10 * gamePanel.tileSize;
        int frameHeight = 5 * gamePanel.tileSize; 

        drawSubWindowActiveAction(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12));

        g2.drawString("Player sedang " + gamePanel.player.getState() + "...",frameX+18 ,frameY + 200);
        
    }

    public void drawInventoryScreen(Entity entity, boolean cursor){
        // Inventroy Box
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if (entity == gamePanel.player){
            frameY = gamePanel.tileSize;
            frameWidth = gamePanel.tileSize * 10;
            frameHeight = gamePanel.tileSize * 15;
        }
        drawSubWindowInventory(frameX + 31, frameY + 10, frameWidth, frameHeight);

        final int slotXStart = frameX + 32;
        final int slotYStart = frameY + 11;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gamePanel.tileSize + 3;
        // // Isi Inventory
        // List<Asset> inventory = entity.getInventory();
        // drawItemsInInventory(entity, slotXStart, slotX, slotY, slotSize, inventory);

        // // Draw Item
        // for (int i = 0; i < gamePanel.player.inventorySize; i++){
        //     g2.drawImage(gamePanel.player.inventory.get(i).down1,slotX, slotY, null);
        // }

    }

    public void drawSubWindowActiveAction(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0);
        g2.setColor(color);
        g2.fillRoundRect(x, y+160, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 165, width - 10, height - 10, 25, 25);
    }

    public void drawSubWindowStat(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 210);
        g2.setColor(color);
        g2.fillRoundRect(x, y+160, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 165, width - 10, height - 10, 25, 25);
    }


    public void drawSubWindowInventory(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 210);
        g2.setColor(color);
        g2.fillRoundRect(x, y+160, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 165, width - 10, height - 10, 25, 25);
    }

    public void drawSubWindowPlayerStat(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 210);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    private void drawCharacterScreen() {
        int frameX = gamePanel.tileSize * 2;
        int frameY = gamePanel.tileSize;
        int frameWidth = gamePanel.tileSize * 10;
        int frameHeight = (int) (gamePanel.tileSize * 10.5);

        drawSubWindowPlayerStat(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12));

        int textX = frameX + 20;
        int textY = frameY + 20 + gamePanel.tileSize;
        int lineHeight = 20;

        drawText(textX, textY, lineHeight);

        int tailX = (frameX + frameWidth) - 30;

        drawValues(textY, lineHeight, tailX);
    }

    public void drawText(int textX, int textY, int lineHeight){
        g2.drawString("Name", textX, textY);
        textY += lineHeight;
        g2.drawString("Job", textX, textY);
        textY += lineHeight;
        g2.drawString("Mood", textX, textY);
        textY += lineHeight;
        g2.drawString("Health", textX, textY);
        textY += lineHeight;
        g2.drawString("Hunger", textX, textY);
        textY += lineHeight;
        g2.drawString("Money", textX, textY);
        textY += lineHeight;
    }

    private void drawValues(int textY, int lineHeight, int tailX) {
        int textX;
        String value;

        value = String.valueOf(gamePanel.player.getName());
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.getJob());
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gamePanel.player.getMood() + "/" + 100;
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gamePanel.player.getHealth() + "/" + 100;
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gamePanel.player.getHunger() + "/" + 100;
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.getMoney());
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
    }

    private void drawItemsInInventory(Entity entity, int slotXStart, int slotX, int slotY, int slotSize, List<Asset> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            Asset object = inventory.get(i);

            // EQUIPPED BOX COLOR
            if (object == gamePanel.player.getCurrentBahanMakanan()
                    || object == gamePanel.player.getCurrentBahanMakanan()) {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gamePanel.tileSize, gamePanel.tileSize, 10, 10);
            }

            g2.drawImage(object.getImage1(), slotX, slotY, null);

            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }
    } //

    public int getItemIndexFromSlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }

    public int getPlayerSlotCol() {
        return playerSlotCol;
    }

    public UI setPlayerSlotCol(int playerSlotCol) {
        this.playerSlotCol = playerSlotCol;
        return this;
    }

    public int getPlayerSlotRow() {
        return playerSlotRow;
    }

    public UI setPlayerSlotRow(int playerSlotRow) {
        this.playerSlotRow = playerSlotRow;
        return this;
    }

}
