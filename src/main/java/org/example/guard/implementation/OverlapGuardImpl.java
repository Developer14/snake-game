package org.example.guard.implementation;

import org.example.Snake;
import org.example.guard.OverlapGuard;
import org.example.reaction.Reaction;

import java.awt.*;

public class OverlapGuardImpl implements OverlapGuard {

    private Snake.SnakePart root;
    private Reaction reaction;

    @Override
    public boolean checkMove(Snake.SnakePart snake) {
        return checkOverlap(snake);
    }

    private boolean checkOverlap(Snake.SnakePart snake) {
        boolean isOverlapped = !snake.isRoot && snake.point.equals(root.point);
        if (isOverlapped && reaction != null)
            reaction.react();
        return isOverlapped;
    }

    @Override
    public void setFoodPoint(Point food) {
        // not needed here
    }

    @Override
    public void setRoot(Snake.SnakePart root) {
        this.root = root;
    }

    @Override
    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }
}
