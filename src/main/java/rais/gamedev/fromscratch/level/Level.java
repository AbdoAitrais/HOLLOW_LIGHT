package rais.gamedev.fromscratch.level;

import rais.gamedev.fromscratch.graphics.Screen;
import rais.gamedev.fromscratch.level.tile.Tile;

public class Level {

    public static final int TILE_SIZE = 16;
    public static final int TILE_SIZE_SHIFTING = (int) Math.sqrt(TILE_SIZE);
    protected int width, height;
    protected int[] baseLayerTiles;
    protected int[] objectLayerTiles; // TODO:: Add object layer tiles ( requires some Tiled work :( )

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        this.baseLayerTiles = new int[width *  height];
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
        int x0 = xScroll >> Level.TILE_SIZE_SHIFTING; // divide xScroll by 2^4 = 16
        // (x1 + 1) tile to be rendered to ensure smooth scrolling
        int x1 = ((xScroll + screen.width) >> Level.TILE_SIZE_SHIFTING) + 1;
        int y0 = yScroll >> Level.TILE_SIZE_SHIFTING;
        int y1 = ((yScroll + screen.height) >> Level.TILE_SIZE_SHIFTING) + 1;
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }

    public Tile getTile(int x, int y) {
        return null;
    }

}
