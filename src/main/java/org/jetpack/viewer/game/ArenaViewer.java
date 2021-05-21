package org.jetpack.viewer.game;

import org.jetpack.gui.ColorDatabase;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.Player;
import org.jetpack.viewer.Viewer;

import java.awt.*;
import java.util.List;

public class ArenaViewer extends Viewer<Arena> {
    private final Dimension info;

    public ArenaViewer(Arena arena) {
        super(arena);
        this.info = new Dimension(0,0);
    }

    @Override
    public void drawModel(GUI gui) {
        info.width = (gui.getTerminalWidth() - getModel().getWidth()) / 2;
        info.height = (gui.getTerminalHeight() - getModel().getHeight()) / 2;
        gui.drawFillRectangle(new Position(info.width, info.height), new Dimension(getModel().getWidth(), getModel().getHeight()), ColorDatabase.BACK.getName());
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
        viewer.drawElement(gui, element, new Position(info.width, info.height));
    }

    private void drawInfo(GUI gui, Player player) {
        gui.drawRectangle(new Position(0, 0), new Dimension(gui.getTerminalWidth(), gui.getTerminalHeight()), Math.min(info.width, info.height), ColorDatabase.INFO.getName());
        gui.drawText(new Position(0, 0), "Lives: " + player.getLives(), ColorDatabase.INFOTEXT.getName());
        gui.drawText(new Position(gui.getTerminalWidth()/2, 0), "Coins: " + player.getCoins(), ColorDatabase.INFOTEXT.getName());
    }
}
