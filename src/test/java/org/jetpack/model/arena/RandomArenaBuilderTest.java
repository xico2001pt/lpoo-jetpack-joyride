package org.jetpack.model.arena;

import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.obstacles.Obstacle;
import org.jetpack.model.elements.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomArenaBuilderTest {

    private ArenaBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new RandomArenaBuilder(30,20);
    }

    @Test
    void createArena() {
        Arena arena = builder.createArena();
        assertEquals(30, arena.getWidth());
        assertEquals(20, arena.getHeight());
    }

    @Test
    void incrementInstant() {
        builder.incrementInstant(3);
        assertEquals(3, builder.getInstant());

        builder.incrementInstant(-2);
        assertEquals(3, builder.getInstant());
    }

    @Test
    void getCoins() {
        builder.incrementInstant(2000);
        assertTrue(builder.getCoins().size() > 0);
        for (Coin coin : builder.getCoins()) {
            assert(coin != null);
        }
    }

    @Test
    void getObstacles() {
        builder.incrementInstant(2000);
        assertTrue(builder.getObstacles().size() > 0);
        for (Obstacle obstacle : builder.getObstacles()) {
            assert(obstacle != null);
        }
    }

    @Test
    void createPlayer() {
        Player p = builder.createPlayer();
        assertEquals(1, p.getPosition().getX());
        assertEquals(10, p.getPosition().getY());
    }
}