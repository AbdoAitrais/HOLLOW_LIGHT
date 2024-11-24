package rais.gamedev.fromscratch.graphics;


import java.util.Arrays;
import java.util.Random;

/**
 * Screen:
 *      Could also be called Render, and this class is going to help us generate/render
 *      our views/images/frames
 *
 */
public class Screen {

    private final int  MAP_SIZE = 64;
    private final int MAP_SIZE_MASK = MAP_SIZE -1;

    private int width, height;
    public int[] pixels;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }

    public void render(int xOffset, int yOffset) {
        for (int y = 0; y < height; y++) {
            int yy = y + yOffset;
//            if (y < 0 || y >= height) break;
            for (int x = 0; x < width; x++) {
                int xx = x + xOffset;
//                if (x < 0 || x >= width) break;
                int tileIndex = ((xx >> 4) & MAP_SIZE_MASK) + ((yy >> 4) & MAP_SIZE_MASK)* MAP_SIZE;
                pixels[x + y * width] = tiles[tileIndex];
            }
        }
    }

}
