package org.jetpack.viewer;

import org.jetpack.gui.GUI;
import org.jetpack.model.Matrix;
import org.jetpack.model.Position;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.obstacles.Laser;
import org.jetpack.viewer.game.ElementViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ElementViewerTest {
    private Element element;
    private ElementViewer viewer;
    private GUI gui;

/*    @BeforeEach
    void setUp() {
        element = new Laser(new Position(0, 0));
        viewer = new ElementViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        viewer.drawElement(element, new Position(0, 0), gui);
        Mockito.verify(gui, Mockito.times(1)).drawImage(element.getPosition(), element.getImage());
    }*/
}