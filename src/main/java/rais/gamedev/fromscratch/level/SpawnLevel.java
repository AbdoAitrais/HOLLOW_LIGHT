package rais.gamedev.fromscratch.level;

import rais.gamedev.fromscratch._utility.MapLoader;
import rais.gamedev.fromscratch.level.tile.Tile;
import rais.gamedev.fromscratch.level.tile.TileRegistry;

public class SpawnLevel extends Level{

    public SpawnLevel(String path) {
        super(path);
    }

    @Override
    protected void loadLevelFromFile(String path) {
        MapLoader mapLoader = new MapLoader(path);
        this.width = mapLoader.width;
        this.height = mapLoader.height;
        this.baseLayerTiles = new int[width * height];
        System.arraycopy(mapLoader.baseLayerTiles, 0, this.baseLayerTiles, 0, mapLoader.baseLayerTiles.length);
        if (mapLoader.secondLayerTiles != null) {
            this.secondLayerTiles = new int[width*height];
            System.arraycopy(mapLoader.secondLayerTiles, 0, this.secondLayerTiles, 0, mapLoader.secondLayerTiles.length);
        }
    }

    @Override
    public Tile getTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y > height || x + y * width >= baseLayerTiles.length) return TileRegistry.tiles.get(0);
        if (TileRegistry.tiles.get(baseLayerTiles[x + y * width]) != null)
            return TileRegistry.tiles.get(baseLayerTiles[x + y * width]);
        return TileRegistry.tiles.get(0);
    }

    @Override
    public Tile getSecondLayerTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y > height || x + y * width >= baseLayerTiles.length) return null;
        if (secondLayerTiles[x + y * width] != 0 && TileRegistry.tiles.get(secondLayerTiles[x + y * width]) != null)
            return TileRegistry.tiles.get(secondLayerTiles[x + y * width]);
        return null;
    }


}
