package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * Hello world!
 *
 */
public class App {

    public static final int WIDTH_SPOTS = 25;
    public static final int HEIGHT_SPOTS = 30;
    public static final int GAME_WIDTH = (WIDTH_SPOTS+1) * Snake.SIZE;
    public static final int GAME_HEIGHT = (HEIGHT_SPOTS+1) * Snake.SIZE;


    public static void main( String[] args )
    {

        JFrame mainWindow = new JFrame("Snake");
        Screen screen = new Screen();
        Controller controller = new Controller(mainWindow, screen);

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setMinimumSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        mainWindow.setResizable(false);
        mainWindow.getContentPane().setLayout(new BoxLayout(mainWindow.getContentPane(), BoxLayout.Y_AXIS));
        JPanel panel = (JPanel) mainWindow.getContentPane();
        panel.setLayout(null);
        panel.add(screen);
        panel.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        mainWindow.pack();
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);

        controller.initGame();

    }
}
