package rais.gamedev.fromscratch.graphics;


import java.util.Arrays;

/**
 * Screen:
 *      Could also be called Render, and this class is gonna help us generate/render
 *      our views/images/frames
 *
 */
public class Screen {

    private int width, height;
    public int[] pixels;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[20 + 30 * width] = 0xff00ff;
            }
        }
    }

}
