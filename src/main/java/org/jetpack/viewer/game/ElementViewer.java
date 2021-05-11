package org.jetpack.viewer.game;

import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.elements.Element;

public class ElementViewer {
    public void drawElement(Element element, Position offset, GUI gui) {
        gui.drawImage(element.getPosition().getIncrementedPosition(offset), element.getImage());
    }
}
