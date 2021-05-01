package controller;

import model.arena.Arena;
import model.arena.ArenaBuilder;
import viewer.ArenaViewer;
import gui.GUI;
import viewer.WindowViewer;

import java.io.IOException;

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

    public void start() throws IOException {
        int frames = 0;
        ArenaBuilder arenaBuilder = getArena().getSupplier();
        while (getArena().getPlayer().getLives() > 0) {
            System.out.println(frames);
            viewer.draw(getArena());
            GUI.ACTION action = viewer.getArenaViewer().getNextAction();

            if (action == GUI.ACTION.QUIT) break;

            playerController.doAction(action);
            elementController.moveElements();
            getArena().addCoins(arenaBuilder.getCoins(frames));
            getArena().addObstacles(arenaBuilder.getObstacles(frames));
            frames++;
        }
    }
}
