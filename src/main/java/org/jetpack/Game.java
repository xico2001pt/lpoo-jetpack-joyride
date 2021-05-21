package org.jetpack;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.gui.LanternaGUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    public static void main(String[] args) {
        try {
            createRandomLevel(30, 20);
        } catch (FontFormatException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void createRandomLevel(int width, int height) throws FontFormatException, IOException, URISyntaxException {
        GUI gui = new LanternaGUI(width, height);

        GameLoop gameLoop = new GameLoop(60, gui);

        gameLoop.run();
    }
}
