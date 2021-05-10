package org.jetpack.controller;

import org.jetpack.states.GameState;
import org.jetpack.states.MainMenuState;
import org.jetpack.gui.GUI;
import org.jetpack.states.State;

import java.io.IOException;

public class GameLoop {
    private final int FPS;
    private final GUI gui;
    private State currentGameState;
    private boolean running;

    public GameLoop(int FPS, GUI gui) {
        this.FPS = FPS;
        this.gui = gui;
        this.currentGameState = new MainMenuState(this, gui);
        this.running = false;
    }

    public void run() throws IOException {
        running = true;
        long millisecondsPerFrame = 1000 / FPS;
        long lastInstant = System.currentTimeMillis(), currentInstant, elapsedInstants;

        while (running) {
            currentInstant = System.currentTimeMillis();
            elapsedInstants = currentInstant - lastInstant;

            GUI.ACTION action = processInput();
            if (action == GUI.ACTION.QUIT) running = false;  // TODO: To be deleted soon
            this.currentGameState.update(action, elapsedInstants);
            this.currentGameState.render();

            lastInstant = currentInstant;
            try {
                Thread.sleep(Math.max(0, currentInstant + millisecondsPerFrame - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        gui.close();
    }

    public void setState(State state) {
        this.currentGameState = state;
    }

    public void stop() {
        this.running = false;
    }

    private GUI.ACTION processInput() throws IOException {
        return gui.getNextAction();
    }
}
