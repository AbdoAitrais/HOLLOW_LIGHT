package rais.gamedev.fromscratch.entity.mob;

import rais.gamedev.fromscratch.entity.Entity;
import rais.gamedev.fromscratch.graphics.Sprite;

/**
 * Mob ( short for mobile ) represents any mobile entity like players, monsters and animals ...
 */
public class Mob extends Entity {

    public Sprite sprite;
    public int direction = 0; // determines the direction to which the player moves ( North, East, South and West )
    public boolean moving = false;

    // takes the amount by which the Mob's coordinates change in order to implement movement
    public void move(int xChange, int yChange) {
        if (xChange > 0) direction = Direction.East.ordinal();
        if (xChange < 0) direction = Direction.West.ordinal();
        if (yChange > 0) direction = Direction.South.ordinal();
        if (yChange < 0) direction = Direction.North.ordinal();

        if (!collision()) {
            x += xChange;
            y += yChange;
        }
    }

    public void render() {

    }

    public boolean collision() {
        return false;
    }

}
