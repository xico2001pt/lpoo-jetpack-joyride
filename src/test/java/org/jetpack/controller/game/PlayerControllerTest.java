package org.jetpack.controller.game;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.arena.ArenaBuilder;
import org.jetpack.model.elements.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {
    private GameLoop gameLoop;
    private Arena arena;
    private PlayerController controller;

    @BeforeEach
    void setUp() {
        gameLoop = Mockito.mock(GameLoop.class);
        Player player = new Player(new Position(5, 5));
        ArenaBuilder builder = Mockito.mock(ArenaBuilder.class);
        arena = new Arena(10, 10, builder);
        arena.setPlayer(player);
        controller = new PlayerController(arena);
    }

    @Test
    void updateNone() {
        controller.update(gameLoop, GUI.ACTION.NONE, 0);
        assertEquals(new Position(5, 5), arena.getPlayer().getPosition());
    }


    @Test
    void updateUp() {

        // Initialize actionBefore as NONE
        controller.update(gameLoop, GUI.ACTION.NONE, 0);
        controller.update(gameLoop, GUI.ACTION.MOUSE_PRESSED, 200);

        assertEquals(new Position(5, 4), arena.getPlayer().getPosition());
    }

    @Test
    void updateCannotMove() {
        arena.getPlayer().setPosition(new Position(5, 0));
        arena.setPlayer(arena.getPlayer());

        // Initialize actionBefore as NONE
        controller.update(gameLoop, GUI.ACTION.NONE, 0);
        // Initialize MOUSE PRESSED
        controller.update(gameLoop, GUI.ACTION.MOUSE_PRESSED, 200);
        // Start player's movement (up)
        controller.update(gameLoop, GUI.ACTION.MOUSE_PRESSED, 200);

        assertEquals(new Position(5, 0), arena.getPlayer().getPosition());
    }
}