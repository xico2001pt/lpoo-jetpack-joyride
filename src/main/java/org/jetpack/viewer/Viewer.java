package org.jetpack.viewer;

import org.jetpack.gui.GUI;

import java.io.IOException;

public abstract class Viewer<T> {
    private final T model;

    public Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void draw(GUI gui) throws IOException {
        gui.clear();
        drawModel(gui);
        gui.refresh();
    }

    protected abstract void drawModel(GUI gui);
}
