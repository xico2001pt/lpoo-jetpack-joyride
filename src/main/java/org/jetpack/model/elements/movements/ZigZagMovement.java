package org.jetpack.model.elements.movements;

import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ZigZagMovement extends MovementStrategy {
    private boolean up = true;

    @Override
    public Position move(Position position, Arena arena) {
        Position nextPosition;
        if (up) {
            nextPosition =  new Position(position.getLeft().getX(), max(position.getUp().getY(), 0));
        } else {
            nextPosition =  new Position(position.getLeft().getX(), min(position.getDown().getY(), arena.getHeight() - 1));
        }
        up = !up;
        return nextPosition;
    }
}
