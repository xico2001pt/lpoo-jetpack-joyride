package org.jetpack.model;

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
        Position p = position.getLeft();
        assertEquals(4, p.getX());
        assertEquals(2, p.getY());
    }

    @Test
    void getRight() {
        Position p = position.getRight();
        assertEquals(6, p.getX());
        assertEquals(2, p.getY());
    }

    @Test
    void getUp() {
        Position p = position.getUp();
        assertEquals(5, p.getX());
        assertEquals(1, p.getY());
    }

    @Test
    void getDown() {
        Position p = position.getDown();
        assertEquals(5, p.getX());
        assertEquals(3, p.getY());
    }

    @Test
    void getIncrementedPosition() {
        Position p = position.getIncrementedPosition(3,-1);
        assertEquals(8, p.getX());
        assertEquals(1, p.getY());
    }
}