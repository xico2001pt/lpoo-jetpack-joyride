package org.jetpack.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LanternaMouseAdapter extends MouseAdapter {
    LanternaGUI gui;

    public LanternaMouseAdapter(LanternaGUI gui) {
        this.gui = gui;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gui.changeMouseState(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gui.changeMouseState(false);
    }
}
