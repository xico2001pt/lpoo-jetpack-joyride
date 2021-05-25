package org.jetpack.viewer.menu;

import org.jetpack.gui.ColorDatabase;
import org.jetpack.gui.GUI;
import org.jetpack.model.menu.PauseMenu;
import org.jetpack.model.Position;
import org.jetpack.viewer.Viewer;

import java.awt.*;

public class PauseMenuViewer extends Viewer<PauseMenu> {
    public PauseMenuViewer(PauseMenu menu) {
        super(menu);
    }

    @Override
    public void drawModel(GUI gui) {
        int centerX = gui.getTerminalWidth() / 2;
        int centerY = gui.getTerminalHeight() / 2;

        gui.drawRectangle(new Position(0,0), new Dimension(gui.getTerminalWidth(), gui.getTerminalHeight()), 1, ColorDatabase.INFO.getName());
        gui.drawFillRectangle(new Position(1,1), new Dimension(gui.getTerminalWidth() - 2, gui.getTerminalHeight() - 2), ColorDatabase.BACK.getName());

        gui.drawText(new Position(centerX - 6, centerY - 3), "-- Paused --", ColorDatabase.GOLD.getName());

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(new Position(centerX - 3, centerY - 1 + i), getModel().getEntry(i),
                    getModel().isSelected(i) ? ColorDatabase.WHITE.getName() : ColorDatabase.INFO.getName());
    }
}