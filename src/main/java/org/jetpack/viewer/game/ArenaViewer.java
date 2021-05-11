package org.jetpack.viewer.game;

import org.jetpack.gui.ColorDatabase;
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
        int infoWidth = (gui.getTerminalWidth() - getModel().getWidth()) / 2;
        int infoHeight = (gui.getTerminalHeight() - getModel().getHeight()) / 2;
        viewer.drawElement(element, new Position(infoWidth, infoHeight), gui);
    }

    private void drawInfo(GUI gui, Player player) {
        int infoWidth = (gui.getTerminalWidth() - getModel().getWidth()) / 2;
        int infoHeight = (gui.getTerminalHeight() - getModel().getHeight()) / 2;

        gui.drawRectangle(new Position(0, 0), gui.getTerminalWidth(), infoHeight, ColorDatabase.DARK_GREY.getName());
        gui.drawRectangle(new Position(0, gui.getTerminalHeight() - infoHeight), gui.getTerminalWidth(), infoHeight, ColorDatabase.DARK_GREY.getName());
        gui.drawRectangle(new Position(0, 1), infoWidth, gui.getTerminalHeight() - 1, ColorDatabase.DARK_GREY.getName());
        gui.drawRectangle(new Position(gui.getTerminalWidth() - infoWidth, 1), infoWidth, gui.getTerminalHeight() - 1, ColorDatabase.DARK_GREY.getName());


        gui.drawText(new Position(0, 0), "Lives: " + player.getLives(), ColorDatabase.DARK_GREY.getName());
        gui.drawText(new Position(gui.getTerminalWidth()/2, 0), "Coins: " + player.getCoins(), ColorDatabase.DARK_GREY.getName());
    }
}
