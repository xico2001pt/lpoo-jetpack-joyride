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
        int yInitial = (gui.getTerminalHeight() / 2) - 5;
        gui.drawText(new Position((gui.getTerminalWidth() - 15)/ 2, yInitial), "-- Game Over --", ColorDatabase.DARK_GOLD.getName());

        gui.drawText(new Position((gui.getTerminalWidth() - 9)/ 2, yInitial + 2), "Score: " + String.valueOf(getModel().getScore()), ColorDatabase.WHITE.getName());

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(new Position((gui.getTerminalWidth() - getModel().getEntry(i).length())/ 2, yInitial + 5 + i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? ColorDatabase.RED.getName() : ColorDatabase.WHITE.getName());
    }
}