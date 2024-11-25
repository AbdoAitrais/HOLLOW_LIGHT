package rais.gamedev.fromscratch.graphics;

public class Sprite {

    public final int SIZE;
    private int x, y; // sprite coordinates in the sprite sheet
    public int[] pixels; // pixel representation of the sprite
    private SpriteSheet spriteSheet;

    public static Sprite grass = new Sprite(16, 0,0, SpriteSheet.sprites);

    public Sprite(int size, int x, int y, SpriteSheet spriteSheet) {
        SIZE = size;
        this.pixels = new int[SIZE * SIZE];
        this.x = x * size; // x coordinate times the size of the sprite inside the sprite sheet to get the x inside the sprite sheet
        this.y = y * size;
        this.spriteSheet = spriteSheet;
        load();
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            System.arraycopy(spriteSheet.pixels, (x + this.x) + (y + this.y) * spriteSheet.height, pixels, 0 + y * SIZE, SIZE);

        }
    }

}
