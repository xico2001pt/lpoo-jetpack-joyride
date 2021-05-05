import controller.ArenaController;
import gui.GUI;
import gui.LanternaGUI;
import model.arena.Arena;
import model.arena.ArenaBuilder;
import model.arena.RandomArenaBuilder;
import viewer.ArenaViewer;
import viewer.WindowViewer;

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

        ArenaController controller = new ArenaController(arenaBuilder, new WindowViewer(gui), gui);

        controller.start(30);
    }
}
