package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.List;

import main.GamePanel;
import object.Asset;
import object.BahanMakanan;
import object.OBJ_Ayam;

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
    private List<Asset> inventory = new ArrayList<>();
    private List<Asset> dagangan = new ArrayList<>();
    private BahanMakanan currentBahanMakanan;
    private int Kekenyangan;


    public List<Asset> getInventory() {
        return inventory;
    }

    public void setInventory(List<Asset> inventory) {
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

    public List<Asset> getDagangan() {
        return dagangan;
    }

    public void useInventory(String name){
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(name)) {
                inventory.remove(i);
            }
        }
    }



    
}
