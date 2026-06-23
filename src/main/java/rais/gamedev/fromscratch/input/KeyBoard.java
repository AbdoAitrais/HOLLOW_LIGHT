package rais.gamedev.fromscratch.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.im.InputContext;
import java.util.Locale;

public class KeyBoard implements KeyListener {

    private final boolean[] keys = new boolean[120];
    public boolean up, down, left, right;
    private final InputContext context = InputContext.getInstance();


    public void update() {
        Locale locale = context.getLocale();
        String language = locale.getLanguage();
        if (language.equals("en")) {
            up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
            down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
            left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
            right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        } else if (language.equals("fr")) {
            up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_Z];
            down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
            left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_Q];
            right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() < keys.length)
            keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < keys.length)
            keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
