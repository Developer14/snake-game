package org.example;

import org.example.stage.Score;

import java.awt.*;

import static org.example.Snake.SIZE;

public class Screen extends Canvas implements StagePainter, SnakePartPainter, ScreenPainter {

    private transient Graphics2D graphics2D;

    public void initScreen() {
        setBounds(0, 0, App.GAME_WIDTH, App.GAME_HEIGHT);
        createBufferStrategy(2);
        graphics2D = (Graphics2D) getBufferStrategy().getDrawGraphics();
    }

    @Override
    public void paintStage() {
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0,0, getWidth(), getHeight());
    }

    @Override
    public void draw(Point point, boolean isRoot) {
        graphics2D.setColor(isRoot ? Color.orange : Color.pink);
        graphics2D.fillOval(point.x * SIZE, point.y * SIZE, SIZE, SIZE);
    }

    @Override
    public void paintScorePanel(Score score) {
        graphics2D.setColor(Color.white);
        graphics2D.setFont(new Font(null, Font.BOLD, 25));
        graphics2D.drawString(String.valueOf(score.totalScore), 10, 550);
        graphics2D.drawString(String.valueOf(score.level), 200, 550);
        graphics2D.drawString(String.valueOf(score.lives), 400, 550);
    }

    @Override
    public void paintRegularFood(Point point) {
        graphics2D.setColor(Color.green);
        paintFood(point);
    }

    @Override
    public void paintBreakThroughPowerFood(Point point) {
        graphics2D.setColor(Color.blue);
        paintFood(point);
    }

    @Override
    public void paintTurboPowerFood(Point point) {
        graphics2D.setColor(Color.red);
        paintFood(point);
    }

    private void paintFood(Point point) {
        graphics2D.fillOval(point.x * SIZE,point.y * SIZE, SIZE, SIZE);
    }

    @Override
    public void updateScreen() {
        getBufferStrategy().show();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(App.GAME_WIDTH, App.GAME_HEIGHT);
    }
}
