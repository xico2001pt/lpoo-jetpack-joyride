package org.jetpack.viewer;

import org.jetpack.gui.GUI;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Element;

import java.util.List;

public class ArenaViewer extends Viewer<Arena> {

    public ArenaViewer(Arena arena) {
        super(arena);
    }

    @Override
    public void drawElements(GUI gui) {
        drawElements(gui, getModel().getObstacles(), new ElementViewer());
        drawElements(gui, getModel().getCoins(), new ElementViewer());
        drawElement(gui, getModel().getPlayer(), new ElementViewer());
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer viewer) {
        for (T element: elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer viewer) {
        viewer.drawElement(element, gui);
    }
}
