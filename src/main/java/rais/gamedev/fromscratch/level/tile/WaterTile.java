package rais.gamedev.fromscratch.level.tile;

import rais.gamedev.fromscratch.graphics.Screen;
import rais.gamedev.fromscratch.graphics.Sprite;

public class WaterTile extends Tile {

    public WaterTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public boolean solid() {
        return true;
    }
}
