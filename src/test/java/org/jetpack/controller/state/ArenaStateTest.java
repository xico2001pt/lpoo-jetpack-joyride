/*
package org.jetpack.controller.state;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.arena.ArenaBuilder;
import org.jetpack.model.elements.Player;
import org.jetpack.states.ArenaState;
import org.jetpack.states.GameState;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

class ArenaStateTest {

    @Test
    void update() throws FontFormatException, IOException, URISyntaxException {
        ArenaBuilder arenaBuilder = Mockito.mock(ArenaBuilder.class);
        Arena arena = new Arena(10, 10);
        arena.setPlayer(new Player(new Position(5, 5)));
        Mockito.when(arenaBuilder.createArena()).thenReturn(arena);
        GUI gui = Mockito.mock(GUI.class);
        GameLoop gameLoop = new GameLoop(30, gui);

        GameState arenaState = new ArenaState(gameLoop, gui, arenaBuilder);
        arenaState.update(GUI.ACTION.QUIT, 1000);

        Mockito.verify(arenaBuilder, Mockito.times(1)).incrementInstant(1000);
        Mockito.verify(arenaBuilder, Mockito.times(1)).getCoins();
        Mockito.verify(arenaBuilder, Mockito.times(1)).getObstacles();
    }
}*/
