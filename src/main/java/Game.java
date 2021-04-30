import controller.ArenaController;
import gui.GUI;
import gui.LanternaGUI;
import model.arena.Arena;
import model.arena.RandomArenaBuilder;
import viewer.ArenaViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    public static void main(String[] args) {
        try {
            createRandomLevel(20, 20);
        } catch (FontFormatException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void createRandomLevel(int width, int height) throws FontFormatException, IOException, URISyntaxException {
        GUI gui = new LanternaGUI(width, height);

        Arena arena = new RandomArenaBuilder(width, height).createArena();
        ArenaViewer viewer = new ArenaViewer(gui);
        ArenaController controller = new ArenaController(arena);

        //controller.start()
    }
}
