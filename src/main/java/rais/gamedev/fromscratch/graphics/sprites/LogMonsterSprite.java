package rais.gamedev.fromscratch.graphics.sprites;

import rais.gamedev.fromscratch.graphics.SpriteSheet;

public class LogMonsterSprite extends  Sprite {

    public static Sprite log_monster = new Sprite(32,32,0,0,SpriteSheet.log);
    public static Sprite log_monsterForward_1 = new Sprite(32,32,1,0,SpriteSheet.log);
    public static Sprite log_monsterForward_2 = new Sprite(32,32,3,0,SpriteSheet.log);

    public static Sprite log_monsterBackward = new Sprite(32,32,0,1,SpriteSheet.log);
    public static Sprite log_monsterBackward_1 = new Sprite(32,32,1,1,SpriteSheet.log);
    public static Sprite log_monsterBackward_2 = new Sprite(32,32,3,1,SpriteSheet.log);

    public static Sprite log_monsterSide = new Sprite(32,32,0,2,SpriteSheet.log);
    public static Sprite log_monsterSide_1 = new Sprite(32,32,1,2,SpriteSheet.log);
    public static Sprite log_monsterSide_2 = new Sprite(32,32,3,2,SpriteSheet.log);


    public LogMonsterSprite(int width, int height, int color) {
        super(width, height, color);
    }

    public LogMonsterSprite(int width, int height, int x, int y, SpriteSheet spriteSheet) {
        super(width, height, x, y, spriteSheet);
    }
}
