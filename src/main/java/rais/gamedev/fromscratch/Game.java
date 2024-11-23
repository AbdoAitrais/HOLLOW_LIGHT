package rais.gamedev.fromscratch;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable {

    public static int width = 300;
    public static int height = width/ 16 * 9;
    public static int scale = 3;
    public Thread gameThread;
    public boolean running = false;
    public JFrame frame;

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        frame = new JFrame();
    }

    public synchronized void start() {
        this.running = true;
        this.gameThread = new Thread(this, "GameThread");
        this.gameThread.start();
    }

    public synchronized void stop() {
        this.running = false;
        try {
            this.gameThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (this.running){
            System.out.println("Running ...");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false); // important to avoid graphical issues
        game.frame.setTitle("Game");
        game.frame.add(game);
        game.frame.pack(); // set size to the size of game ( canvas )
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null); // centers the window
        game.frame.setVisible(true);

        game.start();
    }
}