package org.example;

import org.example.stage.Stage;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller extends Engine implements KeyListener, GameControl {

    enum GameState {
        STOPPED, STARTED, PAUSED
    }

    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());

    private GameState state = GameState.STOPPED;
    private Screen screen;
    Stage stage;
    Snake snake;

    private Gear currentGear;
    private boolean directionBlocked;

    public Controller(JFrame mainWindow, Screen screen) {
        super(mainWindow, screen);
        this.screen = screen;

        currentDirection = Direction.RIGHT;
        snake = new Snake(currentDirection, screen);

        StageBootstrap stageBootstrap = new StageBootstrap(this, screen);
        stage = stageBootstrap.nextStage();
        stage.initStage();

        startGame();
    }

    public void initGame() {
        screen.initScreen();
        new Thread(this).start();
    }

    private void startGame() {
        snake.isMoving(true);
        stage.putFood();
        currentGear = gear;
        directionBlocked = false;
        state = GameState.STARTED;
        running = true;
    }

    @Override
    public void resetSnake() {
        currentDirection = Direction.RIGHT;
        snake = new Snake(currentDirection, screen);
        snake.isMoving(true);
    }

    @Override
    public void grow() {
        snake.grow();
    }

    @Override
    public Snake getSnake() {
        return snake;
    }

    @Override
    public void setGear(Gear gear) {
        this.gear = gear;
    }

    @Override
    public void loadStage(Stage stage) {
        this.stage = stage;
        this.stage.initStage();
        this.stage.putFood();
    }

    @Override
    void paintStage() {
        screen.paintStage();
        stage.paintFood();
        stage.paintScorePanel();
    }

    @Override
    void paintSnake(boolean move) {
        if (state.equals(GameState.STARTED) || state.equals(GameState.PAUSED)) {
            snake.draw(currentDirection, move);
            /*if (move)*/ directionBlocked = false;
        }
    }

    @Override
    boolean onNewDirection(Direction newDirection) {
        if (currentDirection.equals(newDirection)) return true;
        if (state.equals(GameState.STARTED) &&
                newDirection != null && newDirection.getReverse() != currentDirection.getKeyCode()) {
            if (!directionBlocked) {
                currentDirection = newDirection;
                directionBlocked = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // don´t need this method
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (state.equals(GameState.STOPPED)) startGame();
            if (state.equals(GameState.PAUSED)) {
                snake.isMoving(true);
                state = GameState.STARTED;
                running = true;
            }
            return;
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            snake.isMoving(false);
            state = GameState.PAUSED;
            running = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            currentGear = gear = gear.up();
            LOGGER.log(Level.INFO, "currentGear={0}", currentGear);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            currentGear = gear = gear.down();
            LOGGER.log(Level.INFO, "currentGear={0}", currentGear);
        }

    }

}
