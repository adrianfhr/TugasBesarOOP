package main;

import java.awt.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;

import javax.swing.JOptionPane;

import entity.Entity;
import object.SuperObject;
import object.OBJ_Ayam;
import object.OBJ_Bayam;
import object.OBJ_Beef;
import object.OBJ_Bistik;
import object.OBJ_Kacang;
import object.OBJ_Kentang;
import object.OBJ_KomporGas;
import object.OBJ_Nasi;
import object.OBJ_NasiAyam;
import object.OBJ_NasiKari;
import object.OBJ_Susu;
import object.OBJ_SusuKacang;
import object.OBJ_TumisSayur;
import object.OBJ_Wortel;
import object.SuperObject;

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
    private int komporSlotCol;
    private int komporSlotRow;
    private int daganganSlotCol;
    private int daganganSlotRow;
    private List<String> messages = new ArrayList<>();
    private List<Integer> messageCounter = new ArrayList<>();

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        maruMonica = new Font("x12y16pxMaruMonica", Font.PLAIN, 12);
        
    }

    public void draw(Graphics2D g2d){
        this.g2 = g2d;
        setupDefaultGraphics(g2d);
        
        drawMessages();
        //statistic();
        if (gamePanel.getGameState() == gamePanel.titleState){
            drawTitleScreen();
        }
        else if ((gamePanel.getGameState() == gamePanel.playState || gamePanel.getGameState() == gamePanel.interactObjState) && !gamePanel.isInputAction){
            drawCharacterScreen();
            drawMessages();


            
            if(gamePanel.isActiveAction){
                drawActiveStateScreen();
                drawPlayerActiveState();
                drawCharacterScreen();
                drawMessages();
            }else{
                gameStatScreen();
            }
            
            if(gamePanel.player[gamePanel.currentPlayer].isInteracting && gamePanel.getGameState() == gamePanel.playState){
                g2.drawString("Push 'R' for remove", gamePanel.player[gamePanel.currentPlayer].screenX - 48, gamePanel.player[gamePanel.currentPlayer].screenY - 32 );
                g2.drawString("Push 'E' for interact", gamePanel.player[gamePanel.currentPlayer].screenX - 48, gamePanel.player[gamePanel.currentPlayer].screenY - 16 );
            }
        }
        else if (gamePanel.getGameState() == gamePanel.characterState){
            drawInventoryScreen(gamePanel.player[gamePanel.currentPlayer], true);
            drawMessages();
        }
        else if (gamePanel.getGameState() == gamePanel.pauseState){
            drawPauseScreen();
        }
        else if (gamePanel.getGameState() == gamePanel.optionState) {
            drawOptionScreen();
        } else if (gamePanel.getGameState() == gamePanel.masakState){

            if(gamePanel.isActiveAction){
                drawActiveStateScreen();
                drawCharacterScreen();
                drawPlayerActiveState();
            }else{
                masak();
            }
        } else if (gamePanel.getGameState() == gamePanel.gameEventState && !gamePanel.isInputAction){
            drawGameEventScreen();
        } else if (gamePanel.getGameState() == gamePanel.dialogueState) {
            drawDialogueScreen();
        } else if (gamePanel.getGameState() == gamePanel.wifeState){
            drawInteractWifeScreen();
        } else if (gamePanel.getGameState() == gamePanel.makanState){
            if (gamePanel.isActiveAction){
                drawActiveStateScreen();
                drawPlayerActiveState();
                drawCharacterScreen();
            } else{
                drawInventoryScreen(gamePanel.player[gamePanel.currentPlayer], true);
            }
        } else if (gamePanel.getGameState() == gamePanel.catState){
            drawInteractCatScreen();
        } else if (gamePanel.getGameState() == gamePanel.gameOverState){
            gamePanel.stopMusic();
            drawGameOverScreen();
        }
    }

    private void setupDefaultGraphics(Graphics2D graphics2D) {
        graphics2D.setFont(maruMonica);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setColor(Color.WHITE);
    }

    private void drawPlayerActiveState(){
        int frameX = 40 * gamePanel.tileSize / 3;
        int frameY = (-9) * gamePanel.tileSize / 3;
        int frameWidth = 7 * gamePanel.tileSize / 3;
        int frameHeight = 7 * gamePanel.tileSize / 3; 

        drawSubWindowStat((frameX), frameY, frameWidth, frameHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12));
        String jam = "";
        String jamTidur = String.format("%02d:%02d", (gamePanel.player[gamePanel.currentPlayer].jamTidur/60)%24, (gamePanel.player[gamePanel.currentPlayer].jamTidur%60));
        String jamMakan = String.format("%02d:%02d", (gamePanel.player[gamePanel.currentPlayer].jamMakan/60)%24, (gamePanel.player[gamePanel.currentPlayer].jamMakan%60));
        String jamOlahraga = String.format("%02d:%02d", (gamePanel.player[gamePanel.currentPlayer].jamOlahraga/60)%24, (gamePanel.player[gamePanel.currentPlayer].jamOlahraga%60));
        String jamKerja = String.format("%02d:%02d", (gamePanel.player[gamePanel.currentPlayer].jamKerja/60)%24, (gamePanel.player[gamePanel.currentPlayer].jamKerja%60));
        String jamMemasak = String.format("%02d:%02d", (gamePanel.player[gamePanel.currentPlayer].jamMemasak/60)%24, (gamePanel.player[gamePanel.currentPlayer].jamMemasak%60));
        String jamMules = String.format("%02d:%02d", (gamePanel.player[gamePanel.currentPlayer].jamMules/60)%24, (gamePanel.player[gamePanel.currentPlayer].jamMules%60));
        String jamBerkunjung = String.format("%02d:%02d", (gamePanel.player[gamePanel.currentPlayer].jamBerkunjung/60)%24, (gamePanel.player[gamePanel.currentPlayer].jamBerkunjung%60));
        String jamIbadah = String.format("%02d:%02d", (gamePanel.player[gamePanel.currentPlayer].jamIbadah/60)%24, (gamePanel.player[gamePanel.currentPlayer].jamIbadah%60));
        if(gamePanel.player[gamePanel.currentPlayer].getState().equals("Tidur")){
            jam = jamTidur;
        }else if(gamePanel.player[gamePanel.currentPlayer].getState().equals("Memasak")){
             jam = jamMemasak;
        }else if(gamePanel.player[gamePanel.currentPlayer].getState().equals("makan")){
             jam = jamMakan;
        }else if(gamePanel.player[gamePanel.currentPlayer].getState().equals("Olahraga")){
             jam = jamOlahraga;
        }else if(gamePanel.player[gamePanel.currentPlayer].getState().equals("Bekerja")){
             jam = jamKerja;
        }else if(gamePanel.player[gamePanel.currentPlayer].getState().equals("buang air")){
             jam = jamMules ;
        }else if(gamePanel.player[gamePanel.currentPlayer].getState().equals("Berkunjung")){
             jam = jamBerkunjung ;
        } else if(gamePanel.player[gamePanel.currentPlayer].getState().equals("Ibadah")){
            jam = jamIbadah ;
       }
        
         
        
        g2.drawString(gamePanel.player[gamePanel.currentPlayer].getState() + " : " + jam ,frameX + 15 ,75);
    }


    private void drawTitleScreen() {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        switch (titleScreenState) {
            case 0 : 
                drawStartScreen();
                break;
            case 1 :
                
                gamePanel.gameState = gamePanel.playState; 
                gamePanel.isInputAction = true;
                gamePanel.playMusic(0);
                String input = JOptionPane.showInputDialog(null, "Masukkan nama Sim:");
                if(input == null) input = "Player 1";
                gamePanel.player[gamePanel.currentPlayer].setName(input);
                gamePanel.isInputAction = false;
                break;
        }
        gamePanel.keyHandler.setEnterPressed(false);
    }

    private void drawStartScreen() {

        // TITLE TEXT
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));

        String text = "Sim-Plicity";
        int x = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        int y = gamePanel.tileSize / 3 * 20;

        // Shadow
        g2.setColor(Color.GRAY);
        g2.drawString(text, x + 5, y + 5);

        // Text
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // GAME IMAGE
        x = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 3 * 2) / 2;
        y += gamePanel.tileSize / 3 * 2;
        g2.drawImage(gamePanel.player[gamePanel.currentPlayer].down1, x, y, gamePanel.tileSize / 3 * 2, gamePanel.tileSize / 3 * 2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        y += gamePanel.tileSize / 3 * 5;
        g2.drawString(text, x, y);
        if (commandNumber == 0) {
            g2.drawString(">", x - gamePanel.tileSize / 3, y);
            if (gamePanel.keyHandler.isEnterPressed()) {
                titleScreenState = 1;
                commandNumber = 0;
            }
        }

        text = "LOAD GAME";
        x = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        y += gamePanel.tileSize / 3;
        g2.drawString(text, x, y + 35);
        if (commandNumber == 1) {
            g2.drawString(">", x - gamePanel.tileSize / 3, y + 35);
            if (gamePanel.keyHandler.isEnterPressed()) {
                // Later
            }
        }

        text = "QUIT";
        x = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        y += gamePanel.tileSize / 3;
        g2.drawString(text, x, y+70);
        if (commandNumber == 2) {
            g2.drawString(">", x - gamePanel.tileSize / 3, y + 70);
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
        int frameX = 40 * gamePanel.tileSize / 3;
        int frameY = (-9) * gamePanel.tileSize / 3;
        int frameWidth = 7 * gamePanel.tileSize / 3;
        int frameHeight = 7 * gamePanel.tileSize / 3; 

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
        g2.drawString("DAY - " + gamePanel.clock/(60*24)+1,  x, y + lineHeight);
        g2.drawString("FPS: " + gamePanel.fps, x , (y + lineHeight * 2));
        g2.drawString("X: " + (gamePanel.player[gamePanel.currentPlayer].worldX/gamePanel.tileSize) + " Y: " + (gamePanel.player[gamePanel.currentPlayer].worldY/gamePanel.tileSize), x, y + lineHeight * 3);

    }

    public void drawActiveStateScreen(){
        int frameX = 27;
        int frameY = 2;
        int frameWidth = 20 * gamePanel.tileSize / 3;
        int frameHeight = 6 * gamePanel.tileSize / 3; 

        drawSubWindowActiveAction(frameX * 9, frameY - 110, frameWidth, frameHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));

        g2.drawString("Player sedang " + gamePanel.player[gamePanel.currentPlayer].getState() + "...",frameX*9 + 16 ,frameY + 105);
    }

    public void addMessage(String text) {
        messages.add(text);
        messageCounter.add(0);
    }

    private void drawMessages() {
        int messageX = gamePanel.tileSize;
        int messageY = gamePanel.tileSize * 7;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i) != null) {
                g2.setColor(Color.BLACK);
                g2.drawString(messages.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.WHITE);
                g2.drawString(messages.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if (messageCounter.get(i) > 180) {
                    messages.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    private void drawDialogueScreen() {
        int x = gamePanel.tileSize * 3;
        int y = gamePanel.tileSize / 2;
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 6);
        int height = gamePanel.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;

        splitAndDrawDialogue(x-55, y-30);
    }

    public void drawInventoryScreen(Entity entity, boolean cursor){
        // Inventroy Box
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if (entity == gamePanel.player[gamePanel.currentPlayer]){
            frameY = gamePanel.tileSize / 3;
            frameWidth = gamePanel.tileSize / 3 * 17;
            frameHeight = gamePanel.tileSize / 3 * 15;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        } else{
            frameX = gamePanel.tileSize * 2;
            slotCol = komporSlotCol;
            slotRow = komporSlotRow;
        }
        drawSubWindowInventory(frameX + 31, frameY + 10, frameWidth, frameHeight);

        final int slotXStart = frameX + 31;
        final int slotYStart = frameY + 10;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gamePanel.tileSize / 3 * 3;
        
        // // // Isi Inventory
        List<SuperObject> inventory = entity.getInventory();
        drawItemsInInventory(entity, slotXStart, slotX, slotY, slotSize, inventory);

        if (cursor) {
            drawSelectionBox(slotXStart, slotYStart, slotSize, slotCol, slotRow);

            // DESCRIPTION FRAME
            int descriptionFrameX = frameX;
            int descriptionFrameY = frameY + frameHeight;
            int descriptionFrameWidth = frameWidth;
            int descriptionFrameHeight = gamePanel.tileSize / 3 * 10;

            drawItemDescriptionText(inventory, descriptionFrameX, descriptionFrameY, descriptionFrameWidth, descriptionFrameHeight, slotCol, slotRow);
        }
    }

    public void drawDaganganScreen(Entity entity, boolean cursor){
        // Inventroy Box
        int frameX = 190;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if (entity == gamePanel.player[gamePanel.currentPlayer]){
            frameY = gamePanel.tileSize / 3;
            frameWidth = gamePanel.tileSize / 3 * 17;
            frameHeight = gamePanel.tileSize / 3 * 15;
            slotCol = daganganSlotCol;
            slotRow = daganganSlotRow;
        }

        final int slotXStart = frameX + 31;
        final int slotYStart = frameY + 10;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gamePanel.tileSize / 2 * 3;
        
        // // // Isi Inventory
        List<SuperObject> dagangan = entity.getDagangan();

        drawItemsInInventory(entity, slotXStart, slotX, slotY, slotSize, dagangan);

        // DRAW HINT WINDOW
        int x = gamePanel.tileSize;
        int y = gamePanel.tileSize * 9;

        // DRAW PLAYER COIN WINDOW
        x = gamePanel.tileSize * 10;
        g2.drawString("Uang : " + gamePanel.player[gamePanel.currentPlayer].getMoney(), x, y + 60);

        if (cursor) {
            drawSelectionBox(slotXStart, slotYStart, slotSize, slotCol, slotRow);

            // DESCRIPTION FRAME
            int descriptionFrameX = frameX;
            int descriptionFrameY = frameY + frameHeight;
            int descriptionFrameWidth = frameWidth;
            int descriptionFrameHeight = gamePanel.tileSize / 3 * 10;
            

            drawItemDescriptionText(dagangan, descriptionFrameX-45, descriptionFrameY + 70, descriptionFrameWidth, descriptionFrameHeight, slotCol, slotRow);
        }
    }

    public void drawSubWindowDagangan(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 210);
        g2.setColor(color);
        g2.fillRoundRect(x, y+160, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 165, width - 10, height - 10, 25, 25);
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

    public void drawSubWindowMenu(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 210);
        g2.setColor(color);
        g2.fillRoundRect(x+355, y+140, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 360, y + 145, width - 10, height - 10, 25, 25);
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
        int frameX = gamePanel.tileSize / 3 * 2;
        int frameY = gamePanel.tileSize / 3;
        int frameWidth = gamePanel.tileSize / 3 * 10;
        int frameHeight = (int) (gamePanel.tileSize / 3 * 15);

        drawSubWindowPlayerStat(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12));

        int textX = frameX + 20;
        int textY = frameY + 20 + gamePanel.tileSize / 3;
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
        textY += lineHeight + 15;
        g2.drawString("Harus Tidur", textX, textY);
        textY += lineHeight;
        g2.drawString("Harus Poop", textX, textY);
        textY += lineHeight;
        g2.drawString("Renovasi", textX, textY);
        textY += lineHeight;
    }

    private void drawValues(int textY, int lineHeight, int tailX) {
        int textX;
        String value;
        String waktuMules = String.format("%02d:%02d", (gamePanel.player[gamePanel.currentPlayer].jamTidakMules/60)%24, (gamePanel.player[gamePanel.currentPlayer].jamTidakMules%60));
        String waktuTidur = String.format("%02d:%02d", (gamePanel.player[gamePanel.currentPlayer].jamTidakTidur/60)%24, (gamePanel.player[gamePanel.currentPlayer].jamTidakTidur%60));
        String waktuUpgrade = String.format("%02d:%02d", (gamePanel.player[gamePanel.currentPlayer].jamUpgrade/60), (gamePanel.player[gamePanel.currentPlayer].jamUpgrade%60));

        value = String.valueOf(gamePanel.player[gamePanel.currentPlayer].getName());
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player[gamePanel.currentPlayer].getJob());
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gamePanel.player[gamePanel.currentPlayer].getMood() + "/" + 100;
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gamePanel.player[gamePanel.currentPlayer].getHealth() + "/" + 100;
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gamePanel.player[gamePanel.currentPlayer].getHunger() + "/" + 100;
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player[gamePanel.currentPlayer].getMoney());
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight + 15;

        value = String.valueOf(waktuTidur);
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(waktuMules);
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(waktuUpgrade);
        textX = UtilityTool.getXForAlightToRightOfText(value, tailX, gamePanel, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
    }

    private void drawItemsInInventory(Entity entity, int slotXStart, int slotX, int slotY, int slotSize, List<SuperObject> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            SuperObject object = inventory.get(i);

            // EQUIPPED BOX COLOR
            if (object == gamePanel.player[gamePanel.currentPlayer].getCurrentBahanMakanan()) {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX + 20, slotY + 180, gamePanel.tileSize / 3, gamePanel.tileSize / 3, 10, 10);
            }

            g2.drawImage(object.getImage1(), slotX + 20, slotY + 180, null);

            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }
    } //

    private void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        int textX;
        int textY;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        // TITLE TEXT
        text = "Game Over";

        // Shadow
        g2.setColor(Color.black);
        textX = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        textY = gamePanel.tileSize * 4;
        g2.drawString(text, textX, textY);

        // Text
        g2.setColor(Color.WHITE);
        g2.drawString(text, textX - 4, textY - 4);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
        text = "Anda mati karena " + gamePanel.player[gamePanel.currentPlayer].getDeath() + ".";

        // Shadow
        g2.setColor(Color.black);
        textX = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        textY = gamePanel.tileSize * 5;
        g2.drawString(text, textX, textY);

        // Text
        g2.setColor(Color.WHITE);
        g2.drawString(text, textX - 4, textY - 4);

        // RETRY
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        textX = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        textY += gamePanel.tileSize * 4;
        g2.drawString(text, textX, textY);
        if (commandNumber == 0) {
            g2.drawString(">", textX - 40, textY);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                commandNumber = 0;
                gamePanel.retry();
            }
        }

        // BACK TO TITLE
        text = "Quit";
        textX = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        textY += 55;
        g2.drawString(text, textX, textY);
        if (commandNumber == 1) {
            g2.drawString(">", textX - 40, textY);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                System.exit(0);
            }
        }

        gamePanel.getKeyHandler().setEnterPressed(false);
    }

    private void drawSelectionBox(int slotXStart, int slotYStart, int slotSize, int slotCol, int slotRow) {
        // CURSOR selection box
        int cursorX = slotXStart + (slotSize * slotCol);
        int cursorY = slotYStart + (slotSize * slotRow);
        int cursorWidth = gamePanel.tileSize / 3;
        int cursorHeight = gamePanel.tileSize / 3;

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX + 15, cursorY + 173, cursorWidth + 25, cursorHeight + 25, 5, 5);
    }

    private void drawItemDescriptionText(List<SuperObject> inventory, int descriptionFrameX, int descriptionFrameY, int descriptionFrameWidth, int descriptionFrameHeight, int slotCol, int slotRow) {
        // DRAW DESCRIPTION TEXT
        int textX = descriptionFrameX + 50;
        int textY = descriptionFrameY + gamePanel.tileSize / 3;

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

    private void drawGameEventScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW
        int frameX = gamePanel.tileSize / 3 * 11;
        int frameY = gamePanel.tileSize / 3 * 9;
        int frameWidth = gamePanel.tileSize / 3 * 25;
        int frameHeight = gamePanel.tileSize / 3 * 20;

        drawSubWindow(frameX, frameY, frameWidth + 50, frameHeight + 100);

        switch (subState) {
            case 0 -> gameEventTop(frameX, frameY);
            case 1 -> drawDaganganScreen(gamePanel.player[gamePanel.currentPlayer], true);
            case 2 -> selectJobScreen(frameX, frameY);
            case 3 -> selectUpgradeScreen(frameX, frameY);
        }

        gamePanel.getKeyHandler().setEnterPressed(false);
    }

    private void selectJobScreen(int frameX, int frameY){
        int textX;
        int textY;

        // TITLE
        String text = "Select Your Job!";
        textX = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        textY = frameY + gamePanel.tileSize / 3 + 35;
        g2.drawString(text, textX + 13, textY);

        // BELI BARANG
        textX = frameX + gamePanel.tileSize / 3;
        textY += gamePanel.tileSize / 2;
        g2.drawString("Badut Sulap", textX + 30, textY + 30);
        if (commandNumber == 0) {
            g2.drawString(">", textX + 10, textY + 30);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
               if(gamePanel.player[gamePanel.currentPlayer].getJob().equals("Badut Sulap")){
                gamePanel.isActiveAction = true;
                gamePanel.player[gamePanel.currentPlayer].setState("Bekerja");
                gamePanel.setGameState(gamePanel.dialogueState);
                currentDialogue = "Hari hari jadi badut :((";
                splitAndDrawDialogue(textX, textY);
               }else{
                gamePanel.setGameState(gamePanel.dialogueState);
                currentDialogue = "Maaf anda bukanlah\nbadut yang layak";
                splitAndDrawDialogue(textX, textY);
               }
            }
        }

        // KERJA
        textY += gamePanel.tileSize / 2;
        g2.drawString("Koki", textX + 30, textY + 45);
        if (commandNumber == 1) {
            g2.drawString(">", textX + 10, textY + 45);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                if(gamePanel.player[gamePanel.currentPlayer].getJob().equals("Koki")){
                    gamePanel.isActiveAction = true;
                    gamePanel.player[gamePanel.currentPlayer].setState("Bekerja");
                    gamePanel.setGameState(gamePanel.dialogueState);
                    currentDialogue = "Hope Gordon Ramsay won't\nkill me this time...";
                    splitAndDrawDialogue(textX, textY);
                   }else{
                    gamePanel.setGameState(gamePanel.dialogueState);
                    currentDialogue = "Maaf anda bukanlah koki yang layak";
                    splitAndDrawDialogue(textX, textY);
                   }
            }
        }

        // UPGRADE RUMAH
        textY += gamePanel.tileSize / 2;
        g2.drawString("Polisi", textX + 30, textY + 60);
        if (commandNumber == 2) {
            g2.drawString(">", textX + 10, textY + 60);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                if(gamePanel.player[gamePanel.currentPlayer].getJob().equals("Polisi")){
                    gamePanel.isActiveAction = true;
                    gamePanel.player[gamePanel.currentPlayer].setState("Bekerja");
                    gamePanel.setGameState(gamePanel.dialogueState);
                    currentDialogue = "Siap mengayomi dan melindungi\nmasyarakat!!";
                    splitAndDrawDialogue(textX, textY);
                }else{
                    gamePanel.player[gamePanel.currentPlayer].setJob("Polisi");
                    gamePanel.setGameState(gamePanel.playState);
                }
            }
        }

        // NEW PLAYER
        textY += gamePanel.tileSize / 2;
        g2.drawString("Programmer", textX + 30, textY + 75);
        if (commandNumber == 3) {
            g2.drawString(">", textX + 10, textY + 75);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                if(gamePanel.player[gamePanel.currentPlayer].getJob().equals("Programmer")){
                    gamePanel.isActiveAction = true;
                    gamePanel.player[gamePanel.currentPlayer].setState("Bekerja");
                    gamePanel.setGameState(gamePanel.dialogueState);
                    currentDialogue = "Not sure if I am a good\nprogrammer or good at googling...";
                    splitAndDrawDialogue(textX, textY);
                }else{
                    gamePanel.setGameState(gamePanel.dialogueState);
                    currentDialogue = "Maaf anda bukanlah\nprogrammer yang layak";
                    splitAndDrawDialogue(textX, textY);
                } 
            }
        }

        // NEW PLAYER
        textY += gamePanel.tileSize / 2;
        g2.drawString("Dokter", textX + 30, textY + 90);
        if (commandNumber == 4) {
            g2.drawString(">", textX + 10, textY + 90);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                if(gamePanel.player[gamePanel.currentPlayer].getJob().equals("Dokter")){
                    gamePanel.isActiveAction = true;
                    gamePanel.player[gamePanel.currentPlayer].setState("Bekerja");
                    gamePanel.setGameState(gamePanel.dialogueState);
                    currentDialogue = "Kami sudah berusaha semaksimal\nmungkin...";
                splitAndDrawDialogue(textX, textY);
                }else{
                    gamePanel.setGameState(gamePanel.dialogueState);
                    currentDialogue = "Maaf anda bukanlah\ndokter yang layak";
                    splitAndDrawDialogue(textX, textY);
                } 
                
            }
        }

        // BACK
        textX = frameX + gamePanel.tileSize / 3;
        textY = frameY + gamePanel.tileSize / 3 * 9;
        g2.drawString("Back", textX + gamePanel.tileSize / 3 * 2, textY + 200);
        if (commandNumber == 5) {
            g2.drawString(">", textX + 9, textY + 200);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
                commandNumber = 0;
            }
        }
    }

    private void selectUpgradeScreen(int frameX, int frameY){
        int textX;
        int textY;

        // TITLE
        String text = "Select Your Room";
        textX = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        textY = frameY + gamePanel.tileSize / 3 + 35;
        g2.drawString(text, textX + 13, textY);

        // BELI BARANG
        textX = frameX + gamePanel.tileSize / 3;
        textY += gamePanel.tileSize / 2;
        g2.drawString("Atas", textX + 30, textY + 30);
        if (commandNumber == 0) {
            g2.drawString(">", textX + 10, textY + 30);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                if (gamePanel.player[gamePanel.currentPlayer].getMoney() >= 1500 && !gamePanel.isPassiveAction){
                    gamePanel.player[gamePanel.currentPlayer].setMoney(gamePanel.player[gamePanel.currentPlayer].getMoney() - 1500);
                    gamePanel.playSoundEffect(18);
                    gamePanel.isPassiveAction = true;
                    gamePanel.isAtas = true;
                    addMessage("Silahkan tunggu " + gamePanel.player[gamePanel.currentPlayer].jamUpgrade/60 + " jam");
                    addMessage("Money - 1500");
                } else if (gamePanel.isPassiveAction){
                    addMessage("Anda sedang melakukan upgrade");
                    addMessage("Silakan tunggu selesai!");
                    gamePanel.playSoundEffect(19);
                }
                else{
                    addMessage("Uang anda tidak cukup");
                    gamePanel.playSoundEffect(19);
                }
            }
        }

        // KERJA
        textY += gamePanel.tileSize / 2;
        g2.drawString("Kanan", textX + 30, textY + 45);
        if (commandNumber == 1) {
            g2.drawString(">", textX + 10, textY + 45);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                if (gamePanel.player[gamePanel.currentPlayer].getMoney() >= 1500 && !gamePanel.isPassiveAction){
                    gamePanel.player[gamePanel.currentPlayer].setMoney(gamePanel.player[gamePanel.currentPlayer].getMoney() - 1500);
                    gamePanel.playSoundEffect(18);
                    gamePanel.isPassiveAction = true;
                    gamePanel.isKanan = true;
                    addMessage("Silahkan tunggu " + gamePanel.player[gamePanel.currentPlayer].jamUpgrade/60 + " jam");
                    addMessage("Money - 1500");
                } else if (gamePanel.isPassiveAction){
                    addMessage("Anda sedang melakukan upgrade");
                    addMessage("Silakan tunggu selesai!");
                    gamePanel.playSoundEffect(19);
                }
                else{
                    addMessage("Uang anda tidak cukup");
                    gamePanel.playSoundEffect(19);
                }
            }
        }

        // UPGRADE RUMAH
        textY += gamePanel.tileSize / 2;
        g2.drawString("Kiri", textX + 30, textY + 60);
        if (commandNumber == 2) {
            g2.drawString(">", textX + 10, textY + 60);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                if (gamePanel.player[gamePanel.currentPlayer].getMoney() >= 1500 && !gamePanel.isPassiveAction){
                    gamePanel.player[gamePanel.currentPlayer].setMoney(gamePanel.player[gamePanel.currentPlayer].getMoney() - 1500);
                    gamePanel.playSoundEffect(18);
                    gamePanel.isPassiveAction = true;
                    gamePanel.isKiri = true;
                    addMessage("Silahkan tunggu " + gamePanel.player[gamePanel.currentPlayer].jamUpgrade/60 + " jam");
                    addMessage("Money - 1500");
                } else if (gamePanel.isPassiveAction){
                    addMessage("Anda sedang melakukan upgrade");
                    addMessage("Silakan tunggu selesai!");
                    gamePanel.playSoundEffect(19);
                }
                else{
                    addMessage("Uang anda tidak cukup");
                    gamePanel.playSoundEffect(19);
                }
            }
        }

        // BACK
        textX = frameX + gamePanel.tileSize / 3;
        textY = frameY + gamePanel.tileSize / 3 * 9;
        g2.drawString("Back", textX + gamePanel.tileSize / 3 * 2, textY + 200);
        if (commandNumber == 3) {
            g2.drawString(">", textX + 9, textY + 200);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
                commandNumber = 0;
            }
        }
    }

    private void gameEventTop(int frameX, int frameY){
        int textX;
        int textY;

        // TITLE
        String text = "Actions";
        textX = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        textY = frameY + gamePanel.tileSize / 3 + 35;
        g2.drawString(text, textX + 13, textY);

        // BELI BARANG
        textX = frameX + gamePanel.tileSize / 3;
        textY += gamePanel.tileSize / 2;
        g2.drawString("Beli Barang", textX + 30, textY + 30);
        if (commandNumber == 0) {
            g2.drawString(">", textX + 10, textY + 30);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 1;
                commandNumber = 0;
            }
        }

        // KERJA
        textY += gamePanel.tileSize / 2;
        g2.drawString("Kerja", textX + 30, textY + 45);
        if (commandNumber == 1) {
            g2.drawString(">", textX + 10, textY + 45);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 2;
                commandNumber = 0;
            }
        }

        // UPGRADE RUMAH
        textY += gamePanel.tileSize / 2;
        g2.drawString("Upgrade Rumah", textX + 30, textY + 60);
        if (commandNumber == 2) {
            g2.drawString(">", textX + 10, textY + 60);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 3;
                commandNumber = 0;
            }
        }

        // NEW PLAYER
        textY += gamePanel.tileSize / 2;
        g2.drawString("New Player", textX + 30, textY + 75);
        if (commandNumber == 3) {
            g2.drawString(">", textX + 10, textY + 75);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
                commandNumber = 0;
                gamePanel.isInputAction = true;
                gamePanel.makePlayer();

            }
        }

        // CHANGE PLAYER
        textY += gamePanel.tileSize / 2;
        g2.drawString("Change Player", textX + 30, textY + 90);
        if (commandNumber == 4) {
            g2.drawString(">", textX + 10, textY + 90);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
                commandNumber = 0;
                gamePanel.gameState = gamePanel.playState;
                
                gamePanel.changePlayer();
              
            }
        }

        // PUNYA ISTRI
        textY += gamePanel.tileSize / 2;
        g2.drawString("Pindah Ruangan", textX + 30, textY + 105);
        if (commandNumber == 5) {
            g2.drawString(">", textX + 10, textY + 105);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
                commandNumber = 0;
                gamePanel.setGameState(gamePanel.playState);
                gamePanel.pindahRuangan();
            }
        }

        // BACK
        textY += gamePanel.tileSize / 3 * 2;
        g2.drawString("Back", textX + 30, textY + 135);
        if (commandNumber == 6) {
            g2.drawString(">", textX + 10, textY + 135);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                gamePanel.setGameState(gamePanel.playState);
                commandNumber = 0;
            }
        }
    }

    private void drawOptionScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW
        int frameX = gamePanel.tileSize / 3 * 12;
        int frameY = gamePanel.tileSize / 3 * 9;
        int frameWidth = gamePanel.tileSize / 3 * 25;
        int frameHeight = gamePanel.tileSize / 3 * 20;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0 -> optionsTop(frameX, frameY);
            case 1 -> optionsFullScreenNotification(frameX, frameY);
            case 2 -> optionsControls(frameX, frameY);
            case 3 -> optionsEndGameConfirmation(frameX, frameY);
        }

        gamePanel.getKeyHandler().setEnterPressed(false);
    }

    private void drawInteractWifeScreen() {
        switch (subState) {
            case 0 -> interactWifeSelect();
        }
        gamePanel.getKeyHandler().setEnterPressed(false);
    }

    private void interactWifeSelect() {
        gamePanel.player[gamePanel.currentPlayer].interactWithNPC(commandNumber);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        // DRAW WINDOW
        int x = gamePanel.tileSize * 5;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.tileSize * 3;
        int height = (int) (gamePanel.tileSize * 3);
        drawSubWindow(x, y, width, height);

        // DRAW TEXT
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        g2.drawString("Talk", x-20, y-10);
        if (commandNumber == 0) {
            g2.drawString(">", x - 30, y-10);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
                gamePanel.setGameState(gamePanel.dialogueState);
                currentDialogue = "Hi! you look good today!";
                gamePanel.playSoundEffect(26);
            }
        }
        y += gamePanel.tileSize;
        g2.drawString("Woohoo", x-20, y-30);
        if (commandNumber == 1) {
            g2.drawString(">", x - 30, y-30);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
                gamePanel.setGameState(gamePanel.dialogueState);
                if (gamePanel.currentMap == 0){
                    currentDialogue = "Ahh.. Jangan dong...";
                    gamePanel.playSoundEffect(15);
                } else if (gamePanel.currentMap == 1){
                    currentDialogue = "Yes, Daddy!";
                }
            }
        }

        y += gamePanel.tileSize;
        g2.drawString("Leave", x-20, y-30);
        if (commandNumber == 2) {
            g2.drawString(">", x - 30, y-30);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                commandNumber = 0;
                subState = 0;
                gamePanel.setGameState(gamePanel.dialogueState);
                currentDialogue = "See u later!";
                gamePanel.playSoundEffect(27);
            }
        }

    }

    private void interactObjSelect() {
        gamePanel.player[gamePanel.currentPlayer].interactWithNPC(commandNumber);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        // DRAW WINDOW
        int x = gamePanel.tileSize * 5;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.tileSize * 3;
        int height = (int) (gamePanel.tileSize * 3.5);
        drawSubWindow(x, y, width, height);

        // DRAW TEXT
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        g2.drawString("Talk", x-20, y-10);
        if (commandNumber == 0) {
            g2.drawString(">", x - 30, y-10);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
                gamePanel.setGameState(gamePanel.dialogueState);
                currentDialogue = "Hi! you look good today!";
            }
        }
        y += gamePanel.tileSize;
        g2.drawString("Woohoo", x-20, y-30);
        if (commandNumber == 1) {
            g2.drawString(">", x - 30, y-30);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
                gamePanel.setGameState(gamePanel.dialogueState);
                if (gamePanel.currentMap == 0){
                    currentDialogue = "Ahh.. Jangan dong...";
                    gamePanel.playSoundEffect(15);
                } else if (gamePanel.currentMap == 1){
                    currentDialogue = "Yes, Daddy!";
                }
            }
        }

        y += gamePanel.tileSize;
        g2.drawString("Suruh pulang", x-20, y-50);
        if (commandNumber == 2) {
            g2.drawString(">", x - 30, y-50);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                commandNumber = 0;
                subState = 0;
                gamePanel.npc[0].teleportNPC(48, 48, 0);
            }
        }

        y += gamePanel.tileSize;
        g2.drawString("Leave", x-20, y-55);
        if (commandNumber == 3) {
            g2.drawString(">", x - 30, y-55);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                commandNumber = 0;
                subState = 0;
                gamePanel.setGameState(gamePanel.playState);
            }
        }
    }

    private void drawInteractCatScreen() {
        switch (subState) {
            case 0 -> interactCatSelect();
        }
        gamePanel.getKeyHandler().setEnterPressed(false);
    }

    private void interactCatSelect() {
        // gamePanel.player[gamePanel.currentPlayer].interactWithCat(commandNumber);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        // DRAW WINDOW
        int x = gamePanel.tileSize * 5;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.tileSize * 3;
        int height = (int) (gamePanel.tileSize * 3.5);
        drawSubWindow(x, y, width, height);

        // DRAW TEXT
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        g2.drawString("Pet", x-20, y-10);
        if (commandNumber == 0) {
            g2.drawString(">", x - 30, y-10);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
                gamePanel.setGameState(gamePanel.dialogueState);
                currentDialogue = "Meow Meow!";
            }
        }
        y += gamePanel.tileSize;
        g2.drawString("Kasih Makan", x-20, y-20);
        if (commandNumber == 1) {
            g2.drawString(">", x - 30, y-20);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
                gamePanel.player[gamePanel.currentPlayer].interactWithCat(1);
            }
        }

        y += gamePanel.tileSize;
        g2.drawString("Leave", x-20, y);
        if (commandNumber == 2) {
            g2.drawString(">", x - 30, y);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                commandNumber = 0;
                subState = 0;
                gamePanel.setGameState(gamePanel.dialogueState);
                currentDialogue = "Meow!";
            }
        }

    }

    private void splitAndDrawDialogue(int x, int y) {
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x+35, y + 60);
            y += 40;
        }
    }

    private void optionsEndGameConfirmation(int frameX, int frameY) {
        int textX = frameX + gamePanel.tileSize / 3;
        int textY = frameY + gamePanel.tileSize / 3 * 3;

        currentDialogue = "Quit the game and \nreturn to the title Screen?";

        splitAndDrawDialogue(textX, textY);

        // YES
        String text = "Yes";
        textX = UtilityTool.getXForCenterOfText(text, gamePanel, g2);
        textY += gamePanel.tileSize / 3 * 3;
        g2.drawString(text, textX, textY + 105);
        if (commandNumber == 0) {
            g2.drawString(">", textX - 25, textY + 105);
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
        textY += gamePanel.tileSize / 3;
        g2.drawString(text, textX, textY + 125);
        if (commandNumber == 1) {
            g2.drawString(">", textX - 25, textY +125);
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
        textY = frameY + gamePanel.tileSize / 3;
        g2.drawString(text, textX, textY + 30);

        textX = frameX + gamePanel.tileSize / 3;
        textY += gamePanel.tileSize / 3;
        g2.drawString("Move", textX + gamePanel.tileSize / 3 * 2, textY + 60);
        g2.drawString("WASD", textX + gamePanel.tileSize / 3 * 16, textY + 60);
        textY += gamePanel.tileSize / 3;
        g2.drawString("Confirm/Interact", textX + gamePanel.tileSize / 3 * 2, textY + 75);
        g2.drawString("E", textX + gamePanel.tileSize / 3 * 18, textY + 75);
        textY += gamePanel.tileSize / 3;
        g2.drawString("Inventory", textX + gamePanel.tileSize / 3 * 2, textY + 90);
        g2.drawString("I", textX + gamePanel.tileSize / 3 * 18, textY + 90);
        textY += gamePanel.tileSize / 3;
        g2.drawString("Pause", textX + gamePanel.tileSize / 3 * 2, textY + 105);
        g2.drawString("P", textX + gamePanel.tileSize / 3 * 18, textY + 105);
        textY += gamePanel.tileSize / 3;
        g2.drawString("Remove Object", textX + gamePanel.tileSize / 3 * 2, textY + 120);
        g2.drawString("R", textX + gamePanel.tileSize / 3 * 18, textY + 120);
        textY += gamePanel.tileSize / 3;
        g2.drawString("Actions", textX + gamePanel.tileSize / 3 * 2, textY + 135);
        g2.drawString("SPACE", textX + gamePanel.tileSize / 3 * 16, textY + 135);

        // BACK
        textX = frameX + gamePanel.tileSize / 3;
        textY = frameY + gamePanel.tileSize / 3 * 9;
        g2.drawString("Back", textX + gamePanel.tileSize / 3 * 2, textY + 150);
        g2.drawString(">", textX + 10, textY + 150);
        if (gamePanel.getKeyHandler().isEnterPressed()) {
            subState = 0;
            commandNumber = 3;
        }
    }

    public void optionsFullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gamePanel.tileSize / 3;
        int textY = frameY + gamePanel.tileSize / 3 * 3;

        currentDialogue = "The change will take \neffect after restarting \nthe game.";

        splitAndDrawDialogue(textX, textY-20);

        // BACK
        textY = frameY + gamePanel.tileSize / 3 * 9;
        g2.drawString("Back", textX+50, textY + 100);
        g2.drawString(">", textX+25, textY+100);
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
        textY = frameY + gamePanel.tileSize / 3 + 35;
        g2.drawString(text, textX + 10, textY);

        // FULLSCREEN ON/OFF
        textX = frameX + gamePanel.tileSize / 3;
        textY += gamePanel.tileSize / 3;
        g2.drawString("Full Screen", textX + 30, textY + 30);
        if (commandNumber == 0) {
            g2.drawString(">", textX + 10, textY + 30);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                gamePanel.setFullScreenOn(!gamePanel.isFullScreenOn());
                subState = 1;
                commandNumber = 0;
            }
        }

        // MUSIC
        textY += gamePanel.tileSize / 3;
        g2.drawString("Music", textX + 30, textY + 45);
        if (commandNumber == 1) {
            g2.drawString(">", textX + 10, textY + 45);
        }

        // SOUND EFFECT
        textY += gamePanel.tileSize / 3;
        g2.drawString("Sound Effects", textX + 30, textY + 60);
        if (commandNumber == 2) {
            g2.drawString(">", textX + 10, textY + 60);
        }

        // CONTROLS
        textY += gamePanel.tileSize / 3;
        g2.drawString("Controls", textX + 30, textY + 75);
        if (commandNumber == 3) {
            g2.drawString(">", textX + 10, textY + 75);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 2;
                commandNumber = 0;
            }
        }

        // END GAME
        textY += gamePanel.tileSize / 3;
        g2.drawString("End Game", textX + 30, textY + 90);
        if (commandNumber == 4) {
            g2.drawString(">", textX + 10, textY + 90);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 3;
                commandNumber = 0;
            }
        }

        // BACK
        textY += gamePanel.tileSize / 3 * 2;
        g2.drawString("Back", textX + 30, textY + 105);
        if (commandNumber == 5) {
            g2.drawString(">", textX + 10, textY + 105);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                gamePanel.setGameState(gamePanel.playState);
                commandNumber = 0;
            }
        }

        // FULLSCREEN CHECK BOX
        textX = frameX + (int) (gamePanel.tileSize / 3 * 15);
        textY = frameY + gamePanel.tileSize / 3 * 2 + 45;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if (gamePanel.isFullScreenOn()) {
            g2.fillRect(textX, textY, 24, 24);
        }

        // MUSIC VOLUME
        textY += gamePanel.tileSize / 3;
        g2.drawRect(textX, textY + 15, 120, 24); // 120 / 5 = 24

        int volumeWidth = 24 * gamePanel.getMusic().getVolumeScale();
        g2.fillRect(textX, textY + 15, volumeWidth, 24);

        // SOUND EFFECT VOLUME
        textY += gamePanel.tileSize / 3;
        g2.drawRect(textX, textY + 30, 120, 24);

        volumeWidth = 24 * gamePanel.getSoundEffect().getVolumeScale();
        g2.fillRect(textX, textY + 30, volumeWidth, 24);

        // SAVE CONFIGURATION
        gamePanel.getConfig().saveConfig();
    }

    public void masak() {

        // DRAW PLAYER INVENTORY
        drawInventoryScreen(gamePanel.player[gamePanel.currentPlayer], false);

        SuperObject kompor = new OBJ_KomporGas(gamePanel);
        // DRAW NPC INVENTORY
        drawInventoryKompor(kompor, true);

        // DRAW HINT WINDOW
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize;
        int width = gamePanel.tileSize * 6;
        int height = gamePanel.tileSize * 2;
        g2.drawString("[E] Back", x + 370, y + 350);
        g2.drawString("[ENTER] Select", x + 370, y + 375);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexFromSlot(komporSlotCol, komporSlotRow);

        if (itemIndex < kompor.getMenu().size()) {
            x = (int) (gamePanel.tileSize * 5.5);
            y = (int) (gamePanel.tileSize * 5.5);
            width = (int) (gamePanel.tileSize * 1.5);
            height = gamePanel.tileSize;


            // BUY
            
        }
    }

    public void drawInventoryKompor(SuperObject kompor, boolean cursor){
        // Inventroy Box
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        frameX = gamePanel.tileSize * 12;
        slotCol = komporSlotCol;
        slotRow = komporSlotRow;

        frameY = gamePanel.tileSize * 1/2;
        frameWidth = gamePanel.tileSize * 6;
        frameHeight = gamePanel.tileSize * 5;

        
        final int slotXStart = frameX + 31;
        final int slotYStart = frameY + 10;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gamePanel.tileSize / 3 * 3;
        
        // // // Isi Inventory
        List<SuperObject> menu = kompor.getMenu();
        drawItemsInMenu(kompor, slotXStart, slotX, slotY, slotSize, menu);
        drawSubWindowMenu(frameX-100, frameY, frameWidth, frameHeight);

        if (cursor) {

            drawSelectionBoxMenu(slotXStart, slotYStart, slotSize, slotCol, slotRow);
            // DESCRIPTION FRAME
            int descriptionFrameX = frameX;
            int descriptionFrameY = frameY + frameHeight;
            int descriptionFrameWidth = frameWidth;
            int descriptionFrameHeight = gamePanel.tileSize / 3 * 10;

            drawMenuDescriptionText(menu, descriptionFrameX-159, descriptionFrameY-150, descriptionFrameWidth, descriptionFrameHeight, slotCol, slotRow);
        }
    }

    private void drawSelectionBoxMenu(int slotXStart, int slotYStart, int slotSize, int slotCol, int slotRow) {
        // CURSOR selection box
        int cursorX = slotXStart + (slotSize * slotCol);
        int cursorY = slotYStart + (slotSize * slotRow);
        int cursorWidth = gamePanel.tileSize;
        int cursorHeight = gamePanel.tileSize;

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX - 135, cursorY + 172, cursorWidth, cursorHeight, 10, 10);
    }

    private void drawItemsInMenu(SuperObject kompor, int slotXStart, int slotX, int slotY, int slotSize, List<SuperObject> menu) {
        for (int i = 0; i < menu.size(); i++) {
            SuperObject object = menu.get(i);
            g2.drawImage(object.getImage1(), slotX - 130, slotY + 180, null);

            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }
    } //

    private void drawMenuDescriptionText(List<SuperObject> menu, int descriptionFrameX, int descriptionFrameY, int descriptionFrameWidth, int descriptionFrameHeight, int slotCol, int slotRow) {
        // DRAW DESCRIPTION TEXT
        int textX = descriptionFrameX + 50;
        int textY = descriptionFrameY + gamePanel.tileSize / 3;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));

        int itemIndex = getItemIndexFromSlot(slotCol, slotRow);

        if (itemIndex < menu.size()) {

            drawSubWindowDesc(descriptionFrameX +  31, descriptionFrameY - 30, descriptionFrameWidth, descriptionFrameHeight);

            for (String line : menu.get(itemIndex).getDescription().split("\n")) {
                g2.drawString(line, textX, textY + 210);
                textY += 25;
            }
        }
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

    public int getKomporSlotCol() {
        return komporSlotCol;
    }

    public UI setKomporSlotCol(int komporSlotCol) {
        this.komporSlotCol = komporSlotCol;
        return this;
    }

    public int getKomporSlotRow() {
        return komporSlotRow;
    }

    public UI setKomporSlotRow(int komporSlotRow) {
        this.komporSlotRow = komporSlotRow;
        return this;
    }

    public int getDaganganSlotCol() {
        return daganganSlotCol;
    }

    public UI setDanganganSlotCol(int daganganSlotCol) {
        this.daganganSlotCol = daganganSlotCol;
        return this;
    }

    public int getDaganganSlotRow() {
        return daganganSlotRow;
    }

    public UI setDaganganSlotRow(int daganganSlotRow) {
        this.daganganSlotRow = daganganSlotRow;
        return this;
    }
}
