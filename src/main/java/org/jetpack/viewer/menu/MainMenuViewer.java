package org.jetpack.viewer.menu;

import org.jetpack.gui.ColorDatabase;
import org.jetpack.gui.GUI;
import org.jetpack.model.menu.MainMenu;
import org.jetpack.model.Position;
import org.jetpack.viewer.Viewer;

public class MainMenuViewer extends Viewer<MainMenu> {
    public MainMenuViewer(MainMenu menu) {
        super(menu);
    }

    @Override
    public void drawModel(GUI gui) {
        gui.drawText(new Position(5, 4), "Menu", ColorDatabase.GOLD.getName());
        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(new Position(5, 7 + i), getModel().getEntry(i),
                        getModel().isSelected(i) ? ColorDatabase.RED.getName() : ColorDatabase.WHITE.getName());
        }
}