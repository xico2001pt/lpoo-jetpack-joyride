package org.jetpack;

import org.jetpack.controller.ArenaController;
import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.gui.LanternaGUI;
import org.jetpack.model.arena.ArenaBuilder;
import org.jetpack.model.arena.RandomArenaBuilder;
import org.jetpack.viewer.WindowViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    public static void main(String[] args) {
        try {
            createRandomLevel(30, 30);
        } catch (FontFormatException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void createRandomLevel(int width, int height) throws FontFormatException, IOException, URISyntaxException {
        GUI gui = new LanternaGUI(width, height);
        ArenaBuilder arenaBuilder = new RandomArenaBuilder(width, height);
        ArenaController controller = new ArenaController(arenaBuilder);

        GameLoop gameLoop = new GameLoop(60, controller, new WindowViewer(gui), gui);

        gameLoop.run();
    }
}
