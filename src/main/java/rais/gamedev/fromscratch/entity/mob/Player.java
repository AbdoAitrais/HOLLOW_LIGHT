package rais.gamedev.fromscratch.entity.mob;

import rais.gamedev.fromscratch.graphics.Screen;
import rais.gamedev.fromscratch.graphics.Sprite;
import rais.gamedev.fromscratch.input.KeyBoard;

public class Player extends Mob {

    private final KeyBoard keyBoardInput;

    public Player(KeyBoard keyBoardInput) {
        this.keyBoardInput = keyBoardInput;
    }

    public Player(int x, int y, KeyBoard keyBoardInput) {
        this.x = x;
        this.y = y;
        this.keyBoardInput = keyBoardInput;
    }

    public void update() {

        int xChange = 0, yChange = 0;
        if (keyBoardInput.left) xChange--;
        if (keyBoardInput.right) xChange++;
        if (keyBoardInput.up) yChange--;
        if (keyBoardInput.down) yChange++;
        // Only move if the player actually moved
        if (xChange != 0 || yChange != 0) move(xChange, yChange);
    }

    public void render(Screen screen) {
        screen.renderPlayer(x, y, Sprite.character);
    }

}
