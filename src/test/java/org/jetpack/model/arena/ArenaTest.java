package org.jetpack.model.arena;

import org.jetpack.model.Position;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.Player;
import org.jetpack.model.elements.obstacles.EnergyWall;
import org.jetpack.model.elements.obstacles.Laser;
import org.jetpack.model.elements.obstacles.Obstacle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArenaTest {

    private Arena arena;

    @BeforeEach
    void setUp() {

        ArenaBuilder builder = Mockito.mock(ArenaBuilder.class);
        arena = new Arena(20, 20, builder);
    }

    @Test
    void setPlayer() {
        Player p = new Player(new Position(10, 10));
        arena.setPlayer(p);
        assertEquals(p, arena.getPlayer());
    }

    @Test
    void addCoins() {
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin(new Position(5,5)));
        coins.add(new Coin(new Position(15,15)));
        arena.addCoins(coins);
        assertEquals(coins, arena.getCoins());
    }

    @Test
    void addObstacles() {
        List<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new EnergyWall(new Position(5,5)));
        obstacles.add(new Laser(new Position(15,15)));
        arena.addObstacles(obstacles);
        assertEquals(obstacles, arena.getObstacles());
    }
}