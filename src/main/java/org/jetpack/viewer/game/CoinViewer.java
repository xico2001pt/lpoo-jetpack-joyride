package org.jetpack.viewer.game;

import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.elements.Coin;

public class CoinViewer implements ElementViewer<Coin> {
    @Override
    public void drawElement(GUI gui, Coin element, Position offset) {
        gui.drawImage(element.getPosition().getIncrementedPosition(offset), element.getImage());
    }
}
