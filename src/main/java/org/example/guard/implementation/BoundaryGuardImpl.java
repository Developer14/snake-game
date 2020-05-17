package org.example.guard.implementation;

import org.example.App;
import org.example.Snake;
import org.example.guard.BoundaryGuard;
import org.example.reaction.Reaction;

import java.awt.*;

public class BoundaryGuardImpl implements BoundaryGuard {

    private Reaction reaction;

    @Override
    public boolean checkMove(Snake.SnakePart snakePart) {
        boolean crashes = false;
        if (snakePart.isRoot){
            crashes = snakePart.point.x < 0 || snakePart.point.x > App.WIDTH_SPOTS
                    || snakePart.point.y < 0 || snakePart.point.y > App.HEIGHT_SPOTS;
            if (crashes && reaction != null)
                reaction.react();
        }

        return crashes;
    }

    @Override
    public void setRoot(Snake.SnakePart root) {
        // not needed here
    }

    @Override
    public void setFoodPoint(Point food) {
        // not needed here
    }

    @Override
    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }
}
