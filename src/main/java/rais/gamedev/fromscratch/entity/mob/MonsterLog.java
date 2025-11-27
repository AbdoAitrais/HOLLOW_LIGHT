package rais.gamedev.fromscratch.entity.mob;

import rais.gamedev.fromscratch.entity.projectile.FireBolt;
import rais.gamedev.fromscratch.graphics.Screen;
import rais.gamedev.fromscratch.graphics.sprites.LogMonsterSprite;
import rais.gamedev.fromscratch.graphics.sprites.Sprite;

import java.util.Random;

public class MonsterLog extends Mob {
    private Sprite sprite;
    private int animate = 0;
    private boolean walking = false;
    private int fireRate; //  represents a gun

    public MonsterLog() {
        sprite = LogMonsterSprite.log_monster;
        fireRate = FireBolt.FIRE_RATE;
    }

    public MonsterLog(int x, int y) {
        this.x = x;
        this.y = y;
        sprite = LogMonsterSprite.log_monster;
    }

    public void update() {
        int xChange = 0, yChange = 0;
        if (animate < Integer.MAX_VALUE) animate++;
        else animate = 0;
        int randomDirection = new Random().nextInt(4);
        if (Direction.West.ordinal() == randomDirection) xChange--;
        if (Direction.East.ordinal() == randomDirection) xChange++;
        if (Direction.North.ordinal() == randomDirection) yChange--;
        if (Direction.South.ordinal() == randomDirection) yChange++;
        // Only move if the playerForward actually moved
        if (xChange != 0 || yChange != 0) {
            walking = true;
            move(xChange, yChange);
        } else walking = false;
    }

    public void render(Screen screen) {
        boolean xFlip = false, yFlip = false;

        if (animate % 20 > 10 && walking) sprite = LogMonsterSprite.log_monster;
        else if (!walking) sprite = LogMonsterSprite.log_monster;
        else sprite = LogMonsterSprite.log_monster;

//        if (direction == Direction.North.ordinal()) {
//            if (animate % 20 > 10 && walking) sprite = Sprite.playerForward_1;
//            else if (!walking) sprite = Sprite.playerForward;
//            else sprite = Sprite.playerForward_2;
//        } else if (direction == Direction.South.ordinal()) {
//            if (animate % 20 > 10 && walking) sprite = Sprite.playerBackward_1;
//            else if (!walking) sprite = Sprite.playerBackward;
//            else sprite = Sprite.playerBackward_2;
//        } else if (direction == Direction.West.ordinal()) {
//            xFlip = true;
//        }
        screen.renderMob(x, y, sprite, xFlip, yFlip, 0x0000);
//        for (Projectile projectile: projectiles) {
//            projectile.render(screen);
//        }
    }

}
