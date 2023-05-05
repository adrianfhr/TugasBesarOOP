package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entity.Entity;
import entity.NPC_Cat;
import entity.NPC_Wife;
import entity.Player;
import environment.EnvironmentManager;
import object.OBJ_Rumah;
import object.SuperObject;
import sound.SoundManager;
import tile.TileManager;
import main.Config;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 pixels
    final int scale = 3; // 1x scale
    public final int tileSize = originalTileSize * 3; //48 x 48 tile

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;

    public final int screenWidth = maxScreenCol * 3 * 16; //tileSize; // 3.072 x 3.072
    public final int screenHeight = maxScreenRow * 3 * 16;//tileSize;

    // Full screen mode
    private int fullScreenWidth = screenWidth;
    private int fullScreenHeight = screenHeight;
    private BufferedImage tempScreen;
    private Graphics2D gps2d;

    final int fps = 60; // 60 frames per second

    //SOUND
    SoundManager music = new SoundManager();
    SoundManager soundEffect = new SoundManager();

    //WORLD SETTINGS
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;
    public final int maxMap = 10;
    public int currentMap = 0;
    public int currentPlayer = 0;

    //SYSTEM SETTINGS
    TileManager tileManager = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    Thread gameThread;
    public UI ui = new UI(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public int clock = 360;
    private final Config config = new Config(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    
    //entity and object
    public Player player[] = new Player[10];
    public SuperObject obj[][] = new SuperObject[maxMap][100];
    public NPC_Wife npc[] = new NPC_Wife[10];
    public NPC_Cat cat[] = new NPC_Cat[10];
    public SuperObject tempBuyObj[] = new SuperObject[10]; 
    public int tempBuyObjCount[] = new int[10];

    //state
    public int gameState = 0;
    public final int titleState = 0;
    public final int playState = 1;
    public final int interactObjState = 2;
    public final int pauseState = 3;
    public final int dialogueState = 4;
    public final int characterState = 5;
    public final int optionState = 6;
    public final int transitionState = 7;
    public final int gameOverState = 8;
    public final int masakState = 9;
    public final int gameEventState = 10;
    public final int tradeState = 11;
    public final int useBarangState = 12;
    public final int wifeState = 13;
    public final int makanState = 14;
    public final int catState = 15;


    //state non-aktif
    public boolean isActiveAction = false;
    public boolean isPassiveAction = false;
    public boolean isInputAction = false;
    public boolean isKanan = false;
    public boolean isKiri = false;
    public boolean isAtas = false;
    private boolean fullScreenOn;
    

    public boolean isNPC = false;
    public boolean isCat = false;
    
    

   
    //GAME SETTINGS
    public GamePanel (){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame(){
        player[0] = new Player(this, keyHandler, "player1", 1);
         assetSetter.setObject();
         assetSetter.setNPC();
         gameState = titleState;
         tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
         eManager.setup();
         eManager.lighting.dayCounter = clock;
         gps2d = (Graphics2D) tempScreen.getGraphics();
         for(int i = 0; i < tempBuyObjCount.length; i++){
             tempBuyObjCount[i] = 999;
         }
        if (fullScreenOn) {
            setFullScreen();
        }

     }

     public void setFullScreen() {

        // GET LOCAL SCREEN DEVICE
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();

        graphicsDevice.setFullScreenWindow(Main.window);

        // GET FULLSCREEN WIDTH & HEIGHT
        fullScreenWidth = Main.window.getWidth();
        fullScreenHeight = Main.window.getHeight();
    }

    public void drawToScreen() {
        Graphics graphics = getGraphics();
        graphics.drawImage(tempScreen, 0, 0, fullScreenWidth, fullScreenHeight, null);
        graphics.dispose();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / fps; // fps 60
        double delta = 0;
        double deltaClock = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0; 
        
        while(gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            if(isActiveAction || player[currentPlayer].isBerkunjungAction){
                deltaClock += (currentTime - lastTime) / (drawInterval*30);
            }

            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1){
                update();
                repaint();
                drawCount++;
                delta--;
            }

            if(deltaClock >= 1 && (player[currentPlayer].isBerkunjungAction || isActiveAction)){
                
                    clock++;
                    deltaClock--;
                    if(player[currentPlayer].getState().equals("Tidur")){
                        player[currentPlayer].jamTidur--;
                        if (player[currentPlayer].abisMakan){
                            player[currentPlayer].jamTidakMules--;
                        }
                    } else if(gameState == masakState ){
                        player[currentPlayer].jamMemasak--;
                        player[currentPlayer].jamTidakTidur--;
                        if (player[currentPlayer].abisMakan){
                        player[currentPlayer].jamTidakMules--;
                    }
                        
                    } else if(player[currentPlayer].getState().equals("Bekerja")){
                        player[currentPlayer].jamKerja--;
                        player[currentPlayer].jamTidakTidur--;
                        if (player[currentPlayer].abisMakan){
                        player[currentPlayer].jamTidakMules--;
                    }
                        
                    } else if(player[currentPlayer].getState().equals("buang air")){
                        player[currentPlayer].jamMules--;
                        player[currentPlayer].jamTidakTidur--;
                        if (player[currentPlayer].abisMakan){
                        player[currentPlayer].jamTidakMules--;
                    }
                        
                    }else if(player[currentPlayer].isBerkunjungAction){
                        player[currentPlayer].jamBerkunjung--;
                        player[currentPlayer].jamTidakTidur--;
                        if (player[currentPlayer].abisMakan){
                        player[currentPlayer].jamTidakMules--;
                    }
                        
                    } else if(player[currentPlayer].getState().equals("Olahraga")){
                        player[currentPlayer].jamOlahraga--;
                        player[currentPlayer].jamTidakTidur--;
                        if (player[currentPlayer].abisMakan){
                        player[currentPlayer].jamTidakMules--;
                    }

                    } else if(player[currentPlayer].getState().equals("makan")){
                        player[currentPlayer].jamMakan--;
                        player[currentPlayer].jamTidakTidur--;
                        if (player[currentPlayer].abisMakan){
                        player[currentPlayer].jamTidakMules--;
                        }
                    }
                    else if(player[currentPlayer].getState().equals("Ibadah")){
                        player[currentPlayer].jamIbadah--;
                        player[currentPlayer].jamTidakTidur--;
                        if (player[currentPlayer].abisMakan){
                            player[currentPlayer].jamTidakMules--;
                        }
                    } else if(player[currentPlayer].getState().equals("Nonton")){
                        player[currentPlayer].jamNonton--;
                        player[currentPlayer].jamTidakTidur--;
                        if (player[currentPlayer].abisMakan){
                            player[currentPlayer].jamTidakMules--;
                        }
                    }

                    for(int i = 0; i < tempBuyObj.length; i++){
                        if (tempBuyObj[i] != null && tempBuyObjCount[i] != 999 && tempBuyObjCount[i] > 0){
                            tempBuyObjCount[i]--;
                        }
                    }

                if (isPassiveAction){
                    player[currentPlayer].jamUpgrade--;
                }
            }

                
                eManager.lighting.dayCounter++;

                for(int i = 0; i < tempBuyObj.length; i++){
                    if (tempBuyObjCount[i] != 999){
                        System.out.println("temp OBJ : " + tempBuyObj[i]);
                        System.out.println("temp OBJ Count : " + tempBuyObjCount[i]);
                    }
                }
            }
        }
    

    public void update(){
        //update game logic
        if (gameState == playState){
            //PLAYER
            player[currentPlayer].update();

            //NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }

            for (int i = 0; i < cat.length; i++) {
                if (cat[i] != null) {
                    cat[i].update();
                }
            }
        } 

        else if (gameState == interactObjState){
            player[currentPlayer].update();
        }
        if (gameState == useBarangState){
            assetSetter.update();
        } //else if(gameState == masakState) player[currentPlayer].update();
        if(isActiveAction) eManager.update();

        playerTime();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //gambar tile
        if(gameState != titleState ){
            tileManager.draw(g2d);
            //objek (rumah, pohon, dll)
            for (int i = 0; i < obj[this.currentMap].length; i++) {
                if(obj[this.currentMap][i] != null){
                    obj[this.currentMap][i].draw(g2d, this);
                }
            }
            
            if(! isActiveAction){
                player[currentPlayer].draw(g2d);
            }
        }

        //NPC
        for(int i = 0; i< npc.length; i++) {
            if(npc[i] != null) {
                npc[i].draw(g2d);
            }
        }

        //CAT
        for (int i = 0; i < cat.length; i++) {
            if (cat[i] != null) {
                cat[i].draw(g2d);
            }
        }

        // Environment
        eManager.draw(g2d);

        //player
        ui.draw(g2d);
        g2d.dispose();
        //draw game graphics
    }

    public void playMusic(int index) {
        music.setFile(index);
        music.play();
        music.loop();
    }

    public void carEngine(int index) {
        music.setFile(index);
        music.play();
        music.loop();
    }

    public int getGameState() {
        return gameState;
    }

    public GamePanel setGameState(int gameState) {
        this.gameState = gameState;
        return this;
    }

    public void playSoundEffect(int index) {
        soundEffect.setFile(index);
        soundEffect.play();
    }

    public SoundManager getSoundEffect() {
        return soundEffect;
    }

    public SoundManager getMusic() {
        return music;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public Config getConfig() {
        return config;
    }

    public boolean isFullScreenOn() {
        return fullScreenOn;
    }

    public GamePanel setFullScreenOn(boolean fullScreenOn) {
        this.fullScreenOn = fullScreenOn;
        return this;
    }

    public void stopMusic() {
        music.stop();
    }

    public void makePlayer(){
        String name = JOptionPane.showInputDialog("Masukkan nama pemain");
        isInputAction = false;
        int index = 0;
        while (player[index] != null) {
            index++;
            if (index == player.length) {
                System.out.println("Array penuh, tidak bisa menambahkan pemain baru!");
                return;
            }
        }
        player[index] = new Player(this, keyHandler, name, index + 1);
        assetSetter.makeOBJ("Rumah", 0, index);
        tileManager.loadMap("res/map/homeMap.txt", player[index].getId());
        setGameState(useBarangState);
    }

    public void changePlayer(){
        boolean sukses = false;
        while(!sukses){
            String name = JOptionPane.showInputDialog("Masukkan nama pemain : ");
            int index = 0;
            while (player[index] != null) {
                if (player[index].getName().equals(name)) {
                    currentPlayer = index;
                    player[currentPlayer].teleport(50, 50, player[currentPlayer].getId());
                    return;
                }
                index++;
                if (index == player.length) {
                    System.out.println("Pemain tidak ditemukan!");
                    return;
                }
            }
        }
    }

    public void retry() {
        player[currentPlayer].setDefaultvalues();
        player[currentPlayer].restoreLife();
        // assetSetter.setObject();
        assetSetter.setNPC();
        currentMap = 0;
        gameState = playState;
        playMusic(0);
    }

    public void playerTime(){
        if(player[currentPlayer].jamTidur == 0 ){
            player[currentPlayer].interactOBJ();
            player[currentPlayer].jamTidur = 4 * 60 * 2;
            player[currentPlayer].jamTidakTidur = 10 * 60 * 2;
            ui.addMessage("Tidur selesai");
        }

        if(player[currentPlayer].jamTidakTidur == 0){
            player[currentPlayer].setHealth(player[currentPlayer].getHealth() - 5);
            player[currentPlayer].setMood(player[currentPlayer].getMood() - 5);
            player[currentPlayer].jamTidakTidur = 10 * 60 * 2;
        }

        if(player[currentPlayer].jamKerja == 0){
            isActiveAction = false;
            if(player[currentPlayer].getJob().equals("Badut Sulap")) player[currentPlayer].setMoney(player[currentPlayer].getMoney() + 15);
            if(player[currentPlayer].getJob().equals("Koki")) player[currentPlayer].setMoney(player[currentPlayer].getMoney() + 30);
            if(player[currentPlayer].getJob().equals("Polisi")) player[currentPlayer].setMoney(player[currentPlayer].getMoney() + 35);
            if(player[currentPlayer].getJob().equals("Programmer")) player[currentPlayer].setMoney(player[currentPlayer].getMoney() + 45);
            if(player[currentPlayer].getJob().equals("Dokter")) player[currentPlayer].setMoney(player[currentPlayer].getMoney() + 50);
            
            player[currentPlayer].jamKerja = 30 * 2;
        }

        if(player[currentPlayer].jamMules == 0){
            player[currentPlayer].interactOBJ();
            player[currentPlayer].jamMules = 10 * 2;
            isActiveAction = false;
            ui.addMessage("Jangan lupa cebok");
        }

        if(player[currentPlayer].jamTidakMules == 0){ //RIBEETTTTT TANDAINN
            player[currentPlayer].setHealth(player[currentPlayer].getHealth() - 5);
            player[currentPlayer].setMood(player[currentPlayer].getMood() - 5);
            player[currentPlayer].jamTidakMules = 4 * 60 * 2;     
        }

        if(player[currentPlayer].jamOlahraga == 0){
            //+5 ksehatan, -5 kekenyangan, +10 mood
            player[currentPlayer].setHealth(player[currentPlayer].getHealth() + 5);
            player[currentPlayer].setMood(player[currentPlayer].getMood() + 10);
            player[currentPlayer]. setHunger(player[currentPlayer].getHunger() - 5);
            ui.addMessage("Olahraga selesai");
            ui.addMessage("Health + 5");
            ui.addMessage("Mood + 10");
            ui.addMessage("Hunger - 5");
            if (player[currentPlayer].getHealth() > 100){
                player[currentPlayer].setHealth(100);
            }
            if (player[currentPlayer].getMood() > 100){
                player[currentPlayer].setMood(100);
            }
            player[currentPlayer].jamOlahraga = 20 * 2;
            isActiveAction = false;
        }

        //jMASIH BINGUNG ISINYA
        if(player[currentPlayer].jamMakan == 0){
            player[currentPlayer].abisMakan = true;
            obj[currentMap][player[currentPlayer].targetIndex].isActiveActionOBJ = true;
            isActiveAction = false;
            gameState = playState;
            player[currentPlayer].jamMakan = 30 * 2;
            player[currentPlayer].selectItem();
        }

        if(player[currentPlayer].jamMemasak == 0){
            player[currentPlayer].interactOBJ();
            isActiveAction = false;
            player[currentPlayer].jamMemasak = 30;
            gameState = playState;
            ui.addMessage("Makanan sudah jadi!");
        }

        if(player[currentPlayer].jamBerkunjung == 0){
            player[currentPlayer].setMood(player[currentPlayer].getMood() + 10);
            player[currentPlayer].setHunger(player[currentPlayer].getHunger() - 10);
            if (player[currentPlayer].getMood() > 100){
                player[currentPlayer].setMood(100);
            }
        }

        if(player[currentPlayer].jamIbadah == 0){
            isActiveAction = false;
            player[currentPlayer].setMood(player[currentPlayer].getMood() + 5);
            if (player[currentPlayer].getMood() > 100){
                player[currentPlayer].setMood(100);
            }
            player[currentPlayer].jamIbadah = 10;
            ui.addMessage("Mood + 5");
            playSoundEffect(12);
        }

        if(player[currentPlayer].jamNonton == 0){
            isActiveAction = false;
            player[currentPlayer].setMood(player[currentPlayer].getMood() + 5);
            if (player[currentPlayer].getMood() > 100){
                player[currentPlayer].setMood(100);
            }
            player[currentPlayer].jamNonton = 10;
            ui.addMessage("Mood + 5");
            playSoundEffect(12);
        }

        if(player[currentPlayer].jamBarang == 0){
             Random random = new Random();
            // int beli = random.nextInt(60)+1;
            // player[currentPlayer].jamBarang = beli;
            // isPassiveAction = true;
            // player[currentPlayer].beliBarang();
        }

        if(player[currentPlayer].jamUpgrade == 0){
            if (isKanan){
                upgradeRumah("Kanan");
                ui.addMessage("Upgrade berhasil!");
                isKanan = false;
                isPassiveAction = false;
            }
            if (isKiri){
                upgradeRumah("Kiri");
                ui.addMessage("Upgrade berhasil!");
                isKiri = false;
                isPassiveAction = false;
            }
            if (isAtas){
                upgradeRumah("Atas");
                ui.addMessage("Upgrade berhasil!");
                isAtas = false;
                isPassiveAction = false;
            }
        }

        checkWaktuBeliBarang();

    }

    public void deleteTempBuyObj(int index){

    }

    public synchronized void checkWaktuBeliBarang(){
        for(int i = 0; i < tempBuyObj.length;i++){
            if(tempBuyObjCount[i] == 0){
                System.out.println("MASUKK ADD");
                addInventory(tempBuyObj[i]);
                tempBuyObj[i] = null;
                tempBuyObjCount[i] = 999 ;
            }
        }
    }

    public void addInventory(SuperObject o){
        
        List<SuperObject> inventory = player[currentPlayer].getInventory();
        inventory.add(o);
       ui.addMessage("Barang "+ o.getName() + " sudah sampai");

    }

    public void upgradeRumah(String posisi){
        boolean top = false;
        boolean side = false;

        int x = player[currentPlayer].worldX/tileSize;
        int y = player[currentPlayer].worldY/tileSize;
        int posisiX = 48, posisiY = 48;

        isInputAction = true;
        String ruangan = JOptionPane.showInputDialog("Masukkan nama ruangan");
        isInputAction = false;

        if(posisi.equals("Atas")){
            while(!top){
                y--;
                int temp = tileManager.mapTileNump[currentMap][player[currentPlayer].worldX/tileSize][y];
                top = tileManager.tile[temp].collision;
            }
            while(!side){
                x--;
                int temp = tileManager.mapTileNump[currentMap][x][player[currentPlayer].worldY/tileSize];
                side = tileManager.tile[temp].collision;
            }
    
            for(int i = x+1 ; i < x + 6 + 1; i++){
                for(int j = y - 1; j > y - 1 - 6; j--){
                    tileManager.mapTileNump[currentMap][i][j] = 21; // 21 lantai rumah
                    posisiX = i;
                    posisiY = j;
                }
            }
        }

        if(posisi.equals("Kanan")){
            while(!top){
                y--;
                int temp = tileManager.mapTileNump[currentMap][player[currentPlayer].worldX/tileSize][y];
                top = tileManager.tile[temp].collision;
            }
            while(!side){
                x++;
                int temp = tileManager.mapTileNump[currentMap][x][player[currentPlayer].worldY/tileSize];
                side = tileManager.tile[temp].collision;
            }
    
            for(int i = x+1 ; i < x + 6 + 1; i++){
                for(int j = y + 1 ; j < y + 6 + 1 ; j++){
                    tileManager.mapTileNump[currentMap][i][j] = 21; // 21 lantai rumah
                    posisiX = i;
                    posisiY = j;
                }
            }
        }

        if(posisi.equals("Kiri")){
            while(!top){
                y--;
                int temp = tileManager.mapTileNump[currentMap][player[currentPlayer].worldX/tileSize][y];
                top = tileManager.tile[temp].collision;
            }

            while(!side){
                x--;
                int temp = tileManager.mapTileNump[currentMap][x][player[currentPlayer].worldY/tileSize];
                side = tileManager.tile[temp].collision;
            }
    
            for(int i = x - 1; i > x - 6 - 1; i--){
                for(int j = y + 1 ; j < y + 6 + 1; j++){
                    tileManager.mapTileNump[currentMap][i][j] = 21; // 21 lantai rumah
                    posisiX = i;
                    posisiY = j;
                }
            }
        }
        
        for(SuperObject rumah : obj[0] ){
            if(rumah instanceof OBJ_Rumah){
                OBJ_Rumah tempRumah = (OBJ_Rumah) rumah;
                if(tempRumah.pemilik == player[currentPlayer].getId()){
                    tempRumah.upgradeRumah(ruangan, posisiX, posisiY);
                }
            }
        }

    }    

    public void pindahRuangan(){
        String ruangan = JOptionPane.showInputDialog("Masukkan nama ruangan");
        int x, y;

        for(SuperObject rumah : obj[0] ){
            if(rumah instanceof OBJ_Rumah){
                OBJ_Rumah tempRumah = (OBJ_Rumah) rumah;
                if(tempRumah.pemilik == player[currentPlayer].getId()){
                    for(int i = 0; i < tempRumah.ruangan.length; i++){
                        if(tempRumah.ruangan[i].equals(ruangan)){
                            x = tempRumah.xRuangan[i];
                            y = tempRumah.yRuangan[i];
                            player[currentPlayer].teleport(x, y, currentMap);
                            break;
                        }
                    }
                    break;
                }
            }
            
        }

        
    }

    public void berkunjung(){
        player[currentPlayer].isBerkunjungAction = true;
    }

}
