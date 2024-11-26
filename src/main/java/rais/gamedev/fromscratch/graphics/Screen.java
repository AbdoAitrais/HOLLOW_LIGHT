package rais.gamedev.fromscratch.graphics;


import rais.gamedev.fromscratch.level.tile.Tile;

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
    public int width, height;
    public int xOffset, yOffset; // movement coordinates
    public int[] pixels;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }

    // takes the xPosition and yPosition of the tile in the map and renders it
    public void renderTile(int xPosition, int yPosition, Tile tile) {
        // adjusting the positions with regard to movement
        xPosition -= xOffset;
        yPosition -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE ; y++) {
            int yAbsolute = yPosition + y;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xAbsolute = xPosition + x;
                // we render one more tile that will be rendered before reaching it
                // to ensure smooth scrolling through the map
                if (xAbsolute < -tile.sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;
                if (xAbsolute < 0) xAbsolute = 0;
//                System.out.println("xAbs: " + xAbsolute + " , yAbs: " + yAbsolute + " , " +pixels.length + " , " + xAbsolute + yAbsolute * width);
                pixels[xAbsolute + yAbsolute * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
