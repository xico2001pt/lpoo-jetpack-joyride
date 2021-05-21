package org.jetpack.controller;

import org.jetpack.gui.GUI;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return this.model;
    }

    public abstract void update(GameLoop gameLoop, GUI.ACTION action, long elapsed);
}
