package rais.gamedev.fromscratch.graphics;

import java.awt.*;
import java.util.Arrays;

public class Sprite {

    public final int SIZE;
    private int x, y; // sprite coordinates in the sprite sheet
    public int[] pixels; // pixel representation of the sprite
    private SpriteSheet spriteSheet;

    public static Sprite grass = new Sprite(16, 0,0, SpriteSheet.sprites);
    public static Sprite water = new Sprite(16, 0, 1, SpriteSheet.sprites);
    public static Sprite voidSprite = new Sprite(16, Color.BLACK.getRGB());

    // TODO:: Sprite should use width and height to calculate SIZE instead of using jst SIZE to solve the problem of unequal sprite sheet dimensions
    public Sprite(int size, int x, int y, SpriteSheet spriteSheet) {
        SIZE = size;
        this.pixels = new int[SIZE * SIZE];
        this.x = x * size; // x coordinate times the size of the sprite inside the sprite sheet to get the x inside the sprite sheet
        this.y = y * size;
        this.spriteSheet = spriteSheet;
        load();
    }

    public Sprite(int size, int color) {
        this.SIZE = size;
        this.pixels = new int[SIZE * SIZE];
        Arrays.fill(pixels, color);
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            System.arraycopy(spriteSheet.pixels, (x + this.x) + (y + this.y) * spriteSheet.height, pixels, 0 + y * SIZE, SIZE);

        }
    }

}
