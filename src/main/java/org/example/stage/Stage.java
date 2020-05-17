package org.example.stage;

import org.example.*;
import org.example.food.RegularFood;
import org.example.guard.BoundaryGuard;
import org.example.guard.HasEatenGuard;
import org.example.guard.OverlapGuard;
import org.example.guard.implementation.BoundaryGuardImpl;
import org.example.guard.implementation.HasEatenGuardImpl;
import org.example.guard.implementation.OverlapGuardImpl;
import org.example.reaction.Reaction;

import java.awt.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Stage implements Reaction {

    private static final Logger LOGGER = Logger.getLogger(Stage.class.getName());

    public int eatCount;
    public int eatPoint;
    public Gear gear;
    public StagePainter stagePainter;

    private BoundaryGuard boundaryGuard = new BoundaryGuardImpl();
    private HasEatenGuard hasEatenGuard = new HasEatenGuardImpl();
    private OverlapGuard overlapGuard = new OverlapGuardImpl();

    public GameControl gameControl;
    public StageBootstrap stageBootstrap;

    private Score score;
    RegularFood food;
    private final Random random = new Random();

    private Stage() {}

    public void putFood() {
        food.setPoint(new Point(random.nextInt(App.WIDTH_SPOTS+1), random.nextInt(App.HEIGHT_SPOTS+1)));
        hasEatenGuard.setFoodPoint(food.getPoint());
    }

    public void paintFood() {
        if (food != null && food.getPoint() != null) stagePainter.paintRegularFood(food.getPoint());
    }

    public void paintScorePanel() {
        stagePainter.paintScorePanel(score);
    }

    @Override
    public void react() {
        LOGGER.log(Level.INFO, "has eaten!!!");
        gameControl.grow();
        putFood();
        score.totalScore += eatPoint;
        eatCount--;
        if (eatCount == 0){
            gameControl.resetSnake();
            gameControl.loadStage(stageBootstrap.nextStage());
        }
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public void initStage() {
        food = new RegularFood(this);
        hasEatenGuard.setReaction(food);
        Reaction defaultCrashReaction = new Reaction() {
            @Override
            public void react() {
                gameControl.resetSnake();
                setGuards();
                overlapGuard.setRoot(gameControl.getSnake().root);
            }
        };

        overlapGuard.setRoot(gameControl.getSnake().root);
        setGuards();

        overlapGuard.setReaction(defaultCrashReaction);
        boundaryGuard.setReaction(defaultCrashReaction);
        gameControl.setGear(gear);

    }

    private void setGuards() {
        gameControl.getSnake().addGuard(boundaryGuard);
        gameControl.getSnake().addGuard(overlapGuard);
        gameControl.getSnake().addGuard(hasEatenGuard);
    }

    public static Stage of(int eatCount, int eatPoint, Gear gear, StagePainter stagePainter,
                           final GameControl gameControl, StageBootstrap stageBootstrap) {
        final Stage stage = new Stage();
        stage.eatCount = eatCount;
        stage.eatPoint = eatPoint;
        stage.gear = gear;
        stage.stagePainter = stagePainter;
        stage.gameControl = gameControl;
        stage.stageBootstrap = stageBootstrap;

        return stage;
    }

}
