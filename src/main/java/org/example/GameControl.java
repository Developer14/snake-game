package org.example;

import org.example.stage.Stage;

public interface GameControl {

    void grow();
    Snake getSnake();
    void resetSnake();
    void setGear(Gear gear);
    void loadStage(Stage stage);
}
