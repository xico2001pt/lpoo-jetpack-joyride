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
        Position center = new Position(gui.getTerminalWidth() / 2, gui.getTerminalHeight() / 2);

        gui.drawRectangle(new Position(0,0), new Dimension(gui.getTerminalWidth(), gui.getTerminalHeight()),
                1, ColorDatabase.GRAY.getName());
        gui.drawFillRectangle(new Position(1,1), new Dimension(gui.getTerminalWidth() - 2,
                gui.getTerminalHeight() - 2), ColorDatabase.DARK_GRAY.getName());
        gui.drawImage(new Position(center.getX() - 5, center.getY() - 5), ImageLibrary.getJetpackJoyrideImage());

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(new Position(center.getX() - 2, center.getY() + i), getModel().getEntry(i),
                    getModel().isSelected(i) ? ColorDatabase.WHITE.getName() : ColorDatabase.LIGHT_GRAY.getName());
    }
}