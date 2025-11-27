package rais.gamedev.fromscratch.graphics.sprites;

import rais.gamedev.fromscratch.graphics.SpriteSheet;

public class PlayerSprite extends Sprite {

    public static PlayerSprite playerForward = new PlayerSprite(16, 32, 0,2,SpriteSheet.character);
    public static PlayerSprite playerForward_1 = new PlayerSprite(16, 32, 1,2,SpriteSheet.character);
    public static PlayerSprite playerForward_2 = new PlayerSprite(16, 32, 3,2,SpriteSheet.character);
    public static PlayerSprite playerBackward = new PlayerSprite(16, 32, 0,0,SpriteSheet.character);
    public static PlayerSprite playerBackward_1 = new PlayerSprite(16, 32, 1,0,SpriteSheet.character);
    public static PlayerSprite playerBackward_2 = new PlayerSprite(16, 32, 3,0,SpriteSheet.character);
    public static PlayerSprite playerSide = new PlayerSprite(16, 32, 0,1,SpriteSheet.character);
    public static PlayerSprite playerSide_1 = new PlayerSprite(16, 32, 1,1,SpriteSheet.character);
    public static PlayerSprite playerSide_2 = new PlayerSprite(16, 32, 3,1,SpriteSheet.character);

    public PlayerSprite(int width, int height, int color) {
        super(width, height, color);
    }

    public PlayerSprite(int width, int height, int x, int y, SpriteSheet spriteSheet) {
        super(width, height, x, y, spriteSheet);
    }


}
