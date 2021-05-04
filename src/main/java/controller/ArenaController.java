package controller;

import gui.GUI;
import model.arena.ArenaBuilder;
import viewer.WindowViewer;

import java.io.IOException;

public class ArenaController extends GameController {
    private final ArenaBuilder arenaBuilder;
    private final PlayerController playerController;
    private final ElementController elementController;
    private final WindowViewer viewer;

    public ArenaController(ArenaBuilder arenaBuilder, WindowViewer viewer) {
        super(arenaBuilder.createArena());

        this.arenaBuilder = arenaBuilder;
        this.playerController = new PlayerController(getArena());
        this.elementController = new ElementController(getArena());
        this.viewer = viewer;
    }

    public void start(int fps) throws IOException {
        long millisecondsPerFrame = 1000 / fps;
        long lastInstant = System.currentTimeMillis(), currentInstant, elapsedInstants;

        while (getArena().getPlayer().getLives() > 0) {
            currentInstant = System.currentTimeMillis();
            elapsedInstants = currentInstant - lastInstant;

            GUI.ACTION action = processInput();
            if (action == GUI.ACTION.QUIT) break;
            update(action, elapsedInstants);
            render();

            lastInstant = currentInstant;
            try {
                Thread.sleep(Math.max(0, currentInstant + millisecondsPerFrame - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        viewer.close();
    }

    private GUI.ACTION processInput() throws IOException {
        return viewer.getArenaViewer().getNextAction();
    }

    private void update(GUI.ACTION action, long elapsed) {
        playerController.doAction(action);
        elementController.moveElements();

        arenaBuilder.incrementInstant(elapsed);
        getArena().addCoins(arenaBuilder.getCoins());
        getArena().addObstacles(arenaBuilder.getObstacles());
    }

    private void render() throws IOException {
        viewer.draw(getArena());
    }
}
