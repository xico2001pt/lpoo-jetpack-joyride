package org.jetpack.viewer.game;

import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.Player;
import org.jetpack.viewer.Viewer;

import java.util.List;

public class ArenaViewer extends Viewer<Arena> {

    public ArenaViewer(Arena arena) {
        super(arena);
    }

    @Override
    public void drawModel(GUI gui) {
        drawElements(gui, getModel().getObstacles(), new ElementViewer());
        drawElements(gui, getModel().getCoins(), new ElementViewer());
        drawElement(gui, getModel().getPlayer(), new ElementViewer());
        drawInfo(gui, getModel().getPlayer());
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer viewer) {
        for (T element: elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer viewer) {
        viewer.drawElement(element, gui);
    }

    private void drawInfo(GUI gui, Player player) {

        int infoWidth = (gui.getTerminalWidth() - gui.getArenaWidth()) / 2;
        int infoHeight = (gui.getTerminalHeight() - gui.getArenaHeight()) / 2;

        for (int y = 0; y < gui.getTerminalWidth(); y++) {
            for (int x = 0; x < gui.getTerminalHeight(); x++) {

                if (y != 0 && (x < infoWidth || x >= gui.getTerminalWidth() - infoWidth))
                    gui.color(new Position(x, y), "#303030");
                if (x != 0 && (y < infoHeight || y >= gui.getTerminalHeight() - infoHeight))
                    gui.color(new Position(x, y), "#303030");
            }
        }

        gui.drawText(new Position(0, 0), "Lives: " + player.getLives(), "#303030");
        gui.drawText(new Position(gui.getTerminalWidth()/2, 0), "Coins: " + player.getCoins(), "#303030");
    }
}
