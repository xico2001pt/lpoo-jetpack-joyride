package org.jetpack.viewer.menu;

import org.jetpack.gui.ColorDatabase;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.menu.InstructionsMenu;
import org.jetpack.viewer.Viewer;

import java.awt.*;

public class InstructionsMenuViewer extends Viewer<InstructionsMenu> {

    public InstructionsMenuViewer(InstructionsMenu menu) {super(menu);}

    @Override
    protected void drawModel(GUI gui) {
        Position center = new Position(gui.getTerminalWidth() / 2, gui.getTerminalHeight() / 2);

        gui.drawRectangle(new Position(0,0), new Dimension(gui.getTerminalWidth(), gui.getTerminalHeight()),
                1, ColorDatabase.GRAY.getName());
        gui.drawFillRectangle(new Position(1,1), new Dimension(gui.getTerminalWidth() - 2,
                gui.getTerminalHeight() - 2), ColorDatabase.DARK_GRAY.getName());

        gui.drawText(new Position(center.getX() - 9, 2), "-- INSTRUCTIONS --", ColorDatabase.GOLD.getName());
        gui.drawText(new Position(2, 5), "P", ColorDatabase.RED.getName());
        gui.drawText(new Position(4, 5), "NORMAL", ColorDatabase.WHITE.getName());
        gui.drawText(new Position(2, 7), "P", ColorDatabase.LIGHT_GRAY.getName());
        gui.drawText(new Position(4, 7), "IMMORTAL     number 1", ColorDatabase.WHITE.getName());
        gui.drawText(new Position(2, 9), "P", ColorDatabase.GOLD.getName());
        gui.drawText(new Position(4, 9), "DOUBLE-COINS number 2", ColorDatabase.WHITE.getName());
        gui.drawText(new Position(2, 11), "P", ColorDatabase.VIBRANT_GREEN.getName());
        gui.drawText(new Position(4, 11), "SLOW-DOWN    number 3", ColorDatabase.WHITE.getName());
        gui.drawText(new Position(center.getX() - 9, 14), "10 COINS = 10 SECS", ColorDatabase.WHITE.getName());

        gui.drawText(new Position(gui.getTerminalWidth() - 6, gui.getTerminalHeight() - 3), getModel().getEntry(0),
                ColorDatabase.WHITE.getName());
    }
}
