package org.jetpack;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.gui.LanternaGUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final static int WIDTH = 30;
    private final static int HEIGHT = 20;
    private final static int FPS = 60;

    public static void main(String[] args) {
        try {
            GUI gui = new LanternaGUI(WIDTH, HEIGHT);
            GameLoop gameLoop =new GameLoop(FPS, gui);
            gameLoop.run();
        } catch (FontFormatException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
