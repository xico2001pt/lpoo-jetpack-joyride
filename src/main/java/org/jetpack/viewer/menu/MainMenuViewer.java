package org.jetpack.viewer.menu;

import org.jetpack.gui.ColorDatabase;
import org.jetpack.gui.GUI;
import org.jetpack.model.elements.ImageLibrary;
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
        int centerX = gui.getTerminalWidth() / 2;
        int centerY = gui.getTerminalHeight() / 2;

        gui.drawRectangle(new Position(0,0), new Dimension(gui.getTerminalWidth(), gui.getTerminalHeight()), 1, ColorDatabase.INFO.getName());
        gui.drawFillRectangle(new Position(1,1), new Dimension(gui.getTerminalWidth() - 2, gui.getTerminalHeight() - 2), ColorDatabase.BACK.getName());
        //gui.drawFillRectangle(new Position(1,centerY - 5), new Dimension(gui.getTerminalWidth() - 2, 4), ColorDatabase.GHOST_WHITE.getName());
        gui.drawImage(new Position(centerX - 5,centerY - 5), ImageLibrary.getJetpackJoyrideImage());

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(new Position(centerX - 2, centerY + i), getModel().getEntry(i),
                    getModel().isSelected(i) ? ColorDatabase.WHITE.getName() : ColorDatabase.INFO.getName());
    }
}