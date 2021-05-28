package org.jetpack.states;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.arena.ArenaBuilder;
import org.jetpack.model.elements.player.Player;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class ArenaStateTest {

    @Test
    void update() throws IOException {
        ArenaBuilder arenaBuilder = Mockito.mock(ArenaBuilder.class);
        Arena arena = new Arena(10, 10, arenaBuilder);
        arena.setPlayer(new Player(new Position(5, 5)));
        Mockito.when(arenaBuilder.createArena()).thenReturn(arena);
        GUI gui = Mockito.mock(GUI.class);
        GameLoop gameLoop = new GameLoop(30, gui);

        State arenaState = new ArenaState(arena);
        arenaState.step(gameLoop, gui, 1000);

        Mockito.verify(arenaBuilder, Mockito.times(1)).incrementInstant(1000);
        Mockito.verify(arenaBuilder, Mockito.times(1)).getCoins();
        Mockito.verify(arenaBuilder, Mockito.times(1)).getObstacles();
    }
}
