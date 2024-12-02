package rais.gamedev.fromscratch.entity.projectile;

import rais.gamedev.fromscratch.graphics.Sprite;

public class FireBolt extends Projectile {

    public FireBolt(int x, int y, double angle) {
        super(x, y, angle);
        range = 200;
        speed = 1.4;
        damage = 20;
        fireRate = 15;
        sprite = Sprite.fire;
        xNew = Math.cos(this.angle) * speed; // rate at which the projectile travels with x Axis
        yNew = Math.sin(this.angle) * speed; // rate at which the projectile travels with y Axis
    }

    public void update() {
        move();
    }

    public void move() {
        x += xNew;
        y += yNew;
    }
}
