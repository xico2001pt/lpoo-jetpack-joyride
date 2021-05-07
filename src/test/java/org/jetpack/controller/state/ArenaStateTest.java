package org.jetpack.controller.state;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.gui.LanternaGUI;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.arena.ArenaBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class ArenaStateTest {

    @Test
    void update() throws FontFormatException, IOException, URISyntaxException {
        ArenaBuilder arenaBuilder = Mockito.mock(ArenaBuilder.class);
        GUI gui = new LanternaGUI(30, 30);
        GameLoop gameLoop = new GameLoop(30, gui);

        GameState arenaState = new ArenaState(gameLoop, gui, arenaBuilder);
        arenaState.update(GUI.ACTION.ENTER, 0);
        arenaState.update(GUI.ACTION.QUIT, 1000);

        Mockito.verify(arenaBuilder, Mockito.times(1)).incrementInstant(1000);
        Mockito.verify(arenaBuilder, Mockito.times(1)).getCoins();
        Mockito.verify(arenaBuilder, Mockito.times(1)).getObstacles();
    }
}