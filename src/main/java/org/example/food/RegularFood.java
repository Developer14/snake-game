package org.example.food;

import org.example.reaction.Reaction;

public class RegularFood extends Food {

    public RegularFood(Reaction defaultReaction) {
        super(defaultReaction);
    }
    @Override
    public void react() {
        //do customizedReaction
        defaultReaction.react();
    }
}
