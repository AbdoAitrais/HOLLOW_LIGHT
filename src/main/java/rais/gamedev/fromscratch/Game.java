package rais.gamedev.fromscratch;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {

    public static int width = 300;
    public static int height = width/ 16 * 9;
    public static int scale = 3;

    private Thread gameThread;
    private boolean running = false;
    private JFrame frame;

    // image : is the view that is going to be rendered
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    // we transform our image ( view ) into a pixel array representation
    // By writing to the pixels array we are going to be able to render each frame of the game
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

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
            update();
            render();
        }
    }

    public void update(){

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            // create the Buffer Strategy if null
            createBufferStrategy(3);
            bs = getBufferStrategy();
        }

        Graphics graphics = bs.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,getWidth(),getHeight());

        // Dispose of the graphics object and show the buffer
        graphics.dispose();
        bs.show();
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