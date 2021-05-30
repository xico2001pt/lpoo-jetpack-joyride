package org.jetpack.controller.menu;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.gui.LanternaGUI;
import org.jetpack.model.menu.InstructionsMenu;
import org.jetpack.states.InstructionsMenuState;
import org.jetpack.states.MainMenuState;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InstructionsMenuControllerTest {

    @Test
    void transitionToMainMenu() throws IOException {
        GUI gui = Mockito.mock(LanternaGUI.class);
        Mockito.when(gui.getTerminalWidth()).thenReturn(30);
        Mockito.when(gui.getTerminalHeight()).thenReturn(20);

        GameLoop gameLoop = new GameLoop(10, gui);
        gameLoop.setState(new InstructionsMenuState(new InstructionsMenu()));

        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.ENTER, GUI.ACTION.QUIT);
        gameLoop.run();
        assertTrue(gameLoop.getState() instanceof MainMenuState);
    }
}