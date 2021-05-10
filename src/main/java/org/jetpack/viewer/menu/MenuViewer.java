package org.jetpack.viewer.menu;

import org.jetpack.gui.GUI;
import org.jetpack.model.Menu;
import org.jetpack.model.Position;
import org.jetpack.viewer.Viewer;

public class MenuViewer extends Viewer<Menu> {
    public MenuViewer(Menu menu) {
        super(menu);
    }

    @Override
    public void drawModel(GUI gui) {
        gui.drawText(new Position(5, 5), "Menu", "#FFFFFF");
        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(new Position(5, 7 + i), getModel().getEntry(i),
                        getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
        }
}