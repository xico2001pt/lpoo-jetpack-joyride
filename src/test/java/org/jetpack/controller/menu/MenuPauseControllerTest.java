package org.jetpack.controller.menu;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.gui.LanternaGUI;
import org.jetpack.model.arena.RandomArenaBuilder;
import org.jetpack.model.menu.PauseMenu;
import org.jetpack.states.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MenuPauseControllerTest {
    private GUI gui;
    private GameLoop gameLoop;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(LanternaGUI.class);
        Mockito.when(gui.getTerminalWidth()).thenReturn(30);
        Mockito.when(gui.getTerminalHeight()).thenReturn(20);
        gameLoop = new GameLoop(10, gui);
    }

    @Test
    void transitionToMainMenu() throws IOException {
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.DOWN, GUI.ACTION.ENTER, GUI.ACTION.QUIT);
        gameLoop.setState(new PauseMenuState(new PauseMenu(), Mockito.mock(ArenaState.class)));
        gameLoop.run();
        assertTrue(gameLoop.getState() instanceof MainMenuState);
    }

    @Test
    void transitionFromArenaToPause() throws IOException {
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.PAUSE, GUI.ACTION.QUIT);
        gameLoop.setState(new ArenaState(new RandomArenaBuilder(10,10).createArena()));
        gameLoop.run();
        assertTrue(gameLoop.getState() instanceof PauseMenuState);
    }
}