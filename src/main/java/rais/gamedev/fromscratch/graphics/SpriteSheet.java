package rais.gamedev.fromscratch.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SpriteSheet {

    private String path;
    public final int SIZE;
    public int height, width;
    public int[] pixels;

    public static SpriteSheet overworld = new SpriteSheet("/textures/gfx/Overworld256.png", 256, 256, 256);
    public static SpriteSheet character = new SpriteSheet("/textures/gfx/character.png", 256, 256, 256);

    public SpriteSheet(String path, int size, int width, int height) {
        this.path = path;
        SIZE = size;
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        load();
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(SpriteSheet.class.getResource(path)));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0,0,w,h,pixels,0,w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
