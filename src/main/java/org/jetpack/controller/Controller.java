package org.jetpack.controller;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;

import java.io.IOException;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return this.model;
    }

    public abstract void step(GameLoop gameLoop, GUI.ACTION action, long elapsed) throws IOException;
}
