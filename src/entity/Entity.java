package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.List;

import object.SuperObject;
import object.BahanMakanan;

public class Entity {
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public boolean isInteracting = false;
    public int targetIndex; //variable bantu untuk cek index interaksi
    private final int maxInventorySize = 20;
    private List<SuperObject> inventory = new ArrayList<>();
    private List<SuperObject> dagangan = new ArrayList<>();
    private BahanMakanan currentBahanMakanan;
    private int Kekenyangan;
    private String[] dialogues = new String[20];
    private int dialogueIndex;

    public List<SuperObject> getInventory() {
        return inventory;
    }

    public void setInventory(List<SuperObject> inventory) {
        this.inventory = inventory;
    }

    public int getMaxInventorySize() {
        return maxInventorySize;
    }

    public BahanMakanan getCurrentBahanMakanan() {
        return currentBahanMakanan;
    }

    public Entity setCurrentBahanMakanan(BahanMakanan currentBahanMakanan) {
        this.currentBahanMakanan = currentBahanMakanan;
        return this;
    }

    public int getKekenyangan() {
        return Kekenyangan;
    }

    public Entity setKekenyangan(int Kekenyangan) {
        this.Kekenyangan = Kekenyangan;
        return this;
    }

    public List<SuperObject> getDagangan() {
        return dagangan;
    }

    public String[] getDialogues() {
        return dialogues;
    }

    public Entity setDialogues(String[] dialogues) {
        this.dialogues = dialogues;
        return this;
    }

    public int getDialogueIndex() {
        return dialogueIndex;
    }

    public void setDialogueIndex(int dialogueIndex) {
        this.dialogueIndex = dialogueIndex;
    }


    public void useInventory(String name){
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(name)) {
                inventory.remove(i);
            }
        }
    }



    
}
