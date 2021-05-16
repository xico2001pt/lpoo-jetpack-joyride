package org.jetpack.viewer.menu;

import org.jetpack.gui.ColorDatabase;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.menu.GameOverMenu;
import org.jetpack.model.menu.MainMenu;
import org.jetpack.viewer.Viewer;

public class GameOverMenuViewer extends Viewer<GameOverMenu> {
    public GameOverMenuViewer(GameOverMenu menu) {
        super(menu);
    }

    @Override
    public void drawModel(GUI gui) {
        gui.drawText(new Position(5, 5), "Game Over", ColorDatabase.DARK_GOLD.getName());
        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(new Position(5, 8 + i), getModel().getEntry(i),
                    getModel().isSelected(i) ? ColorDatabase.RED.getName() : ColorDatabase.WHITE.getName());
    }
}