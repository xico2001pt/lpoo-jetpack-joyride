package viewer;

import gui.GUI;
import model.arena.Arena;
import model.elements.Element;

import java.io.IOException;
import java.util.List;

public class ArenaViewer {
    private final GUI gui;

    public ArenaViewer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Arena arena) throws IOException {
        gui.clear();
        drawElements(arena.getObstacles(), new ElementViewer());
        drawElements(arena.getCoins(), new ElementViewer());
        drawElement(arena.getPlayer(), new ElementViewer());
        gui.refresh();

        // TODO: remove gui commands
    }

    void drawElements(List<? extends Element> elements, ElementViewer viewer) {
        for (Element element: elements)
            drawElement(element, viewer);
    }

    void drawElement(Element element, ElementViewer viewer) {
        viewer.drawElement(element, gui);
    }

    public GUI.ACTION getNextAction() throws IOException {
        return gui.getNextAction();
    }
}
