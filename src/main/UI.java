package main;

import java.awt.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import entity.Entity;
import object.Asset;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font arial_40;
    private Font maruMonica;
    private int playerSlotCol;
    private int playerSlotRow;
    private int commandNumber;
    private int titleScreenState;
    public int slotCol = 0;
    public int slotRow = 0;
    private String currentDialogue;
    private int subState;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        maruMonica = new Font("x12y16pxMaruMonica", Font.PLAIN, 12);
        
    }

    public void draw(Graphics2D g2d){
        this.g2 = g2d;
        setupDefaultGraphics(g2d);
        //statistic();
        if (gamePanel.getGameState() == gamePanel.titleState){
            drawTitleScreen();
        }
        else if (gamePanel.getGameState() == gamePanel.playState || gamePanel.getGameState() == gamePanel.interactObjState){
            drawCharacterScreen();
            //gameStatScreen();
            drawPlayerActiveState();
            if(gamePanel.isActiveAction){
                drawActiveStateScreen();
            }
            
            if(gamePanel.player.isInteracting && gamePanel.getGameState() == gamePanel.playState){
                g2.drawString("Push 'E' for interact", gamePanel.player.screenX - 48, gamePanel.player.screenY - 16 );
            }
        }
        else if (gamePanel.getGameState() == gamePanel.characterState){
            drawInventoryScreen(gamePanel.player, true);
        }
        else if (gamePanel.getGameState() == gamePanel.pauseState){
            drawPauseScreen();
        }
        else if (gamePanel.getGameState() == gamePanel.optionState) {
            drawOptionScreen();
        }
    }

    private void setupDefaultGraphics(Graphics2D graphics2D) {
        graphics2D.setFont(maruMonica);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setColor(Color.WHITE);
    }

    private void drawPlayerActiveState(){
        int frameX = 40 * gamePanel.tileSize;
        int frameY = (-9) * gamePanel.tileSize;
        int frameWidth = 7 * gamePanel.tileSize;
        int frameHeight = 7 * gamePanel.tileSize; 

        drawSubWindowStat((frameX), frameY, frameWidth, frameHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12));
        
        g2.drawString(gamePanel.player.getState() + " : " + gamePanel.player.jamTidur,frameX + 32 ,48);
    }


    private void drawTitleScreen() {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        switch (titleScreenState) {
            case 0 -> drawStartScreen();
            case 1 -> gamePanel.gameState = gamePanel.playState;
        }
        gamePanel.keyHandler.setEnterPressed(false);
    }

    private void drawStartScreen() {

        // TITLE TEXT
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));

        String text = "Sim-Plicity";
        int x = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        int y = gamePanel.tileSize * 20;

        // Shadow
        g2.setColor(Color.GRAY);
        g2.drawString(text, x + 5, y + 5);

        // Text
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // GAME IMAGE
        x = gamePanel.screenWidth / 2 - (gamePanel.tileSize * 2) / 2;
        y += gamePanel.tileSize * 2;
        g2.drawImage(gamePanel.player.down1, x, y, gamePanel.tileSize * 2, gamePanel.tileSize * 2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        y += gamePanel.tileSize * 5;
        g2.drawString(text, x, y);
        if (commandNumber == 0) {
            g2.drawString(">", x - gamePanel.tileSize, y);
            if (gamePanel.keyHandler.isEnterPressed()) {
                titleScreenState = 1;
                commandNumber = 0;
            }
        }

        text = "LOAD GAME";
        x = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        y += gamePanel.tileSize;
        g2.drawString(text, x, y + 35);
        if (commandNumber == 1) {
            g2.drawString(">", x - gamePanel.tileSize, y + 35);
            if (gamePanel.keyHandler.isEnterPressed()) {
                // Later
            }
        }

        text = "QUIT";
        x = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        y += gamePanel.tileSize;
        g2.drawString(text, x, y+70);
        if (commandNumber == 2) {
            g2.drawString(">", x - gamePanel.tileSize, y + 70);
            if (gamePanel.keyHandler.isEnterPressed()) {
                System.exit(0);
            }
        }
    }

    private void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));

        String text = "PAUSED";
        int x = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        int y = gamePanel.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void gameStatScreen(){
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
        int frameY = 2*16;
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
            frameWidth = gamePanel.tileSize * 17;
            frameHeight = gamePanel.tileSize * 15;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        drawSubWindowInventory(frameX + 31, frameY + 10, frameWidth, frameHeight);

        final int slotXStart = frameX + 31;
        final int slotYStart = frameY + 10;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gamePanel.tileSize * 3;
        
        // // // Isi Inventory
        List<Asset> inventory = entity.getInventory();
        drawItemsInInventory(entity, slotXStart, slotX, slotY, slotSize, inventory);

        if (cursor) {
            drawSelectionBox(slotXStart, slotYStart, slotSize, slotCol, slotRow);

            // DESCRIPTION FRAME
            int descriptionFrameX = frameX;
            int descriptionFrameY = frameY + frameHeight;
            int descriptionFrameWidth = frameWidth;
            int descriptionFrameHeight = gamePanel.tileSize * 10;

            drawItemDescriptionText(inventory, descriptionFrameX, descriptionFrameY, descriptionFrameWidth, descriptionFrameHeight, slotCol, slotRow);
        }
    }

    public void drawSubWindowActiveAction(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 210);
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
            if (object == gamePanel.player.getCurrentBahanMakanan()) {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX + 20, slotY + 180, gamePanel.tileSize, gamePanel.tileSize, 10, 10);
            }

            g2.drawImage(object.getImage1(), slotX + 20, slotY + 180, null);

            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }
    } //

    private void drawSelectionBox(int slotXStart, int slotYStart, int slotSize, int slotCol, int slotRow) {
        // CURSOR selection box
        int cursorX = slotXStart + (slotSize * slotCol);
        int cursorY = slotYStart + (slotSize * slotRow);
        int cursorWidth = gamePanel.tileSize;
        int cursorHeight = gamePanel.tileSize;

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX + 15, cursorY + 173, cursorWidth + 25, cursorHeight + 25, 5, 5);
    }

    private void drawItemDescriptionText(List<Asset> inventory, int descriptionFrameX, int descriptionFrameY, int descriptionFrameWidth, int descriptionFrameHeight, int slotCol, int slotRow) {
        // DRAW DESCRIPTION TEXT
        int textX = descriptionFrameX + 50;
        int textY = descriptionFrameY + gamePanel.tileSize;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));

        int itemIndex = getItemIndexFromSlot(slotCol, slotRow);

        if (itemIndex < inventory.size()) {

            drawSubWindowDesc(descriptionFrameX +  31, descriptionFrameY - 30, descriptionFrameWidth, descriptionFrameHeight);

            for (String line : inventory.get(itemIndex).getDescription().split("\n")) {
                g2.drawString(line, textX, textY + 210);
                textY += 25;
            }
        }
    }

    public void drawSubWindowDesc(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 210);
        g2.setColor(color);
        g2.fillRoundRect(x, y+205, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 210, width - 10, height - 10, 25, 25);
    }

    private void drawOptionScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW
        int frameX = gamePanel.tileSize * 14;
        int frameY = gamePanel.tileSize * 9;
        int frameWidth = gamePanel.tileSize * 20;
        int frameHeight = gamePanel.tileSize * 25;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0 -> optionsTop(frameX, frameY);
            case 1 -> optionsFullScreenNotification(frameX, frameY);
            case 2 -> optionsControls(frameX, frameY);
            case 3 -> optionsEndGameConfirmation(frameX, frameY);
        }

        gamePanel.getKeyHandler().setEnterPressed(false);
    }

    private void splitAndDrawDialogue(int x, int y) {
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    private void optionsEndGameConfirmation(int frameX, int frameY) {
        int textX = frameX + gamePanel.tileSize;
        int textY = frameY + gamePanel.tileSize * 3;

        currentDialogue = "Quit the game and \nreturn to the title Screen?";

        splitAndDrawDialogue(textX, textY);

        // YES
        String text = "Yes";
        textX = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        textY += gamePanel.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNumber == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
                gamePanel.setGameState(gamePanel.titleState);
                titleScreenState = 0;
                commandNumber = 0;
                gamePanel.stopMusic();
            }
        }

        // NO
        text = "No";
        textX = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        textY += gamePanel.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNumber == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
                commandNumber = 4;
            }
        }
    }

    private void optionsControls(int frameX, int frameY) {
        int textX;
        int textY;

        // TITLE
        String text = "Controls";
        textX = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        textY = frameY + gamePanel.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gamePanel.tileSize;
        textY += gamePanel.tileSize;
        g2.drawString("Move", textX, textY);
        g2.drawString("WASD", textX + gamePanel.tileSize * 5, textY);
        textY += gamePanel.tileSize;
        g2.drawString("Confirm/Interact", textX, textY);
        g2.drawString("E", textX + gamePanel.tileSize * 5, textY);
        textY += gamePanel.tileSize;
        g2.drawString("Inventory", textX, textY);
        g2.drawString("I", textX + gamePanel.tileSize * 5, textY);
        textY += gamePanel.tileSize;
        g2.drawString("Pause", textX, textY);
        g2.drawString("P", textX + gamePanel.tileSize * 5, textY);

        // BACK
        textX = frameX + gamePanel.tileSize;
        textY = frameY + gamePanel.tileSize * 9;
        g2.drawString("Back", textX, textY);
        g2.drawString(">", textX - 25, textY);
        if (gamePanel.getKeyHandler().isEnterPressed()) {
            subState = 0;
            commandNumber = 3;
        }
    }

    public void optionsFullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gamePanel.tileSize;
        int textY = frameY + gamePanel.tileSize * 3;

        currentDialogue = "The change will take \neffect after restarting \nthe game.";

        splitAndDrawDialogue(textX, textY);

        // BACK
        textY = frameY + gamePanel.tileSize * 9;
        g2.drawString("Back", textX, textY);
        g2.drawString(">", textX - 25, textY);
        if (gamePanel.getKeyHandler().isEnterPressed()) {
            subState = 0;
            commandNumber = 0;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 210);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    private void optionsTop(int frameX, int frameY) {
        int textX;
        int textY;

        // TITLE
        String text = "Options";
        textX = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        textY = frameY + gamePanel.tileSize + 35;
        g2.drawString(text, textX, textY);

        // FULLSCREEN ON/OFF
        textX = frameX + gamePanel.tileSize;
        textY += gamePanel.tileSize;
        g2.drawString("Full Screen", textX, textY + 30);
        if (commandNumber == 0) {
            g2.drawString(">", textX - 25, textY + 30);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                gamePanel.setFullScreenOn(!gamePanel.isFullScreenOn());
                subState = 1;
                commandNumber = 0;
            }
        }

        // MUSIC
        textY += gamePanel.tileSize;
        g2.drawString("Music", textX, textY + 45);
        if (commandNumber == 1) {
            g2.drawString(">", textX - 25, textY + 45);
        }

        // SOUND EFFECT
        textY += gamePanel.tileSize;
        g2.drawString("Sound Effects", textX, textY + 60);
        if (commandNumber == 2) {
            g2.drawString(">", textX - 25, textY + 60);
        }

        // CONTROLS
        textY += gamePanel.tileSize;
        g2.drawString("Controls", textX, textY + 75);
        if (commandNumber == 3) {
            g2.drawString(">", textX - 25, textY + 75);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 2;
                commandNumber = 0;
            }
        }

        // END GAME
        textY += gamePanel.tileSize;
        g2.drawString("End Game", textX, textY + 90);
        if (commandNumber == 4) {
            g2.drawString(">", textX - 25, textY + 90);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 3;
                commandNumber = 0;
            }
        }

        // BACK
        textY += gamePanel.tileSize * 2;
        g2.drawString("Back", textX, textY + 105);
        if (commandNumber == 5) {
            g2.drawString(">", textX - 25, textY + 105);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                gamePanel.setGameState(gamePanel.playState);
                commandNumber = 0;
            }
        }

        // FULLSCREEN CHECK BOX
        textX = frameX + (int) (gamePanel.tileSize * 15);
        textY = frameY + gamePanel.tileSize * 2 + 45;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if (gamePanel.isFullScreenOn()) {
            g2.fillRect(textX, textY, 24, 24);
        }

        // MUSIC VOLUME
        textY += gamePanel.tileSize;
        g2.drawRect(textX, textY + 30, 120, 24); // 120 / 5 = 24

        int volumeWidth = 24 * gamePanel.getMusic().getVolumeScale();
        g2.fillRect(textX, textY + 30, volumeWidth, 24);

        // SOUND EFFECT VOLUME
        textY += gamePanel.tileSize;
        g2.drawRect(textX, textY + 45, 120, 24);

        volumeWidth = 24 * gamePanel.getSoundEffect().getVolumeScale();
        g2.fillRect(textX, textY + 45, volumeWidth, 24);

        // SAVE CONFIGURATION
        gamePanel.getConfig().saveConfig();
    }

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

    public int getCommandNumber() {
        return commandNumber;
    }

    public UI setCommandNumber(int commandNumber) {
        this.commandNumber = commandNumber;
        return this;
    }

    public int getTitleScreenState() {
        return titleScreenState;
    }

    public UI setTitleScreenState(int titleScreenState) {
        this.titleScreenState = titleScreenState;
        return this;
    }
    
    public String getCurrentDialogue() {
        return currentDialogue;
    }

    public UI setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
        return this;
    }

    public int getSubState() {
        return subState;
    }

    public UI setSubState(int subState) {
        this.subState = subState;
        return this;
    }

}
