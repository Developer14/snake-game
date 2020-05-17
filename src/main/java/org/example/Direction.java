package org.example;

import java.awt.event.KeyEvent;

public enum Direction {

    UP(KeyEvent.VK_UP, -1, KeyEvent.VK_DOWN),
    DOWN(KeyEvent.VK_DOWN, 1, KeyEvent.VK_UP),
    LEFT(KeyEvent.VK_LEFT, -1, KeyEvent.VK_RIGHT),
    RIGHT(KeyEvent.VK_RIGHT, 1, KeyEvent.VK_LEFT);

    private int keyCode;
    private int delta;
    private int reverse;

    Direction(int keyCode, int delta, int reverse) {
        this.keyCode = keyCode;
        this.delta = delta;
        this.reverse = reverse;
    }

    public static Direction getByKeyCode(int keyCode) {
        for (Direction direction: Direction.values()) {
            if (direction.keyCode == keyCode)
                return direction;
        }
        return null;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getDelta() {
        return delta;
    }

    public int getReverse() {
        return reverse;
    }
}
