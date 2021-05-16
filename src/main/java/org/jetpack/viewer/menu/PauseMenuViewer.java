package org.jetpack.viewer.menu;

import org.jetpack.gui.ColorDatabase;
import org.jetpack.gui.GUI;
import org.jetpack.model.menu.PauseMenu;
import org.jetpack.model.Position;
import org.jetpack.viewer.Viewer;

public class PauseMenuViewer extends Viewer<PauseMenu> {
    public PauseMenuViewer(PauseMenu menu) {
        super(menu);
    }

    @Override
    public void drawModel(GUI gui) {
        int yInitial = (gui.getTerminalHeight() / 2) - 4;
        gui.drawText(new Position((gui.getTerminalWidth() - 12)/ 2, yInitial), "-- Paused --", ColorDatabase.DARK_GOLD.getName());
        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(new Position((gui.getTerminalWidth() - getModel().getEntry(i).length())/ 2, yInitial + 2 + i), getModel().getEntry(i),
                    getModel().isSelected(i) ? ColorDatabase.RED.getName() : ColorDatabase.WHITE.getName());
    }
}