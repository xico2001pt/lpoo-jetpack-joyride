package org.jetpack.controller;

import org.jetpack.controller.game.PlayerController;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.arena.ArenaBuilder;
import org.jetpack.model.elements.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {
    private GameLoop gameLoop;

    @BeforeEach
    void setUp() {
        gameLoop = Mockito.mock(GameLoop.class);
    }

    @Test
    void updateNone() {

        Arena arena_mock = Mockito.mock(Arena.class);
        PlayerController controller = new PlayerController(arena_mock);

        controller.update(gameLoop, GUI.ACTION.NONE, 0);
        Mockito.verify(arena_mock, Mockito.times(0)).getPlayer();
    }


    @Test
    void updateDown() {

        Player player = new Player(new Position(5, 5));
        ArenaBuilder builder = Mockito.mock(ArenaBuilder.class);
        Arena arena = new Arena(10, 10, builder);
        arena.setPlayer(player);
        PlayerController controller = new PlayerController(arena);
        controller.update(gameLoop, GUI.ACTION.DOWN, 0);

        assertEquals(new Position(5, 6), arena.getPlayer().getPosition());
    }

    @Test
    void updateCannotMove() {

        Player player = new Player(new Position(5, 0));
        ArenaBuilder builder = Mockito.mock(ArenaBuilder.class);
        Arena arena = new Arena(10, 10, builder);
        arena.setPlayer(player);
        PlayerController controller = new PlayerController(arena);
        controller.update(gameLoop, GUI.ACTION.UP, 0);

        assertEquals(new Position(5, 0), arena.getPlayer().getPosition());
    }
}