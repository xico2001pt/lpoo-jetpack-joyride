package org.jetpack.controller;

import org.jetpack.gui.GUI;
import org.jetpack.gui.LanternaGUI;
import org.jetpack.model.arena.RandomArenaBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.jetpack.viewer.WindowViewer;

import java.io.IOException;

class GameLoopTest {

    @Test
    void run() throws IOException {
        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.QUIT);
        GameLoop gameLoop = new GameLoop(10, gui);

        gameLoop.run();
        Mockito.verify(gui, Mockito.times(1)).getNextAction();
    }
}