package org.example.guard;

import org.example.Snake;
import org.example.reaction.Reaction;

import java.awt.*;

public interface Guard {

    void setFoodPoint(Point food);
    void setRoot(Snake.SnakePart root);
    void setReaction(Reaction reaction);
    boolean checkMove(Snake.SnakePart snake);
}
