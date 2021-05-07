package org.jetpack.controller;

import org.jetpack.controller.state.State;
import org.jetpack.gui.GUI;
import org.jetpack.viewer.WindowViewer;

import java.io.IOException;

public class GameLoop {
    private final int FPS;
    private boolean running;
    private GUI gui;
    private State currentState;

    public GameLoop(State initialState, int FPS, GUI gui) {
        this.FPS = FPS;
        this.running = false;
        this.gui = gui;
        this.currentState = initialState;
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
        gameController.updateArena(action, elapsed);

        if (gameController.getArena().getPlayer().getLives() <= 0) running = false;
    }

    private void render() throws IOException {
        windowViewer.draw(gameController.getArena());
    }
}
