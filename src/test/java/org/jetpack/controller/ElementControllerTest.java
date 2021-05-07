package org.jetpack.controller;

import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.obstacles.Laser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ElementControllerTest {
    private ElementController controller;
    private Arena arena;

    @BeforeEach
    void setUp() {
        arena = new Arena(10, 10);
        controller = new ElementController(arena);
    }

    @Test
    void moveElementsNotTime() {
        Laser laser = new Laser(new Position(5, 5));
        arena.addObstacles(Arrays.asList(laser));

        controller.moveElements(0);

        assertEquals(new Position(5, 5), laser.getPosition());
    }

    @Test
    void moveElementsLaser() {
        Laser laser = new Laser(new Position(5, 5));
        arena.addObstacles(Arrays.asList(laser));

        controller.moveElements(110);

        assertEquals(new Position(4, 5), laser.getPosition());
    }

    @Test
    void moveElementsCoin() {
        Coin coin = new Coin(new Position(2, 3));
        arena.addCoins(Arrays.asList(coin));

        controller.moveElements(150);

        assertEquals(new Position(1, 3), coin.getPosition());
    }
}