package controller;

import gui.GUI;
import viewer.WindowViewer;

import java.io.IOException;

public class GameLoop {
    private final int FPS;
    private boolean running;

    private final GameController gameController;
    private final PlayerController playerController;
    private final ElementController elementController;

    private final WindowViewer windowViewer;
    private final GUI gui;

    public GameLoop(int FPS, GameController gameController, WindowViewer windowViewer, GUI gui) {
        this.FPS = FPS;
        this.running = false;

        this.gameController = gameController;
        this.playerController = new PlayerController(gameController.getArena());
        this.elementController = new ElementController(gameController.getArena());

        this.windowViewer = windowViewer;
        this.gui = gui;
    }

    public void run() throws IOException {
        running = true;
        long millisecondsPerFrame = 1000 / FPS;
        long lastInstant = System.currentTimeMillis(), currentInstant, elapsedInstants;

        while (running) {
            currentInstant = System.currentTimeMillis();
            elapsedInstants = currentInstant - lastInstant;

            GUI.ACTION action = processInput();
            if (action == GUI.ACTION.QUIT) running = false;
            update(action, elapsedInstants);
            render();

            lastInstant = currentInstant;
            try {
                Thread.sleep(Math.max(0, currentInstant + millisecondsPerFrame - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        windowViewer.close();
    }

    private GUI.ACTION processInput() throws IOException {
        return gui.getNextAction();
    }

    private void update(GUI.ACTION action, long elapsed) {
        gameController.getArenaBuilder().incrementInstant(elapsed);
        gameController.getArena().addCoins(gameController.getArenaBuilder().getCoins());
        gameController.getArena().addObstacles(gameController.getArenaBuilder().getObstacles());

        playerController.doAction(action);
        elementController.moveElements(elapsed);
        gameController.handleCollisions();

        if (gameController.getArena().getPlayer().getLives() <= 0) running = false;
    }

    private void render() throws IOException {
        windowViewer.draw(gameController.getArena());
    }
}
