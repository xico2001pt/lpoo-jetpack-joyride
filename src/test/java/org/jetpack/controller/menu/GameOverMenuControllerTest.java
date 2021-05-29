package org.jetpack.controller.menu;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.gui.LanternaGUI;
import org.jetpack.model.menu.GameOverMenu;
import org.jetpack.states.ArenaState;
import org.jetpack.states.GameOverMenuState;
import org.jetpack.states.MainMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameOverMenuControllerTest {
    private GUI gui;
    private GameLoop gameLoop;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(LanternaGUI.class);
        gameLoop = new GameLoop(10, gui);
        gameLoop.setState(new GameOverMenuState(new GameOverMenu(0)));
    }

    @Test
    void transitionToArena() throws IOException {
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.ENTER, GUI.ACTION.QUIT);
        gameLoop.run();
        assertTrue(gameLoop.getState() instanceof ArenaState);
    }

    @Test
    void transitionToMainMenu() throws IOException {
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.DOWN, GUI.ACTION.ENTER, GUI.ACTION.QUIT);
        gameLoop.run();
        assertTrue(gameLoop.getState() instanceof MainMenuState);
    }
}