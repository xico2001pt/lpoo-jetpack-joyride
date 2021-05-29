package org.jetpack.controller.menu;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.gui.LanternaGUI;
import org.jetpack.model.menu.MainMenu;
import org.jetpack.states.ArenaState;
import org.jetpack.states.InstructionsMenuState;
import org.jetpack.states.MainMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuControllerTest {
    private GUI gui;
    private GameLoop gameLoop;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(LanternaGUI.class);
        Mockito.when(gui.getTerminalWidth()).thenReturn(30);
        Mockito.when(gui.getTerminalHeight()).thenReturn(30);
        gameLoop = new GameLoop(10, gui);
        // Just reassuring
        gameLoop.setState(new MainMenuState(new MainMenu()));
    }

    @Test
    void transitionToArena() throws IOException {
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.ENTER, GUI.ACTION.QUIT);
        gameLoop.run();
        assertTrue(gameLoop.getState() instanceof ArenaState);
    }

    @Test
    void transitionToInstructions() throws IOException {
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.DOWN, GUI.ACTION.ENTER, GUI.ACTION.QUIT);
        gameLoop.run();
        assertTrue(gameLoop.getState() instanceof InstructionsMenuState);
    }

    @Test
    void exit() throws IOException {
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.DOWN, GUI.ACTION.DOWN, GUI.ACTION.ENTER);
        gameLoop.run();
        assertTrue(gameLoop.getState() instanceof MainMenuState);
    }
}