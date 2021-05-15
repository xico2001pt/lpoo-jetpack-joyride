
package org.jetpack.controller;

import org.jetpack.controller.game.ElementsController;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.arena.ArenaBuilder;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.obstacles.Laser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ElementsControllerTest {
    private ElementsController controller;
    private Arena arena;
    private GameLoop gameLoop;

    @BeforeEach
    void setUp() {
        ArenaBuilder builder = Mockito.mock(ArenaBuilder.class);
        arena = new Arena(10, 10, builder);
        controller = new ElementsController(arena);
        gameLoop = Mockito.mock(GameLoop.class);
    }

    @Test
    void moveElementsNotTime() {
        Laser laser = new Laser(new Position(5, 5));
        arena.addObstacles(Arrays.asList(laser));

        controller.update(gameLoop, GUI.ACTION.NONE, 0);

        assertEquals(new Position(5, 5), laser.getPosition());
    }

    @Test
    void moveElementsLaser() {
        Laser laser = new Laser(new Position(5, 5));
        arena.addObstacles(Arrays.asList(laser));

        controller.update(gameLoop, GUI.ACTION.NONE, 110);

        assertEquals(new Position(4, 5), laser.getPosition());
    }

    @Test
    void moveElementsCoin() {
        Coin coin = new Coin(new Position(2, 3));
        arena.addCoins(Arrays.asList(coin));

        controller.update(gameLoop, GUI.ACTION.NONE, 150);

        assertEquals(new Position(1, 3), coin.getPosition());
    }
}
