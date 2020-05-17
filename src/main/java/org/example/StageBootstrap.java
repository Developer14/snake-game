package org.example;

import org.example.stage.Score;
import org.example.stage.Stage;
import org.example.stage.StageBuilder;

import java.util.HashMap;
import java.util.Map;

public class StageBootstrap {

    private Map<Integer, Stage> stageDefMap = new HashMap<>();
    private int currentStage = 1;

    Score score = new Score();

    public StageBootstrap(GameControl gameControl, StagePainter stagePainter) {

        stageDefMap.put(1, StageBuilder.getInstance()
                .withGear(Gear.ONE)
                .withEatCount(8)
                .withEatPoint(5)
                .withStagePainter(stagePainter)
                .withGameControl(gameControl)
                .withStageBootstrap(this)
            .build()
        );

        stageDefMap.put(2, StageBuilder.getInstance()
                .withGear(Gear.TWO)
                .withEatCount(8)
                .withEatPoint(10)
                .withStagePainter(stagePainter)
                .withGameControl(gameControl)
                .withStageBootstrap(this)
            .build()
        );

        stageDefMap.put(3, StageBuilder.getInstance()
                .withGear(Gear.THREE)
                .withEatCount(12)
                .withEatPoint(15)
                .withStagePainter(stagePainter)
                .withGameControl(gameControl)
                .withStageBootstrap(this)
            .build()
        );

        stageDefMap.put(4, StageBuilder.getInstance()
                .withGear(Gear.FOUR)
                .withEatCount(12)
                .withEatPoint(18)
                .withStagePainter(stagePainter)
                .withGameControl(gameControl)
                .withStageBootstrap(this)
                .build()
        );

        stageDefMap.put(5, StageBuilder.getInstance()
                .withGear(Gear.FIVE)
                .withEatCount(14)
                .withEatPoint(20)
                .withStagePainter(stagePainter)
                .withGameControl(gameControl)
                .withStageBootstrap(this)
                .build()
        );

        stageDefMap.put(6, StageBuilder.getInstance()
                .withGear(Gear.SIX)
                .withEatCount(14)
                .withEatPoint(25)
                .withStagePainter(stagePainter)
                .withGameControl(gameControl)
                .withStageBootstrap(this)
                .build()
        );

        stageDefMap.put(7, StageBuilder.getInstance()
                .withGear(Gear.SEVEN)
                .withEatCount(15)
                .withEatPoint(25)
                .withStagePainter(stagePainter)
                .withGameControl(gameControl)
                .withStageBootstrap(this)
                .build()
        );

        stageDefMap.put(8, StageBuilder.getInstance()
                .withGear(Gear.EIGHT)
                .withEatCount(15)
                .withEatPoint(25)
                .withStagePainter(stagePainter)
                .withGameControl(gameControl)
                .withStageBootstrap(this)
                .build()
        );
    }

    public Stage nextStage() {
        score.level = currentStage;
        Stage stage = stageDefMap.get(currentStage++);
        stage.setScore(score);
        return stage;
    }
}
