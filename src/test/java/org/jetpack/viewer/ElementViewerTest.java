package org.jetpack.viewer;

import org.jetpack.gui.GUI;
import org.jetpack.model.elements.Element;
import org.jetpack.viewer.game.ElementViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ElementViewerTest {
    private Element element;
    private ElementViewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        element = Mockito.mock(Element.class);
        viewer = new ElementViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        viewer.drawElement(element, gui);
        Mockito.verify(gui, Mockito.times(1)).drawElement(element);
    }
}