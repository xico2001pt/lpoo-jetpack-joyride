package controller;

import model.arena.Arena;
import model.arena.ArenaBuilder;
import viewer.ArenaViewer;
import gui.GUI;
import viewer.WindowViewer;

import java.io.IOException;
import java.util.Date;

public class ArenaController extends GameController {
    private final PlayerController playerController;
    private final ElementController elementController;

    private final WindowViewer viewer;

    public ArenaController(Arena arena, WindowViewer viewer) {
        super(arena);

        this.playerController = new PlayerController(arena);
        this.elementController = new ElementController(arena);
        this.viewer = viewer;
    }

    public void start(int fps) throws IOException {
        long millisecondsPerFrame = 1000 / fps;
        long initialInstant = new Date().getTime(), loopInstant, actualInstant;

        ArenaBuilder arenaBuilder = getArena().getSupplier();
        while (getArena().getPlayer().getLives() > 0) {
            System.out.println(millisecondsPerFrame);
            loopInstant = new Date().getTime();
            viewer.draw(getArena());
            GUI.ACTION action = viewer.getArenaViewer().getNextAction();

            if (action == GUI.ACTION.QUIT) break;

            playerController.doAction(action);
            elementController.moveElements();

            actualInstant = new Date().getTime() - initialInstant;
            getArena().addCoins(arenaBuilder.getCoins(actualInstant));
            getArena().addObstacles(arenaBuilder.getObstacles(actualInstant));

            do {
                actualInstant = new Date().getTime();
            } while (actualInstant - loopInstant < millisecondsPerFrame);
        }

        viewer.close();
    }
}
