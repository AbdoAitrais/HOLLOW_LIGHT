package rais.gamedev.fromscratch.graphics.sprites;

import rais.gamedev.fromscratch.graphics.SpriteSheet;

public class LogMonsterSprite extends  Sprite {

    public static Sprite log_monster = new Sprite(32,32,0,0,SpriteSheet.log);


    public LogMonsterSprite(int width, int height, int color) {
        super(width, height, color);
    }

    public LogMonsterSprite(int width, int height, int x, int y, SpriteSheet spriteSheet) {
        super(width, height, x, y, spriteSheet);
    }
}
