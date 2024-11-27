package rais.gamedev.fromscratch.graphics;


import rais.gamedev.fromscratch.level.tile.Tile;

import java.util.Arrays;

/**
 * Screen:
 *      Could also be called Render, and this class is going to help us generate/render
 *      our views/images/frames
 *
 */
public class Screen {

    public int width, height;
    public int xOffset, yOffset; // movement coordinates
    public int[] pixels;

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
        for (int y = 0; y < tile.sprite.HEIGHT ; y++) {
            int yAbsolute = yPosition + y;
            for (int x = 0; x < tile.sprite.WIDTH; x++) {
                int xAbsolute = xPosition + x;
                // we render one more tile that will be rendered before reaching it
                // to ensure smooth scrolling through the map
                if (xAbsolute < -tile.sprite.WIDTH || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;
                if (xAbsolute < 0) xAbsolute = 0;
//                System.out.println("xAbs: " + xAbsolute + " , yAbs: " + yAbsolute + " , " +pixels.length + " , " + xAbsolute + yAbsolute * width);
                pixels[xAbsolute + yAbsolute * width] = tile.sprite.pixels[x + y * tile.sprite.WIDTH];
            }
        }
    }

    public void renderPlayer(int xPosition, int yPosition, Sprite sprite, boolean xFlip, boolean yFlip) {
        xPosition -= xOffset;
        yPosition -= yOffset;
        for (int y = 0; y < sprite.HEIGHT ; y++) {
            int yAbsolute = yPosition + y;
            int yFlipped = y;
            if (yFlip) yFlipped = sprite.HEIGHT - 1 - y;
            for (int x = 0; x < sprite.WIDTH; x++) {
                int xAbsolute = xPosition + x;
                int xFlipped = x;
                if (xAbsolute < -sprite.WIDTH || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;
                if (xAbsolute < 0) xAbsolute = 0;
                if (xFlip) xFlipped = sprite.WIDTH - 1 - x;
                // renders only the playerForward and leaves the background
                pixels[xAbsolute + yAbsolute * width] = sprite.pixels[xFlipped + yFlipped * sprite.WIDTH] == 0xffffff ? pixels[xAbsolute + yAbsolute * width] : sprite.pixels[xFlipped + yFlipped * sprite.WIDTH];
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
