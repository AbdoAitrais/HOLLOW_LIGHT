package rais.gamedev.fromscratch.graphics;

import java.awt.*;
import java.util.Arrays;

public class Sprite {

    public final int WIDTH;
    public final int HEIGHT;
    public int SIZE;
    protected int x, y; // sprite coordinates in the sprite sheet
    public int[] pixels; // pixel representation of the sprite
    protected SpriteSheet spriteSheet;

    public static Sprite grass = new Sprite(16,16, 0,0, SpriteSheet.overworld);
    public static Sprite water = new Sprite(16,16, 0, 1, SpriteSheet.overworld);
    public static Sprite voidSprite = new Sprite(16, 16, Color.BLACK.getRGB());
    public static Sprite character = new Sprite(16, 32, 0,0,SpriteSheet.character);

    public Sprite(int width, int height, int color) {
        WIDTH = width;
        HEIGHT = height;
        SIZE = WIDTH * HEIGHT;
        this.pixels = new int[SIZE * SIZE];
        Arrays.fill(pixels, color);
    }

    public Sprite(int width, int height, int x, int y, SpriteSheet spriteSheet) {
        WIDTH = width;
        HEIGHT = height;
        SIZE = WIDTH * HEIGHT;
        this.pixels = new int[SIZE];
        this.x = x * WIDTH; // x coordinate times the size of the sprite inside the sprite sheet to get the x inside the sprite sheet
        this.y = y * HEIGHT;
        this.spriteSheet = spriteSheet;
        load();
    }

    public void load() {
        for (int y = 0; y < HEIGHT; y++) {
            if (WIDTH >= 0)
                System.arraycopy(spriteSheet.pixels, (x + this.x) + (y + this.y) * spriteSheet.height, pixels, y * WIDTH, WIDTH);


        }
    }

}
