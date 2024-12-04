package rais.gamedev.fromscratch.entity.projectile;

import rais.gamedev.fromscratch.entity.Entity;
import rais.gamedev.fromscratch.graphics.Screen;
import rais.gamedev.fromscratch.graphics.Sprite;

public abstract class Projectile extends Entity {
    protected double x, y;
    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double xNew, yNew;
    protected double speed, range, damage;

    public Projectile(int x, int y, double angle) {
        xOrigin = x;
        yOrigin = y;
        this.angle = angle;
        this.x = x;
        this.y = y;
    }

    protected void move() {

    }

    protected double distance() {
        // Euclidean distance between origin of the projectile and current position
        return (x - xOrigin) * (x - xOrigin) + (y - yOrigin) * (y - yOrigin);
    }

    public void render(Screen screen) {
        screen.renderObject((int) x - 1, (int) y + 10, sprite);
    }
}
