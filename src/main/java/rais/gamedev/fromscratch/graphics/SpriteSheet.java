package rais.gamedev.fromscratch.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SpriteSheet {

    private final String path;
    public int SIZE;
    public int height, width;
    public int[] pixels;
    public static SpriteSheet overworld = new SpriteSheet("/textures/gfx/Overworld.png");
    public static SpriteSheet character = new SpriteSheet("/textures/gfx/character.png");
    public static SpriteSheet gameObjects = new SpriteSheet("/textures/gfx/objects.png");

    public SpriteSheet(String path) {
        this.path = path;
        load();
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(SpriteSheet.class.getResource(path)));
            width = image.getWidth();
            height = image.getHeight();
            SIZE = width * height;
            pixels = new int[width * height];
            image.getRGB(0,0,width,height,pixels,0,width);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
