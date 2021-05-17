package org.jetpack.viewer.menu;

import org.jetpack.gui.ColorDatabase;
import org.jetpack.gui.GUI;
import org.jetpack.model.menu.MainMenu;
import org.jetpack.model.Position;
import org.jetpack.viewer.Viewer;

import java.awt.*;

public class MainMenuViewer extends Viewer<MainMenu> {
    public MainMenuViewer(MainMenu menu) {
        super(menu);
    }

    @Override
    public void drawModel(GUI gui) {
        int yInitial = (gui.getTerminalHeight() / 2) - 7;

        gui.drawRectangle(new Position(0,0), new Dimension(gui.getTerminalWidth(), gui.getTerminalHeight()), 1, ColorDatabase.INFO.getName());
        gui.drawFillRectangle(new Position(1,1), new Dimension(gui.getTerminalWidth() - 2, gui.getTerminalHeight() - 2), ColorDatabase.BACK.getName());

        gui.drawText(new Position((gui.getTerminalWidth() - 11)/ 2, yInitial - 1), "+---------+", ColorDatabase.GOLD.getName());
        gui.drawText(new Position((gui.getTerminalWidth() - 11)/ 2, yInitial), "|", ColorDatabase.GOLD.getName());
        gui.drawText(new Position((gui.getTerminalWidth() - 11)/ 2 + 1, yInitial), " JETPACK ", ColorDatabase.SILVER.getName());
        gui.drawText(new Position((gui.getTerminalWidth() - 11)/ 2 + 10, yInitial), "|", ColorDatabase.GOLD.getName());
        gui.drawText(new Position((gui.getTerminalWidth() - 11)/ 2, yInitial + 1), "| JOYRIDE |", ColorDatabase.GOLD.getName());
        gui.drawText(new Position((gui.getTerminalWidth() - 11)/ 2, yInitial + 2), "+---------+", ColorDatabase.GOLD.getName());

        gui.drawText(new Position((gui.getTerminalWidth() - 4)/ 2, yInitial + 4), "menu", ColorDatabase.DARK_GOLD.getName());
        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(new Position((gui.getTerminalWidth() - getModel().getEntry(i).length())/ 2, yInitial + 6 + i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? ColorDatabase.RED.getName() : ColorDatabase.WHITE.getName());
        }
}