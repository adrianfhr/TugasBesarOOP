package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

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

    //SYSTEM SETTINGS
    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler(this);
    Thread gameThread;
    public UI ui = new UI(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public int clock;
    private final Config config = new Config(this);
    
    //entity and object
    public Player player = new Player(this, keyHandler, "joko");
    public SuperObject obj[][] = new SuperObject[maxMap][10];

    //state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int interactObjState = 2;
    public final int pauseState = 3;
    public final int dialogueState = 4;
    public final int characterState = 5;
    public final int optionState = 6;
    public final int transitionState = 7;
    public final int gameOverState = 8;


    //state non-aktif
    public boolean isActiveAction = false;
    public boolean isPassiveAction = false;
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
                if(player.getState().equals("tidur")){
                    player.jamTidur--;
                }
                
            }
        }
    }

    public void update(){
        //update game logic
        if (gameState == playState){
            player.update();
        } else if (gameState == interactObjState){
            player.update();
        }
        
        
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //gambar tile
        tileManager.draw(g2d);

        //objek (rumah, pohon, dll)
        for (int i = 0; i < obj[this.currentMap].length; i++) {
             if(obj[this.currentMap][i] != null){
                 obj[this.currentMap][i].draw(g2d, this);
             }
         }

        //player
        if(! isActiveAction){
            player.draw(g2d);
        }

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
}
