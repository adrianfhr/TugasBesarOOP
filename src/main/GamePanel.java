package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entity.Player;
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
    public final int maxWorldCol = 66;
    public final int maxWorldRow = 66;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;
    public final int maxMap = 10;
    public int currentMap = 0;
    public int currentPlayer = 0;

    //entity and object
    public Player player[] = new Player[10];
    public SuperObject obj[][] = new SuperObject[maxMap][10];

    //SYSTEM SETTINGS
    KeyHandler keyHandler = new KeyHandler(this);
    Thread gameThread;
    TileManager tileManager = new TileManager(this);
    public UI ui = new UI(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public int clock;
    private final Config config = new Config(this);
    
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


    //state non-aktif
    public boolean isActiveAction = false;
    public boolean isPassiveAction = false;
    public boolean isInputAction = false;
    private boolean fullScreenOn;
    
    

   
    //GAME SETTINGS
    public GamePanel (){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame(){
         player[0] = new Player(this, keyHandler, "Player 1", 1);
         assetSetter.setObject();
         gameState = titleState;
         tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
         gps2d = (Graphics2D) tempScreen.getGraphics();
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
            if(isActiveAction){
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

            if(deltaClock >= 1 && isActiveAction){
                clock++;
                deltaClock--;
                player[currentPlayer].jamTidakTidur--;
                if(player[currentPlayer].getState().equals("Tidur")){
                    player[currentPlayer].jamTidur--;
                }else if(gameState == masakState ){
                    player[currentPlayer].jamMemasak--;
                }
                
            }
        }
    }

    public void update(){
        //update game logic
        if (gameState == playState){
            player[currentPlayer].update();
        } else if (gameState == interactObjState){
            player[currentPlayer].update();
        } //else if(gameState == masakState) player[currentPlayer].update();

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
        int index = 0;
        while (player[index] != null) {
            index++;
            if (index == player.length) {
                System.out.println("Array penuh, tidak bisa menambahkan pemain baru!");
                return;
            }
        }
        player[index] = new Player(this, keyHandler, name, index + 1);
        assetSetter.makeOBJ("rumah");
        tileManager.loadMap("res/map/homeMap.txt", player[index].getId());
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
    public void playerTime(){
        if(player[currentPlayer].jamTidur == 0 ){
            player[currentPlayer].interactOBJ();
            player[currentPlayer].jamTidur = 4 * 60 * 2;
        }

        if(player[currentPlayer].jamTidakTidur == 0){
            player[currentPlayer].setHealth(player[currentPlayer].getHealth() - 5);
            player[currentPlayer].setMood(player[currentPlayer].getMood() - 5);
            player[currentPlayer].jamTidakTidur = 10 * 60 * 2;
        }

        if(player[currentPlayer].jamKerja == 0){
            player[currentPlayer].jamKerja = 30 * 2;
        }

        if(player[currentPlayer].jamMules == 0){
            player[currentPlayer].interactOBJ();
            player[currentPlayer].jamMules = 10 * 2;
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
            player[currentPlayer].jamOlahraga = 20 * 2;
        }

        //jMASIH BINGUNG ISINYA
        if(player[currentPlayer].jamMakan == 0){
            //BELUM TAU ISINYA GMNA
        }

        if(player[currentPlayer].jamMemasak == 0){
        //     //belum tau isinya
        player[currentPlayer].interactOBJ();
        //player[currentPlayer].setMood(player[currentPlayer].getMood() + 10);
        isActiveAction = false;
        player[currentPlayer].jamMemasak = 30;
        gameState = playState;
        }

        if(player[currentPlayer].jamBerkunjung == 0){
            player[currentPlayer].setMood(player[currentPlayer].getMood() + 10);
            player[currentPlayer].setHunger(player[currentPlayer].getHunger() - 10);
        }

        if(player[currentPlayer].jamBerkunjung == 0){
            //MASIH BINGUNG
        }

    }
}
