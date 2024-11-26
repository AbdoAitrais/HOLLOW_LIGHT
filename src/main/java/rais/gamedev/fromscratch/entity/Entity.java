package rais.gamedev.fromscratch.entity;

import rais.gamedev.fromscratch.graphics.Screen;
import rais.gamedev.fromscratch.level.Level;

import java.util.Random;

public abstract class Entity {

    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update() {

    }

    public void render(Screen screen) {

    }

    public void setRemoved(boolean removed) {
        // remove from level
        this.removed = removed;
    }

    public boolean isRemoved() {
        return removed;
    }
}
