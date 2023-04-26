package main;

import javax.swing.*;;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello, World!");
        JFrame window = new JFrame("Tubes Sims");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
