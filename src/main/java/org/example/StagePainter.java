package org.example;

import org.example.stage.Score;

import java.awt.*;

public interface StagePainter {

    void paintStage();
    void paintRegularFood(Point point);
    void paintBreakThroughPowerFood(Point point);
    void paintTurboPowerFood(Point point);
    void paintScorePanel(Score score);
}
