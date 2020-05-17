package org.example.stage;

import org.example.GameControl;
import org.example.Gear;
import org.example.StageBootstrap;
import org.example.StagePainter;

public class StageBuilder {

    public int eatCount;
    public int eatPoint;
    public Gear gear;
    public StagePainter stagePainter;
    public GameControl gameControl;
    public StageBootstrap stageBootstrap;

    private static StageBuilder stageBuilder;

    public static StageBuilder getInstance() {
        if (stageBuilder == null)
            stageBuilder = new StageBuilder();
        return stageBuilder;
    }

    public StageBuilder withEatCount(int eatCount) {
        this.eatCount = eatCount;
        return this;
    }

    public StageBuilder withEatPoint(int eatPoint) {
        this.eatPoint = eatPoint;
        return this;
    }

    public StageBuilder withGear(Gear gear) {
        this.gear = gear;
        return this;
    }

    public StageBuilder withStagePainter(StagePainter stagePainter){
        this.stagePainter = stagePainter;
        return this;
    }

    public StageBuilder withGameControl(GameControl gameControl) {
        this.gameControl = gameControl;
        return this;
    }

    public StageBuilder withStageBootstrap(StageBootstrap stageBootstrap) {
        this.stageBootstrap = stageBootstrap;
        return this;
    }

    public Stage build() {
        return Stage.of(eatCount, eatPoint, gear, stagePainter, gameControl, stageBootstrap);
    }
}
