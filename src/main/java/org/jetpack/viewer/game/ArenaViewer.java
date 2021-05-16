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
        gui.drawFillRectangle(new Position(1,1), getModel().getWidth(), getModel().getHeight(), ColorDatabase.BACK.getName());
        drawElements(gui, getModel().getObstacles(), new ObstacleViewer());
        drawElements(gui, getModel().getCoins(), new CoinViewer());
        drawElement(gui, getModel().getPlayer(), new PlayerViewer());
        drawInfo(gui, getModel().getPlayer());
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element: elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        int infoWidth = (gui.getTerminalWidth() - getModel().getWidth()) / 2;
        int infoHeight = (gui.getTerminalHeight() - getModel().getHeight()) / 2;
        viewer.drawElement(gui, element, new Position(infoWidth, infoHeight));
    }

    private void drawInfo(GUI gui, Player player) {
        int infoWidth = (gui.getTerminalWidth() - getModel().getWidth()) / 2;
        int infoHeight = (gui.getTerminalHeight() - getModel().getHeight()) / 2;

        /*gui.drawFillRectangle(new Position(0, 0), gui.getTerminalWidth(), infoHeight, ColorDatabase.INFO.getName());
        gui.drawFillRectangle(new Position(0, gui.getTerminalHeight() - infoHeight), gui.getTerminalWidth(), infoHeight, ColorDatabase.INFO.getName());
        gui.drawFillRectangle(new Position(0, 1), infoWidth, gui.getTerminalHeight() - 1, ColorDatabase.INFO.getName());
        gui.drawFillRectangle(new Position(gui.getTerminalWidth() - infoWidth, 1), infoWidth, gui.getTerminalHeight() - 1, ColorDatabase.INFO.getName());*/

        gui.drawRectangle(new Position(0, 0), gui.getTerminalWidth(), gui.getTerminalHeight(), ColorDatabase.INFO.getName());

        gui.drawText(new Position(0, 0), "Lives: " + player.getLives(), ColorDatabase.INFOTEXT.getName());
        gui.drawText(new Position(gui.getTerminalWidth()/2, 0), "Coins: " + player.getCoins(), ColorDatabase.INFOTEXT.getName());
    }
}
