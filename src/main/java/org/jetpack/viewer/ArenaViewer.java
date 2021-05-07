package org.jetpack.viewer;

import org.jetpack.gui.GUI;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Element;

import java.io.IOException;
import java.util.List;

public class ArenaViewer {
    private final GUI gui;

    public ArenaViewer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Arena arena) throws IOException {
        drawElements(arena.getObstacles(), new ElementViewer());
        drawElements(arena.getCoins(), new ElementViewer());
        drawElement(arena.getPlayer(), new ElementViewer());
    }

    void drawElements(List<? extends Element> elements, ElementViewer viewer) {
        for (Element element: elements)
            drawElement(element, viewer);
    }

    void drawElement(Element element, ElementViewer viewer) {
        viewer.drawElement(element, gui);
    }
}
