package org.jetpack.viewer.game;

import org.jetpack.gui.ColorDatabase;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.elements.Player;

public class PlayerViewer implements ElementViewer<Player> {
    @Override
    public void drawElement(GUI gui, Player element, Position offset) {
        gui.drawImage(element.getPosition().getIncrementedPosition(offset), element.getImage());
    }
}
