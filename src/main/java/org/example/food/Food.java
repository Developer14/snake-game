package org.example.food;

import org.example.reaction.Reaction;

import java.awt.*;

public abstract class Food implements Reaction {

    public enum FoodType {
        REGULAR, BREAK_THROUGH_POWER, TURBO_POWER
    }

    protected Point point;
    protected Reaction defaultReaction;

    public Food(Reaction defaultReaction) {
        this.defaultReaction = defaultReaction;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setDefaultReaction(Reaction defaultReaction) {
        this.defaultReaction = defaultReaction;
    }

    @Override
    public void react() {
        defaultReaction.react();
    }
}
