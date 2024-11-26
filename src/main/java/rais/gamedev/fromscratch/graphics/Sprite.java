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
    public static Sprite playerForward = new Sprite(16, 32, 0,2,SpriteSheet.character);
    public static Sprite playerForward_1 = new Sprite(16, 32, 1,2,SpriteSheet.character);
    public static Sprite playerForward_2 = new Sprite(16, 32, 3,2,SpriteSheet.character);
    public static Sprite playerBackward = new Sprite(16, 32, 0,0,SpriteSheet.character);
    public static Sprite playerBackward_1 = new Sprite(16, 32, 1,0,SpriteSheet.character);
    public static Sprite playerBackward_2 = new Sprite(16, 32, 3,0,SpriteSheet.character);
    public static Sprite playerSide = new Sprite(16, 32, 0,1,SpriteSheet.character);
    public static Sprite playerSide_1 = new Sprite(16, 32, 1,1,SpriteSheet.character);
    public static Sprite playerSide_2 = new Sprite(16, 32, 3,1,SpriteSheet.character);


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
            for (int x = 0; x < WIDTH; x++) {
                pixels[x + y * WIDTH] = spriteSheet.pixels[(this.x + x) + (this.y + y)* spriteSheet.width];
            }
        }
    }

}
