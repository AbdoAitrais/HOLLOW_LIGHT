package rais.gamedev.fromscratch.level.tile;

import rais.gamedev.fromscratch.graphics.Screen;
import rais.gamedev.fromscratch.graphics.Sprite;
import rais.gamedev.fromscratch.level.Level;

public class Tile {

    public int x,y; // tile coordinates in the map
    public Sprite sprite; // the representing sprite of this tile

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << Level.TILE_SIZE_SHIFTING, y << Level.TILE_SIZE_SHIFTING, this.sprite);
    }

    public boolean solid() {
        return false;
    }

}
