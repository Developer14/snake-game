package org.example.guard.implementation;

import org.example.Snake;
import org.example.guard.Guard;
import org.example.guard.HasEatenGuard;
import org.example.reaction.Reaction;

import java.awt.*;

public class HasEatenGuardImpl implements HasEatenGuard {

    private Reaction reaction;
    private Point food;

    @Override
    public boolean checkMove(Snake.SnakePart snakePart) {
        boolean hasEaten = false;
        if (snakePart.isRoot){
            hasEaten = snakePart.point.equals(food);
            if (hasEaten && reaction != null)
                reaction.react();
        }
        return hasEaten;
    }

    @Override
    public void setFoodPoint(Point food) {
        this.food = food;
    }

    @Override
    public void setRoot(Snake.SnakePart root) {
        // not needed here
    }

    @Override
    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }
}
