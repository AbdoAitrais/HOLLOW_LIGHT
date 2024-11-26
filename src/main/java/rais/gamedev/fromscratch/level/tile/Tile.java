package rais.gamedev.fromscratch.level.tile;

import rais.gamedev.fromscratch.graphics.Screen;
import rais.gamedev.fromscratch.graphics.Sprite;

public class Tile {

    public int x,y; // tile coordinates in the map
    public Sprite sprite; // the representing sprite of this tile

    public static int TILE_SIZE = 16;
    public static int TILE_SIZE_SHIFTING = (int) Math.sqrt(TILE_SIZE);

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile water =  new WaterTile(Sprite.water);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << TILE_SIZE_SHIFTING, y << TILE_SIZE_SHIFTING, this);
    }

    public boolean solid() {
        return false;
    }

}
