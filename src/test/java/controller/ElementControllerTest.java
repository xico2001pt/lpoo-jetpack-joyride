package controller;

import model.Position;
import model.arena.Arena;
import model.elements.obstacles.Laser;
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

        assertNotEquals(new Position(5, 5), laser.getPosition());
    }

    @Test
    void moveElements() {
        Laser laser = new Laser(new Position(5, 5));
        arena.addObstacles(Arrays.asList(laser));

        controller.moveElements(110);

        assertNotEquals(new Position(4, 5), laser.getPosition());
    }
}