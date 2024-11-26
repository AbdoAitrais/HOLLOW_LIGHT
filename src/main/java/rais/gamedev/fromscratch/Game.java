package rais.gamedev.fromscratch;

import rais.gamedev.fromscratch.entity.mob.Player;
import rais.gamedev.fromscratch.graphics.Screen;
import rais.gamedev.fromscratch.level.Level;
import rais.gamedev.fromscratch.level.RandomLevel;
import rais.gamedev.fromscratch.input.KeyBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {

    public static int width = 300;
    public static int height = width/ 16 * 9;
    public static int scale = 3;
    public static String title = "Game";
    private Thread gameThread;
    private boolean running = false;
    private JFrame frame;
    private KeyBoard keyBoard;
    // image : is the view that is going to be rendered
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    // we transform our image ( view ) into a pixel array representation
    // By writing to the pixels array we are going to be able to render each frame of the game
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private Screen screen;
    private Level level;
    private Player player;

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        level = new RandomLevel(64, 64);
        frame = new JFrame();
        keyBoard = new KeyBoard();
        player = new Player(keyBoard);
        addKeyListener(keyBoard);
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

    // Game loop function
    @Override
    public void run() {
        // We calculate a timer to make sure updating game logic
        // only happens 60 times a second to ensure consistency
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1e9 / 60; // 1e9 nano second = 1 sec => 60 updates per second
        double delta = 0.0;
        int frames = 0;
        int updates = 0;
        requestFocus();

        while (this.running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            // Update game logic at fixed intervals of 1/60th of a second.
            while(delta >= 1) {
                update();
                updates++;
                delta--;
            }
            frames++;

            // calculate the update rate and frame rate every second
            if (System.currentTimeMillis() - timer > 1000) {
                timer+=1000;
                frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
                frames = 0;
                updates = 0;
            }

            render();
        }
    }

    // Updates game logic
    public void update(){
        keyBoard.update();
        player.update();

    }

    // Renders game frames/views
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            // create the Buffer Strategy if null
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        int xScroll = player.x - width/2;
        int yScroll = player.y - height/2;
        level.render(xScroll, yScroll, screen);
        player.render(screen);

        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

        Graphics graphics    = bs.getDrawGraphics();
        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        // Dispose of the graphics object and show the buffer
        graphics.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false); // important to avoid graphical issues
        game.frame.setTitle(Game.title);
        game.frame.add(game);
        game.frame.pack(); // set size to the size of game ( canvas )
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null); // centers the window
        game.frame.setVisible(true);

        game.start();
    }
}