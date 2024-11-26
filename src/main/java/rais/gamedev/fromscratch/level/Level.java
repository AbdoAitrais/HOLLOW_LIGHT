package rais.gamedev.fromscratch.level;

import rais.gamedev.fromscratch.graphics.Screen;
import rais.gamedev.fromscratch.level.tile.Tile;
import rais.gamedev.fromscratch.level.tile.TileType;

public class Level {

    protected int width, height;
    protected int[] tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new int[width *  height];
        generateLevel();
    }

    public Level(String path) {
        loadLevelFromFile(path);
    }

    protected void loadLevelFromFile(String path) {
    }

    protected void generateLevel() {
    }

    // updates the level based on entity changes ( creatures .. )
    public void update() {

    }

    protected void time() {

    }

    // renders the level
    public void render(int xScroll, int yScroll, Screen screen) {
        // setting the movement offset
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> Tile.TILE_SIZE_SHIFTING; // divide xScroll by 2^4 = 16
        // (x1 + 1) tile to be rendered to ensure smooth scrolling
        int x1 = ((xScroll + screen.width) >> Tile.TILE_SIZE_SHIFTING) + 1;
        int y0 = yScroll >> Tile.TILE_SIZE_SHIFTING;
        int y1 = ((yScroll + screen.height) >> Tile.TILE_SIZE_SHIFTING) + 1;
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || x > width || y < 0 || y > height) return Tile.voidTile;
        if (tiles[x + y * width] == TileType.Grass.ordinal()) return Tile.grass;
        if (tiles[x + y * width] == TileType.Water.ordinal()) return Tile.water;
        return Tile.voidTile;
    }

}
