package org.example;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.logging.Logger;

public abstract class Engine implements Runnable, KeyListener {

    private static final Logger LOGGER = Logger.getLogger(Engine.class.getName());

    private static final int UNIT = 5;
    private static final int MILLIS = 50;
    private static final int MAX_GEAR = 8;
    private boolean alive = true;
    protected boolean running = false;
    private double revs = 0;
    protected Gear gear = Gear.ONE;

    private ScreenPainter screenPainter;
    protected Direction currentDirection;

    private boolean move = false;
    private boolean immediateMove = false;
    private int speedUpCounter = -1;

    private DecimalFormat decimalFormat = new DecimalFormat("0.0", new DecimalFormatSymbols(Locale.US));

    public Engine(JFrame mainWindow, ScreenPainter screenPainter) {
        this.screenPainter = screenPainter;
        mainWindow.addKeyListener(this);
    }

    @Override
    public void run() {

        try {
            while (alive) {
                paintStage();
                revs += gear.getRev();
                //LOGGER.log(Level.INFO, decimalFormat.format(revs));
                if (speedUpCounter > 0) --speedUpCounter;
                if (speedUpCounter == 0 || immediateMove || Double.parseDouble(decimalFormat.format(revs)) >= (MAX_GEAR - (gear.getGap()))) {
                    revs = 0;
                    move = true;
                }
                paintSnake(move);
                move = immediateMove = false;
                screenPainter.updateScreen();
                Thread.sleep(MILLIS);
            }
        } catch (InterruptedException e) {
            LOGGER.severe(e.getMessage());
            alive = false;
            Thread.currentThread().interrupt();
        }
    }

    abstract void paintStage();

    abstract void paintSnake(boolean move);

    abstract boolean onNewDirection(Direction direction);

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (onNewDirection(Direction.getByKeyCode(e.getKeyCode()))){
            immediateMove = true;
            speedUpCounter = 3;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        speedUpCounter = -1;
    }

}
