package rais.gamedev.fromscratch.level;

import java.util.Random;

public class RandomLevel extends Level {

    private static final Random random = new Random();

    public RandomLevel(int width, int height) {
        super(width, height);
    }

    @Override
    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                baseLayerTiles[x + y*width] = random.nextInt(2);
            }
        }
    }
}
