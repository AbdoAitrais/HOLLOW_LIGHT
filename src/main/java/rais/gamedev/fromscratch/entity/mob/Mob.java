package rais.gamedev.fromscratch.entity.mob;

import rais.gamedev.fromscratch.entity.Entity;
import rais.gamedev.fromscratch.entity.projectile.FireBolt;
import rais.gamedev.fromscratch.entity.projectile.Projectile;
import rais.gamedev.fromscratch.graphics.Sprite;
import rais.gamedev.fromscratch.level.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Mob ( short for mobile ) represents any mobile entity like players, monsters and animals ...
 */
public class Mob extends Entity {

    public Sprite sprite;
    public int direction = 0; // determines the direction to which the playerForward moves ( North, East, South and West )
    public boolean moving = false;
    public List<Projectile> projectiles = new ArrayList<>();

    // takes the amount by which the Mob's coordinates change in order to implement movement
    public void move(int xChange, int yChange) {
        // we are moving diagonally then move on each access separately
        if(xChange != 0 && yChange != 0) {
            move(xChange, 0);
            move(0, yChange);
            return;
        }

        if (xChange > 0) direction = Direction.East.ordinal();
        if (xChange < 0) direction = Direction.West.ordinal();
        if (yChange > 0) direction = Direction.South.ordinal();
        if (yChange < 0) direction = Direction.North.ordinal();

        if (!collision(xChange, yChange)) {
            x += xChange;
            y += yChange;
        }
    }

    protected void shoot(int xO, int yO, double angle) {
//        System.out.println(Math.toDegrees(angle));
        Projectile projectile = new FireBolt(xO, yO, angle);
        projectiles.add(projectile);
    }

    public void render() {

    }

    public boolean collision(int xChange, int yChange) {
        // for every corner
        for (int corner = 0; corner < 4; corner++) {
            int xNextTile = ((x + xChange) + corner % 2 * 22 - 3) >> Level.TILE_SIZE_SHIFTING;
            int yNextTile = ((y + yChange) + corner / 2 * 16 + 12) >> Level.TILE_SIZE_SHIFTING;
            if (level.getTile(xNextTile, yNextTile).solid()) return true;
        }
        return false;
    }

}
