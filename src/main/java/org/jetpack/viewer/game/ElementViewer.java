package org.jetpack.viewer.game;

import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.elements.Element;

public interface ElementViewer<T extends Element> {
    public void drawElement(GUI gui, T element, Position offset);
}
