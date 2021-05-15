package org.jetpack.states;

import org.jetpack.controller.Controller;
import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.viewer.Viewer;

import java.io.IOException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public State(T model, Controller<T> controller, Viewer<T> viewer) {
        this.model = model;
        this.viewer = viewer;
        this.controller = controller;
    }

    public T getModel() {
        return model;
    }

    public void step(GameLoop game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getNextAction();
        controller.update(game, action, time);
        viewer.draw(gui);
    }
}
