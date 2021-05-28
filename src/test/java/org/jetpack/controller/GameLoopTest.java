package org.jetpack.controller;

import org.jetpack.gui.GUI;
import org.jetpack.gui.LanternaGUI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class GameLoopTest {

    @Test
    void run() throws IOException {
        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.DOWN, GUI.ACTION.ENTER);

        GameLoop gameLoop = new GameLoop(10, gui);

        gameLoop.run();
        Mockito.verify(gui, Mockito.times(2)).getNextAction();
    }
}