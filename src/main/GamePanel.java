package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 pixels
    final int scale = 1; // 1x scale
    public final int tileSize = originalTileSize * scale; //48 x 48 tile

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;

    public final int screenWidth = maxScreenCol * 3 * 16; //tileSize; // 3.072 x 3.072
    public final int screenHeight = maxScreenRow * 3 * 16;//tileSize;

    final int fps = 60; // 60 frames per second

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
    
    //entity and object
    public Player player = new Player(this, keyHandler, "joko");
    public SuperObject obj[][] = new SuperObject[maxMap][10];

    //state
    public int gameState;
    public final int playState = 1;
    public final int interactObjState = 2;

    //state non-aktif
    public boolean isActiveAction = false;
    public boolean isPassiveAction = false;
    

   
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
            }
        }
    }

    public void update(){
        //update game logic
            player.update();
        
        
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
        player.draw(g2d);
        ui.draw(g2d);
        g2d.dispose();
        //draw game graphics
    }
}
