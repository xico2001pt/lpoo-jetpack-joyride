package model.arena;

import model.elements.Player;
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
        // TODO: Test creation of Player
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
        builder.incrementInstant(1000);
        assertTrue(builder.getCoins().size() > 0);
    }

    @Test
    void getObstacles() {
        builder.incrementInstant(1000);
        assertTrue(builder.getObstacles().size() > 0);
    }

    @Test
    void createPlayer() {
        Player p = builder.createPlayer();
        assertEquals(1, p.getPosition().getX());
        assertEquals(10, p.getPosition().getY());
    }
}