package rais.gamedev.fromscratch.graphics.sprites;

import rais.gamedev.fromscratch.graphics.SpriteSheet;

public class SpellSprite extends Sprite {

    public static SpellSprite fire = new SpellSprite(16,16,11,3,SpriteSheet.gameObjects);

    public SpellSprite(int width, int height, int color) {
        super(width, height, color);
    }

    public SpellSprite(int width, int height, int x, int y, SpriteSheet spriteSheet) {
        super(width, height, x, y, spriteSheet);
    }
}
