package rais.gamedev.fromscratch.entity.projectile;

import rais.gamedev.fromscratch.entity.Entity;
import rais.gamedev.fromscratch.graphics.Screen;
import rais.gamedev.fromscratch.graphics.Sprite;

public abstract class Projectile extends Entity {
    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double xNew, yNew;
    protected double speed, fireRate, range, damage;

    public Projectile(int x, int y, double angle) {
        xOrigin = x;
        yOrigin = y;
        this.angle = angle;
        this.x = x;
        this.y = y;
    }

    protected void move() {

    }

    public void render(Screen screen) {
        screen.renderObject(x, y, sprite);
    }
}
