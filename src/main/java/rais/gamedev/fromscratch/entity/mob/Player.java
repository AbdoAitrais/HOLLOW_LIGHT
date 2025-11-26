package rais.gamedev.fromscratch.entity.mob;

import rais.gamedev.fromscratch.Game;
import rais.gamedev.fromscratch.entity.projectile.FireBolt;
import rais.gamedev.fromscratch.entity.projectile.Projectile;
import rais.gamedev.fromscratch.graphics.Screen;
import rais.gamedev.fromscratch.graphics.Sprite;
import rais.gamedev.fromscratch.input.KeyBoard;
import rais.gamedev.fromscratch.input.Mouse;

public class Player extends Mob {

    private final KeyBoard keyBoardInput;
    private Sprite sprite;
    private int animate = 0;
    private boolean walking = false;

    private int fireRate; //  represents a gun

    public Player(KeyBoard keyBoardInput) {
        this.keyBoardInput = keyBoardInput;
        sprite = Sprite.playerForward;
        fireRate = FireBolt.FIRE_RATE;
    }

    public Player(int x, int y, KeyBoard keyBoardInput) {
        this.x = x;
        this.y = y;
        this.keyBoardInput = keyBoardInput;
        sprite = Sprite.playerForward;
    }

    public void update() {
        int xChange = 0, yChange = 0;
        if (animate < Integer.MAX_VALUE) animate++;
        else animate = 0;
        if (keyBoardInput.left) xChange--;
        if (keyBoardInput.right) xChange++;
        if (keyBoardInput.up) yChange--;
        if (keyBoardInput.down) yChange++;
        // Only move if the playerForward actually moved
        if (xChange != 0 || yChange != 0) {
            walking = true;
            move(xChange, yChange);
        } else walking = false;
        for (Projectile projectile: projectiles) {
            projectile.update();
        }
        updateShooting();
        removeProjectiles();
    }

    private void updateShooting() {
        if (fireRate > 0) fireRate--;
        if (Mouse.getMouseButton() == 1 && fireRate == 0) {
            double xAdjacent = Mouse.getxMouse() - (double) Game.getWindowWidth() /2;
            double yOpposite = Mouse.getyMouse() -  (double) Game.getWindowHeight() /2;
            double angle = Math.atan2(yOpposite, xAdjacent);
            shoot(x, y, angle);
            fireRate = FireBolt.FIRE_RATE;
        }
    }

    private void removeProjectiles() {
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);
            if (projectile.isRemoved()) projectiles.remove(projectile);
        }
    }

    public void render(Screen screen) {
        boolean xFlip = false, yFlip = false;

        if (animate % 20 > 10 && walking) sprite = Sprite.playerSide_1;
        else if (!walking) sprite = Sprite.playerSide;
        else sprite = Sprite.playerSide_2;

        if (direction == Direction.North.ordinal()) {
            if (animate % 20 > 10 && walking) sprite = Sprite.playerForward_1;
            else if (!walking) sprite = Sprite.playerForward;
            else sprite = Sprite.playerForward_2;
        } else if (direction == Direction.South.ordinal()) {
            if (animate % 20 > 10 && walking) sprite = Sprite.playerBackward_1;
            else if (!walking) sprite = Sprite.playerBackward;
            else sprite = Sprite.playerBackward_2;
        } else if (direction == Direction.West.ordinal()) {
            xFlip = true;
        }
        screen.renderMob(x, y, sprite, xFlip, yFlip, 0xffffff);
        for (Projectile projectile: projectiles) {
            projectile.render(screen);
        }
    }

}
