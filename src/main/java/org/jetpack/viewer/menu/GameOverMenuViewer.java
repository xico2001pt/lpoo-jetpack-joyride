package org.jetpack.viewer.menu;

import org.jetpack.gui.ColorDatabase;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.menu.GameOverMenu;
import org.jetpack.viewer.Viewer;

import java.awt.*;

public class GameOverMenuViewer extends Viewer<GameOverMenu> {
    public GameOverMenuViewer(GameOverMenu menu) {
        super(menu);
    }

    @Override
    public void drawModel(GUI gui) {
        Position center = new Position(gui.getTerminalWidth() / 2, gui.getTerminalHeight() / 2);

        gui.drawRectangle(new Position(0,0), new Dimension(gui.getTerminalWidth(), gui.getTerminalHeight()),
                1, ColorDatabase.GRAY.getName());
        gui.drawFillRectangle(new Position(1,1), new Dimension(gui.getTerminalWidth() - 2,
                gui.getTerminalHeight() - 2), ColorDatabase.DARK_GRAY.getName());

        gui.drawText(new Position(center.getX() - 7, center.getY() - 4), "-- GAMEOVER --", ColorDatabase.GOLD.getName());
        gui.drawText(new Position(center.getX() - 5, center.getY() - 1), "SCORE: ", ColorDatabase.WHITE.getName());
        gui.drawText(new Position(center.getX() + 2, center.getY() - 1), String.valueOf(getModel().getScore()),
                ColorDatabase.RED.getName());

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(new Position(center.getX() - 5, center.getY() + 2 + i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? ColorDatabase.WHITE.getName() : ColorDatabase.LIGHT_GRAY.getName());
    }
}