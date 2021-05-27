package org.jetpack.viewer.game;

import org.jetpack.gui.ColorDatabase;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.player.Player;
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
        gui.drawFillRectangle(new Position(info.width, info.height),
                new Dimension(getModel().getWidth(), getModel().getHeight()), ColorDatabase.DARK_GRAY.getName());

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

        gui.drawRectangle(new Position(0, 0), new Dimension(gui.getTerminalWidth(), gui.getTerminalHeight()),
                Math.min(info.width, info.height), ColorDatabase.GRAY.getName());

        gui.drawText(new Position(gui.getTerminalWidth() - 11, gui.getTerminalHeight() - 1), "LIVES: ", ColorDatabase.WHITE.getName());
        gui.drawText(new Position(gui.getTerminalWidth() - 4, gui.getTerminalHeight() - 1), String.valueOf(player.getLives()), ColorDatabase.RED.getName());
        gui.drawText(new Position(3, gui.getTerminalHeight() - 1), "COINS: " + player.getCoins(), ColorDatabase.WHITE.getName());
        gui.drawText(new Position(10, gui.getTerminalHeight() - 1), String.valueOf(player.getCoins()), ColorDatabase.GOLD.getName());
        gui.drawText(new Position(3, 0), "SCORE: ", ColorDatabase.LIGHT_GRAY.getName());
        gui.drawText(new Position(10, 0), String.valueOf(getModel().getArenaBuilder().getInstant()/1000), ColorDatabase.WHITE.getName());
    }
}
