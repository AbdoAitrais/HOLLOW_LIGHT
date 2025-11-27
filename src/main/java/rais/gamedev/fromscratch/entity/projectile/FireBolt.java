package rais.gamedev.fromscratch.entity.projectile;

import rais.gamedev.fromscratch.graphics.sprites.SpellSprite;

public class FireBolt extends Projectile {

    public static final int FIRE_RATE = 10; // higher slower

    public FireBolt(int x, int y, double angle) {
        super(x, y, angle);
        range = 5000;
        speed = 2;
        damage = 20;
        sprite = SpellSprite.fire;
        xNew = Math.cos(this.angle) * speed; // rate at which the projectile travels with x Axis
        yNew = Math.sin(this.angle) * speed; // rate at which the projectile travels with y Axis
    }

    public void update() {
        move();
        if (distance() > range) setRemoved(true);
    }

    public void move() {
        x += xNew;
        y += yNew;
    }
}
