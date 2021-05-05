package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(5, 2);
    }

    @Test
    void getLeft() {
        assertEquals(4, position.getLeft().getX());
    }

    @Test
    void getRight() {
        assertEquals(6, position.getRight().getX());
    }

    @Test
    void getUp() {
        assertEquals(1, position.getUp().getY());
    }

    @Test
    void getDown() {
        assertEquals(3, position.getDown().getY());
    }

    @Test
    void getIncrementedPosition() {
        assertEquals(8, position.getIncrementedPosition(3, 0).getX());
        assertEquals(1, position.getIncrementedPosition(3, -1).getY());
    }
}