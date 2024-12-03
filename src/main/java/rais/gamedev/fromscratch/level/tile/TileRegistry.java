package rais.gamedev.fromscratch.level.tile;

import rais.gamedev.fromscratch.graphics.Sprite;
import rais.gamedev.fromscratch.graphics.SpriteSheet;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TileRegistry {
    public static Map<Integer, Tile> tiles = new HashMap<>();

    // Initialize tiles
    static {
        tiles.put(0, new Tile(new Sprite(16, 16, Color.BLACK.getRGB()))); // Void
        tiles.put(1, new Tile(new Sprite(16,16, 0,0, SpriteSheet.overworld))); // Grass
        tiles.put(284, new WaterTile( new Sprite(16,16, 3, 7, SpriteSheet.overworld))); // Water
        tiles.put(162, new Tile(new Sprite(16,16,1,4,SpriteSheet.overworld))); // road
        tiles.put(122, new Tile(new Sprite(16, 16,1,3,SpriteSheet.overworld))); // roadSide_1
        tiles.put(163, new Tile(new Sprite(16, 16,2,4,SpriteSheet.overworld))); // roadSide_2
        tiles.put(202, new Tile(new Sprite(16, 16,1,5,SpriteSheet.overworld))); // roadSide_3
        tiles.put(161, new Tile(new Sprite(16, 16,0,4,SpriteSheet.overworld))); // roadSide_4
        tiles.put(121, new Tile(new Sprite(16, 16,0,3,SpriteSheet.overworld))); // roadCorner_1
        tiles.put(123, new Tile(new Sprite(16, 16,2,3,SpriteSheet.overworld))); // roadCorner_2
        tiles.put(201, new Tile(new Sprite(16, 16,0,5,SpriteSheet.overworld))); // roadCorner_3
        tiles.put(203, new Tile(new Sprite(16, 16,2,5,SpriteSheet.overworld))); // roadCorner_4
        tiles.put(241, new Tile(new Sprite(16, 16,0,6,SpriteSheet.overworld))); // roadCorner_5
        tiles.put(242, new Tile(new Sprite(16, 16,1,6,SpriteSheet.overworld))); // roadCorner_6
        tiles.put(282, new Tile(new Sprite(16, 16,1,7,SpriteSheet.overworld))); // roadCorner_7
        tiles.put(281, new Tile(new Sprite(16, 16,0,7,SpriteSheet.overworld))); // roadCorner_8
        tiles.put(290, new Tile(new Sprite(16, 16,9,7,SpriteSheet.overworld))); // bridge_h
        tiles.put(287, new Tile(new Sprite(16, 16,6,7,SpriteSheet.overworld))); // bridge_v
        tiles.put(244, new Tile(new Sprite(16, 16,3,6,SpriteSheet.overworld))); // grassWaterSide_1
        tiles.put(283, new Tile(new Sprite(16, 16,2,7,SpriteSheet.overworld))); // grassWaterSide_2
        tiles.put(324, new Tile(new Sprite(16, 16,3,8,SpriteSheet.overworld))); // grassWaterSide_3
        tiles.put(285, new Tile(new Sprite(16, 16,4,7,SpriteSheet.overworld))); // grassWaterSide_4
        tiles.put(363, new Tile(new Sprite(16, 16,2,9,SpriteSheet.overworld))); // grassWaterSide_5
        tiles.put(364, new Tile(new Sprite(16, 16,3,9,SpriteSheet.overworld))); // grassWaterSide_6
        tiles.put(404, new Tile(new Sprite(16, 16,3,10,SpriteSheet.overworld))); // grassWaterSide_7
        tiles.put(403, new Tile(new Sprite(16, 16,2,10,SpriteSheet.overworld))); // grassWaterSide_8
        tiles.put(646, new Tile(new Sprite(16, 16,5,16,SpriteSheet.overworld))); // grassWaterSide_8


    }
}