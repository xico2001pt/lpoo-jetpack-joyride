package org.jetpack.controller;

import org.jetpack.controller.game.ArenaController;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.arena.ArenaBuilder;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.Player;
import org.jetpack.model.elements.obstacles.Laser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArenaControllerTest {
    private Arena arena;
    private GameLoop gameLoop;

    @BeforeEach
    void setUp() {
        ArenaBuilder builder = Mockito.mock(ArenaBuilder.class);
        arena = new Arena(10, 10, builder);
        Player player = new Player(new Position(5, 5));
        arena.setPlayer(player);
        gameLoop = Mockito.mock(GameLoop.class);
    }

    @Test
    void handleCollisionsObstacles() {
        Laser laser = new Laser(arena.getPlayer().getPosition());
        arena.addObstacles(Arrays.asList(laser));

        int initial_lives = arena.getPlayer().getLives();
        ArenaController controller = new ArenaController(arena);

        controller.update(gameLoop, GUI.ACTION.NONE, 1);

        assertEquals(arena.getPlayer().getLives(), initial_lives - 1);
    }

    @Test
    void handleCollisionsCoins() {
        Coin coin = new Coin(arena.getPlayer().getPosition());
        arena.addCoins(Arrays.asList(coin));

        int initial_lives = arena.getPlayer().getLives();
        int initial_coins = arena.getPlayer().getCoins();

        ArenaController controller = new ArenaController(arena);

        controller.update(gameLoop, GUI.ACTION.NONE, 1);

        assertEquals(arena.getPlayer().getLives(), initial_lives);
        assertEquals(arena.getPlayer().getCoins(), initial_coins + 1);
    }

    @Test
    void handleCollisionsNone() {
        Laser laser = new Laser(arena.getPlayer().getPosition().getIncrementedPosition(-2, -1));
        arena.addObstacles(Arrays.asList(laser));
        Coin coin = new Coin(arena.getPlayer().getPosition().getDown());
        arena.addCoins(Arrays.asList(coin));

        int initial_lives = arena.getPlayer().getLives();
        int initial_coins = arena.getPlayer().getCoins();

        ArenaController controller = new ArenaController(arena);

        controller.update(gameLoop, GUI.ACTION.NONE, 1);

        assertEquals(arena.getPlayer().getLives(), initial_lives);
        assertEquals(arena.getPlayer().getCoins(), initial_coins);
    }
}