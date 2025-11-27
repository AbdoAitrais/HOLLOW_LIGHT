package rais.gamedev.fromscratch.graphics.sprites;

import rais.gamedev.fromscratch.graphics.SpriteSheet;
import rais.gamedev.fromscratch.level.Level;

import java.util.Arrays;

public class Sprite {

    public final int Id;
    public final int WIDTH;
    public final int HEIGHT;
    public int SIZE;
    protected int x, y; // sprite coordinates in the sprite sheet
    public int[] pixels; // pixel representation of the sprite
    protected SpriteSheet spriteSheet;

    public Sprite(int width, int height, int color) {
        Id = 0;
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
        this.Id = (x + y * (spriteSheet.width >> Level.TILE_SIZE_SHIFTING)) + 1;
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
