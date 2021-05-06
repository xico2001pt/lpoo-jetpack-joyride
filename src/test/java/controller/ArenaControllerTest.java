package controller;

import model.Position;
import model.arena.Arena;
import model.arena.ArenaBuilder;
import model.elements.Coin;
import model.elements.Player;
import model.elements.obstacles.Laser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArenaControllerTest {
    private Arena arena;
    @BeforeEach
    void setUp() {
        arena = new Arena(10, 10);
        Player player = new Player(new Position(5, 5));
        arena.setPlayer(player);
    }

    @Test
    void handleCollisionsObstacles() {
        Laser laser = new Laser(arena.getPlayer().getPosition());
        arena.addObstacles(Arrays.asList(laser));

        int initial_lives = arena.getPlayer().getLives();
        ArenaBuilder arenaBuilder = Mockito.mock(ArenaBuilder.class);
        Mockito.when(arenaBuilder.createArena()).thenReturn(arena);
        ArenaController controller = new ArenaController(arenaBuilder);

        controller.updateArena();

        assertEquals(arena.getPlayer().getLives(), initial_lives - 1);
    }

    @Test
    void handleCollisionsCoins() {
        Coin coin = new Coin(arena.getPlayer().getPosition());
        arena.addCoins(Arrays.asList(coin));

        int initial_lives = arena.getPlayer().getLives();
        int initial_coins = arena.getPlayer().getCoins();

        ArenaBuilder arenaBuilder = Mockito.mock(ArenaBuilder.class);
        Mockito.when(arenaBuilder.createArena()).thenReturn(arena);
        ArenaController controller = new ArenaController(arenaBuilder);

        controller.updateArena();

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

        ArenaBuilder arenaBuilder = Mockito.mock(ArenaBuilder.class);
        Mockito.when(arenaBuilder.createArena()).thenReturn(arena);
        ArenaController controller = new ArenaController(arenaBuilder);

        controller.updateArena();

        assertEquals(arena.getPlayer().getLives(), initial_lives);
        assertEquals(arena.getPlayer().getCoins(), initial_coins);
    }
}