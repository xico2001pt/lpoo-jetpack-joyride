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
        gui.drawText(new Position(5, 5), "Menu", ColorDatabase.WHITE.getName());
        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(new Position(5, 7 + i), getModel().getEntry(i),
                    getModel().isSelected(i) ? ColorDatabase.GOLD.getName() : ColorDatabase.WHITE.getName());
    }
}