package org.jetpack.controller;

import org.jetpack.model.menu.MainMenu;
import org.jetpack.states.State;
import org.jetpack.states.MainMenuState;
import org.jetpack.gui.GUI;

import java.io.IOException;

public class GameLoop {
    public static final int INFO_SIZE = 1;
    private final int FPS;
    private final GUI gui;
    private State<?> currentGameState;
    private boolean running;

    public GameLoop(int FPS, GUI gui) {
        this.FPS = FPS;
        this.gui = gui;
        this.currentGameState = new MainMenuState(new MainMenu());
        this.running = false;
    }

    public void run() throws IOException {
        running = true;
        long millisecondsPerFrame = 1000 / FPS;
        long lastInstant = System.currentTimeMillis(), currentInstant, elapsedInstants;

        while (running) {
            currentInstant = System.currentTimeMillis();
            elapsedInstants = currentInstant - lastInstant;

            this.currentGameState.step(this, gui, elapsedInstants);

            lastInstant = currentInstant;
            try {
                Thread.sleep(Math.max(0, currentInstant + millisecondsPerFrame - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        gui.close();
    }

    public State<?> getState() {
        return this.currentGameState;
    }

    public void setState(State<?> state) {
        this.currentGameState = state;
    }

    public void stop() {
        this.running = false;
    }

    public int getWidth() {
        return gui.getTerminalWidth();
    }

    public int getHeight() {
        return gui.getTerminalHeight();
    }
}
